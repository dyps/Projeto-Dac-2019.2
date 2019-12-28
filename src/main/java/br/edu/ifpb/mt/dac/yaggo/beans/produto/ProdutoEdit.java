package br.edu.ifpb.mt.dac.yaggo.beans.produto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.DacRuntimeException;
import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ProdutoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Produto Produto;
	@Inject
	private ProdutoService ProdutoService;
	@Inject
	private CategoriaService categoriaService;

	private List<String> categorias;

	public void init() {

		try {
			if (Produto == null) {
				Produto = new Produto();
			} else {
				Produto = ProdutoService.getByID(Produto.getId());
				setCategorias(tostr(Produto.getCategorias()));
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	private List<String> tostr(List<Categoria> list) {
		List<String> lista= new ArrayList<String>();
		for (Categoria categoria : list) {
			lista.add(categoria.getNome());
		}
		return lista;
	}

	public String saveProduto() {
		try {
			Produto.setCategorias(categoriaService.getByNomes(getCategorias()));
			if (isEdicaoDeProduto()) {
				ProdutoService.update(Produto);
			} else {
				ProdutoService.save(Produto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + Produto.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;

	}

	public boolean isEdicaoDeProduto() {
		return Produto != null && Produto.getId() != null;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto Produto) {
		this.Produto = Produto;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public List<String> getAllCategorias() {
		try {
			return categoriaService.getAllNomes();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar as categorias.", e);
		}
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
}
