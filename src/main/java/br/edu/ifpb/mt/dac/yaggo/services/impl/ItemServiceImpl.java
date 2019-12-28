package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.ItemDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Item;
import br.edu.ifpb.mt.dac.yaggo.services.ItemService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class ItemServiceImpl implements Serializable, ItemService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private ItemDAO ItemDAO;

	@Override
	@TransacionalCdi
	public void delete(Item Item) throws ServiceDacException {
		try {
			ItemDAO.delete(Item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Item getByID(int id) throws ServiceDacException {
		try {
			return ItemDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Item Item) throws ServiceDacException {
		try {
			ItemDAO.save(Item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Item update(Item Item) throws ServiceDacException {
		try {
			return ItemDAO.update(Item);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public List<Item> getAll() throws ServiceDacException {
		try {
			return ItemDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
