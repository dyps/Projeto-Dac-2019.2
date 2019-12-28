package br.edu.ifpb.mt.dac.yaggo.beans.telefone;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.entities.Telefone;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.services.TelefoneService;

@Named
@ViewScoped
public class TelefoneEdit extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6626090146786982344L;
	private Cliente Cliente;
	@Inject
	private ClienteService ClienteService;
	@Inject
	private TelefoneService telefoneService;

	private Telefone telefone;

	public void init() {

		try {
			telefone = new Telefone();
			Cliente = ClienteService.getByID(Cliente.getId());
			telefone.setCliente(Cliente);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveCliente() {
		try {
			telefoneService.save(telefone);
		} catch (

		ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + Cliente.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_TELEFONE + "cliente=" + Cliente.getId();

	}

	public boolean isEdicaoDeCliente() {
		return Cliente != null && Cliente.getId() != null;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String delete(Telefone Telefone) {
		try {
			telefoneService.delete(Telefone);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Telefone '" + Telefone.getNumero() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_TELEFONE + "cliente=" + Cliente.getId();

	}

}
