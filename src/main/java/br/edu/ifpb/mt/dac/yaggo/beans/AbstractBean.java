package br.edu.ifpb.mt.dac.yaggo.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.entities.TipoFuncionario;
import br.edu.ifpb.mt.dac.yaggo.filters.FuncionarioFilter;
import br.edu.ifpb.mt.dac.yaggo.services.FuncionarioService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5952781944164272602L;
	@Inject
	private FuncionarioService funcionarioService;

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}

	private void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isFuncionarioInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);

	}

	public String getFuncionarioLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal funcPrincipal = externalContext.getUserPrincipal();
		if (funcPrincipal == null) {
			return "";
		}

		return funcPrincipal.getName();
	}

	public Funcionario getFuncionarioLogado() {
		FuncionarioFilter filter = new FuncionarioFilter();
		filter.setLogin(getFuncionarioLogin());
		List<Funcionario> funcs = null;
		try {
			funcs = funcionarioService.findBy(filter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o funcionario logado!");
		}

		if (!funcs.isEmpty()) {
			return funcs.get(0);
		}

		return null;
	}

	public TipoFuncionario[] getTipoFuncionarios() {
		return TipoFuncionario.values();
	}

}
