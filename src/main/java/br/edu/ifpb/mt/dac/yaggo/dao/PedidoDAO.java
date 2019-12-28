package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.PedidoFilter;

public interface PedidoDAO {

	List<Pedido> getAll() throws PersistenciaDacException;

	Pedido update(Pedido pedido) throws PersistenciaDacException;

	void save(Pedido pedido) throws PersistenciaDacException;

	Pedido getByID(int id) throws PersistenciaDacException;

	void delete(Pedido pedido) throws PersistenciaDacException;

	List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException;

}
