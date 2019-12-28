package br.edu.ifpb.mt.dac.yaggo.filters;

public class MesaFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer numero;
	private String valorTotalMax;
	private String valorTotalMin;

	public Integer getNumero() {
		return numero;
	}

	public String getValorTotalMax() {
		return valorTotalMax;
	}

	public void setValorTotalMax(String valorTotalMax) {
		this.valorTotalMax = valorTotalMax;
	}

	public String getValorTotalMin() {
		return valorTotalMin;
	}

	public void setValorTotalMin(String valorTotalMin) {
		this.valorTotalMin = valorTotalMin;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}


}
