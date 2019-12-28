package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.filters.CategoriaFilter;

public interface CategoriaService {

	List<Categoria> findBy(CategoriaFilter categoriaFilter) throws ServiceDacException;

	void delete(Categoria categoria) throws ServiceDacException;

	Categoria getByID(int id) throws ServiceDacException;

	void save(Categoria categoria) throws ServiceDacException;

	Categoria update(Categoria categoria) throws ServiceDacException;

	List<Categoria> getAll() throws ServiceDacException;

	List<Categoria> getByNomes(List<String> categorias) throws ServiceDacException;

	List<String> getAllNomes() throws ServiceDacException;
}
