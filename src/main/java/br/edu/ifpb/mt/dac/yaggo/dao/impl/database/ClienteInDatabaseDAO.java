package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.dac.yaggo.dao.ClienteDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Cliente;
import br.edu.ifpb.mt.dac.yaggo.filters.ClienteFilter;

public class ClienteInDatabaseDAO extends InDatabaseDAO implements ClienteDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Cliente> findBy(ClienteFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);

		List<Cliente> resultado = typedQuery.getResultList();
		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Cliente> root, ClienteFilter filter) {

		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(root.get("nome"), "%" + filter.getNome() + "%"));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public void delete(Cliente obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Cliente.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a Cliente.", pe);
		}

	}

	@Override
	public Cliente getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Cliente resultado = null;
		try {
			resultado = em.find(Cliente.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a Cliente com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Cliente Cliente) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Cliente);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Cliente.", pe);
		}
	}

	@Override
	public Cliente update(Cliente Cliente) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Cliente resultado = Cliente;
		try {
			resultado = em.merge(Cliente);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a Cliente.", pe);
		}
		return resultado;
	}

	@Override
	public List<Cliente> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Cliente> resultado = new ArrayList<Cliente>();
		try {
			TypedQuery<Cliente> query = em.createQuery("SELECT s FROM Cliente s", Cliente.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as Clientes.", pe);
		}
		return resultado;
	}

	@Override
	public boolean existeClienteComCPF(Cliente cliente) throws PersistenciaDacException {
		if (cliente == null) {
			return false;
		}
		for (Cliente aacliente : this.getAll()) {
			if (aacliente.getId().equals(cliente.getId()))
				continue;

			boolean cpfIgual = aacliente.getCPF().trim().equalsIgnoreCase(cliente.getCPF());
			if (cpfIgual) {
				return true;
			}
		}
		return false;

	}

}
