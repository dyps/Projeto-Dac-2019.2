package br.edu.ifpb.mt.dac.yaggo.beans.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.entities.Item;
import br.edu.ifpb.mt.dac.yaggo.entities.LocalPedido;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.EnderecoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.EnderecoService;
import br.edu.ifpb.mt.dac.yaggo.services.ItemService;
import br.edu.ifpb.mt.dac.yaggo.services.MesaService;
import br.edu.ifpb.mt.dac.yaggo.services.PedidoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class PedidoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Pedido pedido;
	@Inject
	private PedidoService pedidoService;
	@Inject
	private ClienteService clienteService;
	@Inject
	private MesaService mesaService;
	@Inject
	private ItemService itemService;
	private Mesa mesa;
	@Inject
	private EnderecoService enderecoService;
	private List<Endereco> enderecos;

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public boolean isClienteSelected() {
		return pedido.getCliente() != null;

	}

	public void loadEnderecos() {
		try {
			if (isClienteSelected()) {
				EnderecoFilter enderecoFilter = new EnderecoFilter();
				enderecoFilter.setCliente(pedido.getCliente());
				enderecos = enderecoService.findBy(enderecoFilter);
			} else {
				enderecos = new ArrayList<>();
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}

	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void init() {
		try {
			if (pedido == null) {
				pedido = new Pedido();
			} else {
				pedido = pedidoService.getByID(pedido.getId());
				if (pedido.getLocal() == LocalPedido.ENTREGA) {
					loadEnderecos();
				}

			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}

		try {
			if (mesa == null) {
			} else {
				mesa = mesaService.getByID(mesa.getId());
				pedido.setLocal(LocalPedido.MESA);
				pedido.setMesa(mesa);
			}
		} catch (

		ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}

	}

	public String addItem() {
		salvar();
		return EnderecoPaginas.PAGINA_PRINCIPAL_ITEM + "pedido=" + pedido.getId();

	}

	private boolean validate(Pedido pedido) {
		if (pedido.getItems() != null) {
			if (pedido.getItems().size() > 1) {
				return true;
			}
		}
		reportarMensagemDeErro("Deve conter items");
		return false;
	}

	public String savePedido() {
		if (validate(pedido)) {
			return salvar();
		}
		return null;
	}

	private String salvar() {
		try {
			if (pedido.getItems() != null) {
				float soma = 0;
				for (Item item : pedido.getItems()) {
					soma += item.getValor();
				}
				if (pedido.getLocal() == LocalPedido.ENTREGA) {
					soma += pedido.getValorEntrega();
				}
				pedido.setValorTotal(soma);
			}
			Funcionario f = getFuncionarioLogado();
			if (f != null) {
				if (pedido.getFuncionarios() != null && pedido.getFuncionarios().size() == 0) {
					if (!pedido.getFuncionarios().contains(f)) {
						pedido.getFuncionarios().add(f);
					}
				} else {
				}
				if (pedido.getFuncionarios() == null) {
					List<Funcionario> funcionarios = new ArrayList<Funcionario>();
					funcionarios.add(f);
					pedido.setFuncionarios(funcionarios);

				}
			}
			if (isEdicaoDePedido()) {
				pedidoService.update(pedido);
			} else {
				pedido.setDataPedido(new Date());
				pedidoService.save(pedido);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pedido '" + pedido.getId() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;

	}

	public boolean isEdicaoDePedido() {
		return pedido != null && pedido.getId() != null;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public LocalPedido[] getLocais() {
		return LocalPedido.values();

	}

	public List<Cliente> getClientes() {
		try {
			return clienteService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

	}

	public boolean isLocalIsMesa() {
		return pedido.getLocal() == LocalPedido.MESA;
	}

	public boolean isLocalIsEntrega() {
		return pedido.getLocal() == LocalPedido.ENTREGA;
	}

	public List<Mesa> getMesas() {
		try {
			return mesaService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

	}

	public String delete(Item item) {
		try {
			itemService.delete(item);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + item.getProduto().getNome() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;

	}
}
