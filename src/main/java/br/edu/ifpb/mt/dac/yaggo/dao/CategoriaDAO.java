package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.filters.CategoriaFilter;

public interface CategoriaDAO {

	List<Categoria> findBy(CategoriaFilter filter) throws PersistenciaDacException;

	void delete(Categoria categoria) throws PersistenciaDacException;

	Categoria getByID(int id) throws PersistenciaDacException;

	void save(Categoria categoria) throws PersistenciaDacException;

	Categoria update(Categoria categoria) throws PersistenciaDacException;

	List<Categoria> getAll() throws PersistenciaDacException;

}
