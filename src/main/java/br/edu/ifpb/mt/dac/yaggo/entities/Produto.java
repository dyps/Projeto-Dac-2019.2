package br.edu.ifpb.mt.dac.yaggo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Produto")
public class Produto implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	private String nome;
	private float valorUnitario;
	private boolean disponivel = true;

	@OneToMany(mappedBy = "produto")
	private List<Item> items;
	
	
	@ManyToMany
	@Column(nullable = false)
	@JoinTable(name = "tb_categoria_tb_produto", joinColumns = {
			@JoinColumn(name = "produtos_Id") }, inverseJoinColumns = { @JoinColumn(name = "categorias_Id") })
	private List<Categoria> categorias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((int) valorUnitario);
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
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		if (valorUnitario != other.valorUnitario) {
			return false;
		}
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + Id + ", nome=" + nome + ", valorUnitario=" + valorUnitario + ", disponivel="
				+ disponivel + "]";
	}

	public Produto clone() {
		Produto clone = new Produto();
		clone.setId(Id);
		clone.setNome(nome);
		clone.setValorUnitario(valorUnitario);
		clone.setCategorias(categorias);
		clone.setDisponivel(disponivel);
		return clone;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	

}
