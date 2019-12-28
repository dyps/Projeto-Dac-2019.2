package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.PedidoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.PedidoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.PedidoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class PedidoServiceImpl implements Serializable, PedidoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private PedidoDAO PedidoDAO;

	@Override
	public List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return PedidoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Pedido Pedido) throws ServiceDacException {
		try {
			PedidoDAO.delete(Pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Pedido getByID(int id) throws ServiceDacException {
		try {
			return PedidoDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Pedido Pedido) throws ServiceDacException {
		try {
			PedidoDAO.save(Pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}


	@Override
	@TransacionalCdi
	public Pedido update(Pedido Pedido) throws ServiceDacException {
		try {
			return PedidoDAO.update(Pedido);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public List<Pedido> getAll() throws ServiceDacException {
		try {
			return PedidoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
