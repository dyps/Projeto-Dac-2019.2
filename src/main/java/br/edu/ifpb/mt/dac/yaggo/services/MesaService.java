package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.filters.MesaFilter;

public interface MesaService {

	Mesa getByID(int id) throws ServiceDacException;

	List<Mesa> findBy(MesaFilter filter) throws ServiceDacException;

	void delete(Mesa Mesa) throws ServiceDacException;

	void save(Mesa Mesa) throws ServiceDacException;

	Mesa update(Mesa Mesa) throws ServiceDacException;

	List<Mesa> getAll() throws ServiceDacException;

}
