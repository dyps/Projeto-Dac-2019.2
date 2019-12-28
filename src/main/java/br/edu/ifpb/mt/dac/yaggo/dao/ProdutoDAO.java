package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;

public interface ProdutoDAO {

	void save(Produto obj) throws PersistenciaDacException;

	Produto update(Produto obj) throws PersistenciaDacException;

	void delete(Produto obj) throws PersistenciaDacException;

	Produto getByID(Integer objId) throws PersistenciaDacException;

	List<Produto> getAll() throws PersistenciaDacException;

	List<Produto> findBy(ProdutoFilter filter) throws PersistenciaDacException;

}
