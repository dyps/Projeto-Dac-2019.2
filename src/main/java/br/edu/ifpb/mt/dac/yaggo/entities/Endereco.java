package br.edu.ifpb.mt.dac.yaggo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Endereco")
public class Endereco implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	private String apelidoLocal;
	private Integer numero;
	private String rua;
	private String complemento;

	@ManyToOne
	private Cliente cliente;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getApelidoLocal() {
		return apelidoLocal;
	}

	public void setApelidoLocal(String apelidoLocal) {
		this.apelidoLocal = apelidoLocal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelidoLocal == null) ? 0 : apelidoLocal.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		Endereco other = (Endereco) obj;

		if (apelidoLocal == null) {
			if (other.apelidoLocal != null)
				return false;
		} else if (!apelidoLocal.equals(other.apelidoLocal))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + Id + ", apelidoLocal=" + apelidoLocal + ", numero=" + numero + ", rua=" + rua
				+ ", complemento=" + complemento + ", cliente=" + cliente + "]";
	}

	public Endereco clone() {
		Endereco clone = new Endereco();
		clone.setId(Id);
		clone.setApelidoLocal(apelidoLocal);
		clone.setCliente(cliente);
		clone.setComplemento(complemento);
		clone.setNumero(numero);
		clone.setRua(rua);
		return clone;
	}

}
