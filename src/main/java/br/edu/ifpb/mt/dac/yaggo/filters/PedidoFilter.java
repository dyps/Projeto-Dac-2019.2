package br.edu.ifpb.mt.dac.yaggo.filters;

import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;

public class PedidoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean pague = null;
	private Boolean finalizado = false;

	private Mesa mesa;

	public Boolean getPague() {
		return pague;
	}

	public void setPague(Boolean pague) {
		this.pague = pague;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

}
