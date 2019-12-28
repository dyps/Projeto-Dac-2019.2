package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.PedidoFilter;

public interface PedidoService {

	Pedido getByID(int id) throws ServiceDacException;

	List<Pedido> getAll() throws ServiceDacException;

	List<Pedido> findBy(PedidoFilter filter) throws ServiceDacException;

	void delete(Pedido Pedido) throws ServiceDacException;

	void save(Pedido Pedido) throws ServiceDacException;

	Pedido update(Pedido Pedido) throws ServiceDacException;

}
