package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;

public interface ProdutoService {

	Produto getByID(int id) throws ServiceDacException;

	List<Produto> getAll() throws ServiceDacException;

	Produto update(Produto Produto) throws ServiceDacException;

	void save(Produto Produto) throws ServiceDacException;

	List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException;

	void delete(Produto Produto) throws ServiceDacException;;

}
