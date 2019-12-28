package br.edu.ifpb.mt.dac.yaggo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Mesa")
public class Mesa implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	@Column(nullable = false)
	private Integer numero;

	@OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Float getValorTotal() {
		float valorTotal = 0;
		if (getPedidos() != null) {
			if (getPedidos().size() >= 1) {
				for (Pedido pedido : getPedidos()) {
					if (pedido.getItems() != null) {
						valorTotal += pedido.getValorTotal();
					}
				}
			}
		}
		return valorTotal;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		result = prime * result + (numero);
		result = prime * result + (getValorTotal().intValue());
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
		Mesa other = (Mesa) obj;

		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mesa [id=" + Id + ", numero=" + numero + ", valorTotal=" + getValorTotal() + "]";
	}

	public Mesa clone() {
		Mesa clone = new Mesa();
		clone.setId(Id);
		clone.setNumero(numero);
		clone.setPedidos(pedidos);
		return clone;
	}

}
