package br.edu.ifpb.mt.dac.yaggo.filters;

import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;

public class EnderecoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
