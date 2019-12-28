package br.edu.ifpb.mt.dac.yaggo.beans.produto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ManageProduto extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoFilter ProdutoFilter;

	@Inject
	private ProdutoService ProdutoService;

	private List<Produto> Produtos;

	@Inject
	private CategoriaService categoriaService;

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String delete(Produto Produto) {
		try {
			ProdutoService.delete(Produto);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + Produto.getNome() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;

	}

	public String filtrar() {
		try {
			Produtos = ProdutoService.findBy(getProdutoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.ProdutoFilter = new ProdutoFilter();
		return null;
	}

	public ProdutoFilter getProdutoFilter() {
		return ProdutoFilter;
	}

	public void setProdutoFilter(ProdutoFilter ProdutoFilter) {
		this.ProdutoFilter = ProdutoFilter;
	}

	public List<Produto> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto> Produtos) {
		this.Produtos = Produtos;
	}

	public List<String> getCategorias() {
		try {
			return categoriaService.getAllNomes();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}

}
