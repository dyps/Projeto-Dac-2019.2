package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.ItemDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Item;

public class ItemInDatabaseDAO extends InDatabaseDAO implements ItemDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void delete(Item obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Item.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Item.", pe);
		}

	}

	@Override
	public Item getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = null;
		try {
			resultado = em.find(Item.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Item com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Item Item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Item.", pe);
		}
	}

	@Override
	public Item update(Item Item) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Item resultado = Item;
		try {
			resultado = em.merge(Item);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a Item.", pe);
		}
		return resultado;
	}

	@Override
	public List<Item> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Item> resultado = new ArrayList<Item>();
		try {
			TypedQuery<Item> query = em.createQuery("SELECT s FROM Item s", Item.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as Items.", pe);
		}
		return resultado;
	}

}
