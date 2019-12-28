package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.MesaDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.filters.MesaFilter;

public class MesaInDatabaseDAO extends InDatabaseDAO implements MesaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Mesa> findBy(MesaFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Mesa> resultado = new ArrayList<Mesa>();
		try {

			String jpql = "SELECT u FROM Mesa u  WHERE 1=1";

			if (notEmpty(filter.getNumero())) {
				jpql += " AND u.numero = :numero";
			}
			TypedQuery<Mesa> query = em.createQuery(jpql, Mesa.class);
			if (notEmpty(filter.getNumero())) {
				query.setParameter("numero", filter.getNumero());

			}

			resultado = query.getResultList();

			List<Mesa> remover = new ArrayList<>();
			for (int i = 0; i < resultado.size(); i++) {
				Mesa mesa = resultado.get(i);

				if (notEmpty(filter.getValorTotalMax())) {
					if (mesa.getValorTotal() > Float.parseFloat(filter.getValorTotalMax())) {
						remover.add(mesa);
					}
				}
				if (notEmpty(filter.getValorTotalMin())) {
					if (mesa.getValorTotal() < Float.parseFloat(filter.getValorTotalMin())) {
						remover.add(mesa);
					}
				}
			}
			for (Mesa mesa : remover) {
				resultado.remove(mesa);
			}

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar as mesas.", pe);
		} catch (NumberFormatException pe) {
			throw new PersistenciaDacException("Formato de dado incorreto", pe);
		}
		return resultado;

	}

	@Override
	public void delete(Mesa obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Mesa.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Mesa.", pe);
		}

	}

	@Override
	public Mesa getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Mesa resultado = null;
		try {
			resultado = em.find(Mesa.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Mesa com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Mesa Mesa) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Mesa);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Mesa.", pe);
		}
	}

	@Override
	public Mesa update(Mesa Mesa) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Mesa resultado = Mesa;
		try {
			resultado = em.merge(Mesa);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a Mesa.", pe);
		}
		return resultado;
	}

	@Override
	public List<Mesa> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Mesa> resultado = new ArrayList<Mesa>();
		try {
			TypedQuery<Mesa> query = em.createQuery("SELECT s FROM Mesa s", Mesa.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as Mesas.", pe);
		}
		return resultado;
	}

}
