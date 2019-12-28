package br.edu.ifpb.mt.dac.yaggo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Pedido")
public class Pedido implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;

	@Enumerated(EnumType.STRING)
	private LocalPedido local;
	private Date dataPedido;
	private Date dataEntregue;
	private boolean pague = false;
	private boolean finalizado = false;

	@ManyToOne
	private Endereco endereco;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	private float valorTotal;

	private Integer valorEntrega;

	@ManyToOne
	private Cliente cliente;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_funcionario_tb_pedido", joinColumns = {
			@JoinColumn(name = "funcionarios_Id") }, inverseJoinColumns = { @JoinColumn(name = "pedidos_Id") })
	private List<Funcionario> funcionarios;

	@ManyToOne
	private Mesa mesa;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<Item> items;

	public LocalPedido getLocal() {
		return local;
	}

	public void setLocal(LocalPedido local) {
		this.local = local;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntregue() {
		return dataEntregue;
	}

	public void setDataEntregue(Date dataEntregue) {
		this.dataEntregue = dataEntregue;
	}

	public boolean isPague() {
		return pague;
	}

	public void setPague(boolean pague) {
		this.pague = pague;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getValorEntrega() {
		return valorEntrega;
	}

	public void setValorEntrega(Integer valorEntrega) {
		this.valorEntrega = valorEntrega;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((int) valorTotal);
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + ((dataEntregue == null) ? 0 : dataEntregue.hashCode());
		result = prime * result + ((valorEntrega == null) ? 0 : valorEntrega.hashCode());
		result = prime * result + ((mesa == null) ? 0 : mesa.hashCode());
		result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
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
		Pedido other = (Pedido) obj;

		if (dataEntregue == null) {
			if (other.dataEntregue != null)
				return false;
		} else if (!dataEntregue.equals(other.dataEntregue))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (funcionarios == null) {
			if (other.funcionarios != null)
				return false;
		} else if (!funcionarios.equals(other.funcionarios))
			return false;
		if (valorTotal != other.valorTotal)
			return false;
		if (valorEntrega != other.valorEntrega)
			return false;
		if (local != other.local)
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
		return "Pedido [id=" + Id + ", valorTotal=" + valorTotal + ", local=" + local + ", dataPedido=" + dataPedido
				+ ", dataEntregue=" + dataEntregue + ", pague=" + pague + ", finalizado=" + finalizado
				+ ", valorEntrega=" + valorEntrega + ", cliente=" + cliente + ", mesa=" + mesa + "]";
	}

	public Pedido clone() {
		Pedido clone = new Pedido();
		clone.setId(Id);
		clone.setCliente(cliente);
		clone.setDataEntregue(dataEntregue);
		clone.setDataPedido(dataPedido);
		clone.setFinalizado(finalizado);
		clone.setFuncionarios(funcionarios);
		clone.setLocal(local);
		clone.setMesa(mesa);
		clone.setPague(pague);
		clone.setValorEntrega(valorEntrega);
		clone.setValorTotal(valorTotal);
		return clone;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
