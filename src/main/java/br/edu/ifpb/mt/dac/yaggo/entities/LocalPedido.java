package br.edu.ifpb.mt.dac.yaggo.entities;

public enum LocalPedido {
	MESA("Mesa"), ENTREGA("Entrega"), BALCAO("Balcao");
	
	private String nome;

	private LocalPedido(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

}
