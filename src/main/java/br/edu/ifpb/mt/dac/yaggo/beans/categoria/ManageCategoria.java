package br.edu.ifpb.mt.dac.yaggo.beans.categoria;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.filters.CategoriaFilter;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ManageCategoria extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategoriaFilter categoriaFilter;

	@Inject
	private CategoriaService categoriaService;

	private List<Categoria> categorias;

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}
	public String delete(Categoria categoria) {
		try {
			categoriaService.delete(categoria);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Categoria '" + categoria.getNome() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIA;

	}

	public String filtrar() {
		try {
			categorias = categoriaService.findBy(getCategoriaFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.categoriaFilter = new CategoriaFilter();
		return null;
	}

	public CategoriaFilter getCategoriaFilter() {
		return categoriaFilter;
	}

	public void setCategoriaFilter(CategoriaFilter categoriaFilter) {
		this.categoriaFilter = categoriaFilter;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	
}
