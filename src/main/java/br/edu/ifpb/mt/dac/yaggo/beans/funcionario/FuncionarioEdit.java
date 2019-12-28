package br.edu.ifpb.mt.dac.yaggo.beans.funcionario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.services.FuncionarioService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class FuncionarioEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Funcionario funcionario;
	@Inject
	private FuncionarioService funcionarioService;

	public void init() {

		try {
			if (funcionario == null) {
				funcionario = new Funcionario();
			} else {
				funcionario = funcionarioService.getByID(funcionario.getId());
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String saveFuncionario() {
		try {
			if (isEdicaoDeFuncionario()) {
				funcionarioService.update(funcionario, false);
			} else {
				funcionarioService.save(funcionario);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_FUNCIONARIO;

	}

	public boolean isEdicaoDeFuncionario() {
		return funcionario != null && funcionario.getId() != null;
	}

	public void checarDisponibilidadeLogin() {
		try {
			funcionarioService.validarLogin(funcionario);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", funcionario.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

}
