package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.PedidoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.filters.PedidoFilter;

public class PedidoInDatabaseDAO extends InDatabaseDAO implements PedidoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Pedido> findBy(PedidoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = new ArrayList<Pedido>();
		try {

			String jpql = "SELECT u FROM Pedido u  ";
			if (notEmpty(filter.getMesa())) {
				jpql += " JOIN u.mesa m ";
			}
			jpql += " WHERE 1=1 ";

			if (notEmpty(filter.getPague())) {
				jpql += " AND u.pague = ";
				boolean pague = filter.getPague();
				if (pague) {
					jpql += "1";

				} else {
					jpql += "0";

				}
			}
			if (notEmpty(filter.getFinalizado())) {
				jpql += " AND u.finalizado = ";
				boolean fin = filter.getFinalizado();
				if (fin) {
					jpql += "1";

				} else {
					jpql += "0";
				}
			}

			if (notEmpty(filter.getMesa())) {
				jpql += " AND m.id = :id";
			}
			TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);

			if (notEmpty(filter.getMesa())) {
				query.setParameter("id", filter.getMesa().getId());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os pedidos.", pe);
		} catch (NumberFormatException pe) {
			throw new PersistenciaDacException("Formato de dado incorreto", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Pedido obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Pedido.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Pedido.", pe);
		}

	}

	@Override
	public Pedido getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pedido resultado = null;
		try {
			resultado = em.find(Pedido.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Pedido com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Pedido Pedido) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Pedido);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Pedido.", pe);
		}
	}

	@Override
	public Pedido update(Pedido Pedido) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pedido resultado = Pedido;
		try {
			resultado = em.merge(Pedido);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a Pedido.", pe);
		}
		return resultado;
	}

	@Override
	public List<Pedido> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pedido> resultado = new ArrayList<Pedido>();
		try {
			TypedQuery<Pedido> query = em.createQuery("SELECT s FROM Pedido s", Pedido.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as Pedidos.", pe);
		}
		return resultado;
	}

}
