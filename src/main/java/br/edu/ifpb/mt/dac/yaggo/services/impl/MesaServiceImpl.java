package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.MesaDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.filters.MesaFilter;
import br.edu.ifpb.mt.dac.yaggo.services.MesaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class MesaServiceImpl implements Serializable, MesaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private MesaDAO MesaDAO;

	@Override
	public List<Mesa> findBy(MesaFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			
			return MesaDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Mesa Mesa) throws ServiceDacException {
		try {
			MesaDAO.delete(Mesa);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Mesa getByID(int id) throws ServiceDacException {
		try {
			return MesaDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Mesa Mesa) throws ServiceDacException {
		try {
			MesaDAO.save(Mesa);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Mesa update(Mesa Mesa) throws ServiceDacException {
		try {
			return MesaDAO.update(Mesa);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public List<Mesa> getAll() throws ServiceDacException {
		try {
			return MesaDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
