package br.edu.ifpb.mt.dac.yaggo.beans.categoria;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.DacRuntimeException;
import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class CategoriaEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Categoria categoria;
	@Inject
	private CategoriaService categoriaService;

	public void init() {
		
		try {
			if (categoria == null) {
				categoria = new Categoria();
			} else {
				categoria = categoriaService.getByID(categoria.getId());
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveCategoria() {
		try {
			if (isEdicaoDeCategoria()) {
				categoriaService.update(categoria);
			} else {
				categoriaService.save(categoria);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Categoria '" + categoria.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIA;

	}
	public boolean isEdicaoDeCategoria() {
		return categoria != null && categoria.getId() != null;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getAllCategorias() {
		try {
			List<Categoria> cat = categoriaService.getAll();
			if (isEdicaoDeCategoria()) {
				cat.remove(categoria);
			}
			return cat;
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar as categorias.", e);
		}
	}
}
