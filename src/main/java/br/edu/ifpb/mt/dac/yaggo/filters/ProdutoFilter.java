package br.edu.ifpb.mt.dac.yaggo.filters;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

public class ProdutoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	private String valorMaxUnitario;

	private String valorMinUnitario;

	private boolean disponivel = true;

	private List<String> categorias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValorMaxUnitario() {
		return valorMaxUnitario;
	}

	public void setValorMaxUnitario(String valorMaxUnitario) {
		this.valorMaxUnitario = valorMaxUnitario;
	}

	public String getValorMinUnitario() {
		return valorMinUnitario;
	}

	public void setValorMinUnitario(String valorMinUnitario) {
		this.valorMinUnitario = valorMinUnitario;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public void validate() throws ServiceDacException {

		if (valorMaxUnitario != null && !valorMaxUnitario.trim().isEmpty() && valorMinUnitario != null
				&& !valorMinUnitario.trim().isEmpty()) {
			try {
				float max = Float.parseFloat(valorMaxUnitario);
				float min = Float.parseFloat(valorMinUnitario);
				if (max < min) {
					throw new ServiceDacException("O valor minimo nao pode ser maior que o valor maximo!");
				}
			} catch (Exception e) {
				throw new ServiceDacException("Os valores minimo e maximo de vem conter numeros(50.0)!");

			}
		}

	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

}
