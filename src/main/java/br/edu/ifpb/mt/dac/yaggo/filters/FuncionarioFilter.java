package br.edu.ifpb.mt.dac.yaggo.filters;

import br.edu.ifpb.mt.dac.yaggo.entities.TipoFuncionario;

public class FuncionarioFilter implements Filter {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private String login;
	private String nome;
	private TipoFuncionario tipoFuncionario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

}
