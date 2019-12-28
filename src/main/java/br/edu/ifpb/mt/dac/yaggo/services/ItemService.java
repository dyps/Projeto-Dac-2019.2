package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Item;

public interface ItemService {

	Item getByID(int id) throws ServiceDacException;

	List<Item> getAll() throws ServiceDacException;

	Item update(Item Item) throws ServiceDacException;

	void delete(Item Item) throws ServiceDacException;

	void save(Item Item) throws ServiceDacException;;

}
