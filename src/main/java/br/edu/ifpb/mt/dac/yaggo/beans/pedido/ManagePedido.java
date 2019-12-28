package br.edu.ifpb.mt.dac.yaggo.beans.pedido;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.PedidoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.MesaService;
import br.edu.ifpb.mt.dac.yaggo.services.PedidoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ManagePedido extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PedidoFilter pedidoFilter;

	@Inject
	private PedidoService pedidoService;

	private List<Pedido> pedidos;

	private Mesa mesa;

	@Inject
	private MesaService mesaService;

	@PostConstruct
	public void init() {
		limpar();
		try {
			if (mesa == null) {
			} else {
				pedidoFilter.setMesa(mesaService.getByID(mesa.getId()));
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		filtrar();
	}

	public String delete(Pedido pedido) {
		try {
			pedidoService.delete(pedido);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pedido '" + pedido.getLocal() + " id=" + pedido.getId() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;

	}

	public String filtrar() {
		try {
			pedidos = pedidoService.findBy(getPedidoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.pedidoFilter = new PedidoFilter();
		return null;
	}

	public PedidoFilter getPedidoFilter() {
		return pedidoFilter;
	}

	public void setPedidoFilter(PedidoFilter pedidoFilter) {
		this.pedidoFilter = pedidoFilter;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String finalizar(Pedido pedido) {
		try {
			marcarFuncionaro(pedido);
			pedido.setFinalizado(true);
			pedidoService.update(pedido);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso(
				"Pedido '" + pedido.getId() + " para " + pedido.getLocal().getNome() + "' atualizado");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;

	}

	public String entregar(Pedido pedido) {
		try {
			marcarFuncionaro(pedido);
			pedido.setDataEntregue(new Date());
			pedidoService.update(pedido);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso(
				"Pedido '" + pedido.getId() + " para " + pedido.getLocal().getNome() + "' atualizado");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;

	}

	private void marcarFuncionaro(Pedido pedido) {
		Funcionario f = getFuncionarioLogado();
		if (f != null) {
			if (!pedido.getFuncionarios().contains(f)) {
				pedido.getFuncionarios().add(f);
			}
		}
	}

	public List<Mesa> getAllMesas() {
		try {
			return mesaService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
