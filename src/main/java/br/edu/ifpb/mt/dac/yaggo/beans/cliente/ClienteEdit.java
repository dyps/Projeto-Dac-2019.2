package br.edu.ifpb.mt.dac.yaggo.beans.cliente;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.DacRuntimeException;
import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ClienteEdit extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6626090146786982344L;
	private Cliente Cliente;
	@Inject
	private ClienteService ClienteService;

	public void init() {

		try {
			if (Cliente == null) {
				Cliente = new Cliente();
			} else {
				Cliente = ClienteService.getByID(Cliente.getId());
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveCliente() {
		try {
			if (isEdicaoDeCliente()) {
				ClienteService.update(Cliente);
			} else {
				ClienteService.save(Cliente);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + Cliente.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CLIENTE;

	}

	public boolean isEdicaoDeCliente() {
		return Cliente != null && Cliente.getId() != null;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente Cliente) {
		this.Cliente = Cliente;
	}

	public List<Cliente> getAllClientes() {
		try {
			return ClienteService.getAll();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar os Clientes.", e);
		}
	}
}
