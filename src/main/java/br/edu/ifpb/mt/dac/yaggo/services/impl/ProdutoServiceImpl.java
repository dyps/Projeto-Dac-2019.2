package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.ProdutoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class ProdutoServiceImpl implements Serializable, ProdutoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;
	
	@Inject
	private ProdutoDAO ProdutoDAO;

	@Override
	public List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return ProdutoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Produto Produto) throws ServiceDacException {
		try {
			ProdutoDAO.delete(Produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
		
	}

	@Override
	public Produto getByID(int id) throws ServiceDacException {
		try {
			return ProdutoDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Produto Produto) throws ServiceDacException {
		try {
			ProdutoDAO.save(Produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Produto update(Produto Produto) throws ServiceDacException {
		try {
			return ProdutoDAO.update(Produto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
		
	}

	@Override
	public List<Produto> getAll() throws ServiceDacException {
		try {
			return ProdutoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
