package br.edu.ifpb.mt.dac.yaggo.beans.endereco;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.services.EnderecoService;

@Named
@ViewScoped
public class EnderecoEdit extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6626090146786982344L;
	private Cliente Cliente;
	@Inject
	private ClienteService ClienteService;
	@Inject
	private EnderecoService EnderecoService;

	private Endereco Endereco;

	public void init() {

		try {
			Endereco = new Endereco();
			Cliente = ClienteService.getByID(Cliente.getId());
			Endereco.setCliente(Cliente);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveCliente() {
		try {
			EnderecoService.save(Endereco);
		} catch (

		ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + Cliente.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_ENDERECO+"cliente="+Cliente.getId();

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

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco Endereco) {
		this.Endereco = Endereco;
	}
	public String delete(Endereco Endereco) {
		try {
			EnderecoService.delete(Endereco);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Endereco '" + Endereco.getNumero() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_ENDERECO+"cliente="+Cliente.getId();
		
	}


	
	
}
