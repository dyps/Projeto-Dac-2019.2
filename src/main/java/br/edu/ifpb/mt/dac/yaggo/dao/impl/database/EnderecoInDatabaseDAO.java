package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.EnderecoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.filters.EnderecoFilter;

public class EnderecoInDatabaseDAO extends InDatabaseDAO implements EnderecoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void delete(Endereco obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Endereco.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Endereco.", pe);
		}

	}

	@Override
	public Endereco getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Endereco resultado = null;
		try {
			resultado = em.find(Endereco.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Endereco com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Endereco Endereco) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Endereco);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Endereco.", pe);
		}
	}

	@Override
	public List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Endereco> resultado = new ArrayList<Endereco>();
		try {

			String jpql = "SELECT u FROM Endereco u JOIN u.cliente c WHERE  1 = 1 ";
			if (notEmpty(filter.getCliente())) {
				jpql += " AND c.id = :id";
			}

			TypedQuery<Endereco> query = em.createQuery(jpql, Endereco.class);

			if (notEmpty(filter.getCliente())) {
				query.setParameter("id", filter.getCliente().getId());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os produtos.", pe);
		} catch (NumberFormatException pe) {
			throw new PersistenciaDacException("Formato de dado incorreto", pe);
		}
		return resultado;

	}

}
