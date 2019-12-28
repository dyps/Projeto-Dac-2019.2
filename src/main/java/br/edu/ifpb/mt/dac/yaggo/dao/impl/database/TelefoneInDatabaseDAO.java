package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.TelefoneDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Telefone;

public class TelefoneInDatabaseDAO extends InDatabaseDAO implements TelefoneDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void delete(Telefone obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Telefone.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Telefone.", pe);
		}

	}

	@Override
	public Telefone getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Telefone resultado = null;
		try {
			resultado = em.find(Telefone.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Telefone com base no ID.",
					pe);
		}

		return resultado;
	}

	@Override
	public void save(Telefone Telefone) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Telefone);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Telefone.", pe);
		}
	}


	public List<Telefone> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Telefone> resultado = new ArrayList<Telefone>();
		try {
			TypedQuery<Telefone> query = em.createQuery("SELECT s FROM Telefone s", Telefone.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as Telefones.", pe);
		}
		return resultado;
	}

}
