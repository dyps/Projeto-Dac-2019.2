package br.edu.ifpb.mt.dac.yaggo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Item")
public class Item implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	private Integer quantidade;
	private String modificacao;
	private boolean viagem;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Pedido pedido;

	private float valor;

	private void atualizarValor() {
		if (produto != null) {
			valor = quantidade * produto.getValorUnitario();
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		atualizarValor();
	}

	public String getModificacao() {
		return modificacao;
	}

	public void setModificacao(String modificacao) {
		this.modificacao = modificacao;
	}

	public boolean isViagem() {
		return viagem;
	}

	public void setViagem(boolean viagem) {
		this.viagem = viagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		atualizarValor();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float getValor() {
		return valor;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (quantidade);
		result = prime * result + ((modificacao == null) ? 0 : modificacao.hashCode());
		result = prime * result + ((int) valor);
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;

		if (modificacao == null) {
			if (other.modificacao != null)
				return false;
		} else if (!modificacao.equals(other.modificacao))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (!quantidade.equals(other.quantidade))
			return false;
		if (valor!=other.valor)
			return false;
		if (viagem !=other.viagem)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + Id + ", quantidade=" + quantidade 
				+ ", modificacao=" + modificacao 
				+ ", viagem=" + viagem 
				+ ", valor=" + valor 
				+ ", produto=" + produto 
				+ ", pedido=" + pedido 
				+ "]";
	}

	public Item clone() {
		Item clone = new Item();
		clone.setId(Id);
		clone.setModificacao(modificacao);
		clone.setPedido(pedido);
		clone.setProduto(produto);
		clone.setQuantidade(quantidade);
		clone.setValor(valor);
		clone.setViagem(viagem);
		return clone;
	}

	
}
