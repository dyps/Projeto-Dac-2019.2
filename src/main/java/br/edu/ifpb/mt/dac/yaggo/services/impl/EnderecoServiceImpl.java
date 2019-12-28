package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.EnderecoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.filters.EnderecoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.EnderecoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class EnderecoServiceImpl implements Serializable, EnderecoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private EnderecoDAO EnderecoDAO;

	@Override
	@TransacionalCdi
	public void delete(Endereco Endereco) throws ServiceDacException {
		try {
			EnderecoDAO.delete(Endereco);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Endereco getByID(int id) throws ServiceDacException {
		try {
			return EnderecoDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Endereco Endereco) throws ServiceDacException {
		try {
			EnderecoDAO.save(Endereco);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Endereco> findBy(EnderecoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return EnderecoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

}
