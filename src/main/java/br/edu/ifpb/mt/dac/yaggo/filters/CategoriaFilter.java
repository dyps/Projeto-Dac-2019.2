package br.edu.ifpb.mt.dac.yaggo.filters;

public class CategoriaFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	private String nomeCategoriaPai;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCategoriaPai() {
		return nomeCategoriaPai;
	}

	public void setNomeCategoriaPai(String nomeCategoriaPai) {
		this.nomeCategoriaPai = nomeCategoriaPai;
	}


	
}
