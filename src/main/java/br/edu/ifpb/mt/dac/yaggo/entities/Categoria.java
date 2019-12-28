package br.edu.ifpb.mt.dac.yaggo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// 	Lembrar de criar uma pagina que ao inciar ver primeiramente as subcategorias daquela categoria e apos mostrar os produtos
@Entity
@Table(name = "TB_Categoria")
public class Categoria implements Identificavel {

	
	@Column(unique = true)
	private String nome;

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;

	@OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL)
	private List<Categoria> categoriaFilhos;

	@ManyToOne
	private Categoria categoriaPai;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Categoria> getCategoriaFilhos() {
		return categoriaFilhos;
	}

	public void setCategoriaFilhos(List<Categoria> categoriaFilhos) {
		this.categoriaFilhos = categoriaFilhos;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
 
	@ManyToMany
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((categoriaPai == null) ? 0 : categoriaPai.hashCode());
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
		Categoria other = (Categoria) obj;

		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (categoriaPai == null) {
			if (other.categoriaPai != null)
				return false;
		} else if (!categoriaPai.equals(other.categoriaPai))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + Id + ", nome=" + nome + ", categoriaPai_Id=" + categoriaPai + "]";
	}

	public Categoria clone() {
		Categoria clone = new Categoria();
		clone.setId(Id);
		clone.setNome(nome);
		clone.setCategoriaPai(categoriaPai);
		return clone;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

}
