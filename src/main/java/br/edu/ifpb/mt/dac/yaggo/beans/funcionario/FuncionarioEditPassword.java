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
public class FuncionarioEditPassword extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5613546003837005628L;

	private Funcionario funcionario;

	private String confirmacaoPasswordAtual;

	private String newPassword;

	@Inject
	private FuncionarioService funcionarioService;

	public String changePassword() {

		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				reportarMensagemDeErro("Your current password is missing or incorrect!");
				return null; // Permanecer na mesma página
			}

			funcionario.setSenha(getNewPassword());
			funcionarioService.update(funcionario, true);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Password sucessfully changed for foncionario '" + funcionario.getNome() + "'");

		return EnderecoPaginas.PAGINA_PRINCIPAL_FUNCIONARIO;
	}

	private boolean senhaAtualConfere() throws ServiceDacException {
		return funcionarioService.senhaConfere(this.funcionario, getConfirmacaoPasswordAtual());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}

	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
