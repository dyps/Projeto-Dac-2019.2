package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.filters.ClienteFilter;

public interface ClienteDAO {

	List<Cliente> findBy(ClienteFilter filter) throws PersistenciaDacException;

	void delete(Cliente cliente) throws PersistenciaDacException;

	Cliente getByID(int id) throws PersistenciaDacException;

	void save(Cliente cliente) throws PersistenciaDacException;

	Cliente update(Cliente cliente) throws PersistenciaDacException;

	List<Cliente> getAll() throws PersistenciaDacException;

	boolean existeClienteComCPF(Cliente cliente) throws PersistenciaDacException;

}
