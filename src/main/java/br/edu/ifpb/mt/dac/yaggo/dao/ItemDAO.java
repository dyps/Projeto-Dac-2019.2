package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Item;

public interface ItemDAO {

	void delete(Item item) throws PersistenciaDacException;

	Item getByID(int id) throws PersistenciaDacException;

	void save(Item item) throws PersistenciaDacException;

	Item update(Item item) throws PersistenciaDacException;

	List<Item> getAll() throws PersistenciaDacException;

}
