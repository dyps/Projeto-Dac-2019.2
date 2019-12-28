package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.CategoriaDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.filters.CategoriaFilter;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class CategoriaServiceImpl implements Serializable, CategoriaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Override
	public List<Categoria> findBy(CategoriaFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return categoriaDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Categoria categoria) throws ServiceDacException {
		try {
			categoriaDAO.delete(categoria);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Categoria getByID(int id) throws ServiceDacException {
		try {
			return categoriaDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Categoria categoria) throws ServiceDacException {
		try {
			categoriaDAO.save(categoria);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Categoria update(Categoria categoria) throws ServiceDacException {
		try {
			return categoriaDAO.update(categoria);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public List<Categoria> getAll() throws ServiceDacException {
		try {
			return categoriaDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Categoria> getByNomes(List<String> categorias) throws ServiceDacException {
		List<Categoria> resultado = new ArrayList<Categoria>();
		for (String string : categorias) {
			CategoriaFilter filter = new CategoriaFilter();
			filter.setNome(string);
			resultado.add(findBy(filter).get(0));
		}
		return resultado;
	}

	@Override
	public List<String> getAllNomes() throws ServiceDacException {
		List<String> lista = new ArrayList<String>();
		for (Categoria categoria : getAll()) {
			lista.add(categoria.getNome());
		}
		return lista;

	}

}
