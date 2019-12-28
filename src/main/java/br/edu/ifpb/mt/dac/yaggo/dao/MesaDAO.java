package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.filters.MesaFilter;

public interface MesaDAO {

	List<Mesa> getAll() throws PersistenciaDacException;

	Mesa update(Mesa mesa) throws PersistenciaDacException;

	void save(Mesa mesa) throws PersistenciaDacException;

	Mesa getByID(int id) throws PersistenciaDacException;

	List<Mesa> findBy(MesaFilter filter) throws PersistenciaDacException;

	void delete(Mesa mesa) throws PersistenciaDacException;

}
