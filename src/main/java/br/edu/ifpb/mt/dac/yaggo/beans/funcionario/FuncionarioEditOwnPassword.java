package br.edu.ifpb.mt.dac.yaggo.beans.funcionario;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;

@Named
@ViewScoped
public class FuncionarioEditOwnPassword extends FuncionarioEditPassword {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7911284084719353784L;
	public void init() {
		Funcionario funcionarioLogado = getFuncionarioLogado();
		setFuncionario(funcionarioLogado);
	}
}
