package br.edu.ifpb.mt.dac.yaggo.entities;


public enum TipoFuncionario {
	GARCOM("Garcom"), COZINHEIRO("Cozinheiro"), GERENTE("Gerente");
	
	private String nome;

	private TipoFuncionario(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
}
