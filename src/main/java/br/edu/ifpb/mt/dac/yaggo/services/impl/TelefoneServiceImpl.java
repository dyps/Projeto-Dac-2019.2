package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.TelefoneDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Telefone;
import br.edu.ifpb.mt.dac.yaggo.services.TelefoneService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class TelefoneServiceImpl implements Serializable, TelefoneService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private TelefoneDAO TelefoneDAO;


	@Override
	@TransacionalCdi
	public void delete(Telefone Telefone) throws ServiceDacException {
		try {
			TelefoneDAO.delete(Telefone);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Telefone getByID(int id) throws ServiceDacException {
		try {
			return TelefoneDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Telefone Telefone) throws ServiceDacException {
		try {
			TelefoneDAO.save(Telefone);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
