package br.edu.ifpb.mt.dac.yaggo.beans.cliente;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.filters.ClienteFilter;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ManageCliente extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClienteFilter ClienteFilter;

	@Inject
	private ClienteService ClienteService;

	private List<Cliente> Clientes;

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}
	public String delete(Cliente Cliente) {
		try {
			ClienteService.delete(Cliente);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Cliente '" + Cliente.getNome() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_CLIENTE;

	}

	public String filtrar() {
		try {
			Clientes = ClienteService.findBy(getClienteFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.ClienteFilter = new ClienteFilter();
		return null;
	}

	public ClienteFilter getClienteFilter() {
		return ClienteFilter;
	}

	public void setClienteFilter(ClienteFilter ClienteFilter) {
		this.ClienteFilter = ClienteFilter;
	}

	public List<Cliente> getClientes() {
		return Clientes;
	}

	public void setClientes(List<Cliente> Clientes) {
		this.Clientes = Clientes;
	}

	
}
