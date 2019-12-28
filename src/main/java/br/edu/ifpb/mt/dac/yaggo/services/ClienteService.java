package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.filters.ClienteFilter;

public interface ClienteService {

	Cliente getByID(int id) throws ServiceDacException;

	Cliente update(Cliente Cliente) throws ServiceDacException;

	List<Cliente> getAll() throws ServiceDacException;

	void save(Cliente Cliente) throws ServiceDacException;

	void delete(Cliente Cliente) throws ServiceDacException;

	List<Cliente> findBy(ClienteFilter filter) throws ServiceDacException;

}
