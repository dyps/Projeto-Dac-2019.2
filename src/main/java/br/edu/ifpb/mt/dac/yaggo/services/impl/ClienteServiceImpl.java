package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.edu.ifpb.mt.dac.yaggo.dao.ClienteDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.filters.ClienteFilter;
import br.edu.ifpb.mt.dac.yaggo.services.ClienteService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class ClienteServiceImpl implements Serializable, ClienteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315031303377932680L;

	@Inject
	private ClienteDAO ClienteDAO;

	@Override
	public List<Cliente> findBy(ClienteFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return ClienteDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Cliente Cliente) throws ServiceDacException {
		try {
			ClienteDAO.delete(Cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public Cliente getByID(int id) throws ServiceDacException {
		try {
			return ClienteDAO.getByID(id);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Cliente Cliente) throws ServiceDacException {
		try {
			verificarCPF(Cliente);
			ClienteDAO.save(Cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Cliente update(Cliente Cliente) throws ServiceDacException {
		try {
			verificarCPF(Cliente);
			return ClienteDAO.update(Cliente);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	private void verificarCPF(Cliente cliente) throws ServiceDacException {
		try {
			if (cliente.getCPF() != null && !cliente.getCPF().trim().isEmpty()) {
				CPFValidator cpfValidator = new CPFValidator();
				List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cliente.getCPF());
				if (erros.size() > 0) {
					throw new ServiceDacException("Cpf Invalido: " + cliente.getCPF());
				}

				boolean jahExiste;
				jahExiste = ClienteDAO.existeClienteComCPF(cliente);
				if (jahExiste) {
					throw new ServiceDacException("Cpf ja existente: " + cliente.getCPF());
				}
			}
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public List<Cliente> getAll() throws ServiceDacException {
		try {
			return ClienteDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
