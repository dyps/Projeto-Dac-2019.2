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

import br.edu.ifpb.mt.dac.yaggo.dao.FuncionarioDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.filters.FuncionarioFilter;

public class FuncionarioInDatabaseDAO extends InDatabaseDAO implements FuncionarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
		Root<Funcionario> root = criteriaQuery.from(Funcionario.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, filter);
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<Funcionario> typedQuery = em.createQuery(criteriaQuery);

		List<Funcionario> resultado = typedQuery.getResultList();
		return resultado;

//		List<Funcionario> resultado= new ArrayList<Funcionario>();
//		try {
//			
//			
//			String jpql = "SELECT u FROM Funcionario u WHERE 1 = 1 ";
//			
//			// name
//			if (notEmpty(filter.getNome())) {
//				jpql += "AND u.nome LIKE :nome ";
//			}
//			
//			// tipo
//			if (notEmpty(filter.getTipoFuncionario())) {
//				jpql += "AND u.tipo = :tipo ";
//			}
//			
//			// Login
//			if (notEmpty(filter.getLogin())) {
//				jpql += "AND u.login = :login ";
//			}
//			
//			TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
//			
//			//  name
//			if (notEmpty(filter.getNome())) {
//				query.setParameter("nome", "%" + filter.getNome() + "%");
//			}
//			
//			// tipo
//			if (notEmpty(filter.getTipoFuncionario())) {
//				query.setParameter("tipo", filter.getTipoFuncionario());
//			}
//			
//			// Login
//			if (notEmpty(filter.getLogin())) {
//				query.setParameter("login", filter.getLogin());
//			}
//			
//			resultado = query.getResultList();
//		} catch (PersistenceException pe) {
//			pe.printStackTrace();
//			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os funcionarios.", pe);
//		}
//		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Funcionario> root,
			FuncionarioFilter filter) {

		List<Predicate> predicate = new ArrayList<Predicate>();
		if (notEmpty(filter.getLogin())) {
			predicate.add(criteriaBuilder.like(root.get("login"), "%" + filter.getLogin() + "%"));
		}
		if (notEmpty(filter.getNome())) {
			predicate.add(criteriaBuilder.like(root.get("nome"), "%" + filter.getNome() + "%"));
		}
		if (notEmpty(filter.getTipoFuncionario())) {
			predicate.add(criteriaBuilder.equal(root.get("tipo"), filter.getTipoFuncionario()));
		}

		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public Funcionario getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Funcionario resultado = null;
		try {
			resultado = em.find(Funcionario.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o funcionario com base no ID.",
					pe);
		}

		return resultado;
	}

	@Override
	public boolean existeFuncionarioComLogin(Funcionario func) {
		if (empty(func) || empty(func.getLogin())) {
			return false;
		}

		// Usar estratégia de contabilizar quantos usuários existem com o dado login, e
		// que não seja ele mesmo.
		// Existe algum usuário com o login caso a contagem seja diferente de zero.
		// Usar COUNT(*), já que cláusula EXISTS não pode ser usada no SELECT pela BNF
		// do JPQL:
		// https://javaee.github.io/tutorial/persistence-querylanguage006.html

		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Funcionario u WHERE u.login = :login";
		if (notEmpty(func.getId())) {
			jpql += " AND u != :func ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", func.getLogin());
		if (notEmpty(func.getId())) {
			query.setParameter("func", func);
		}

		Long count = query.getSingleResult();
		return count > 0;
	}

	@Override
	public void save(Funcionario funcionario) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(funcionario);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o funcionario.", pe);
		}
	}

	@Override
	public Funcionario update(Funcionario funcionario) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Funcionario resultado = funcionario;
		try {
			resultado = em.merge(funcionario);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o funcionario.", pe);
		}
		return resultado;
	}

	@Override
	public void delete(Funcionario obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Funcionario.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}
	}

	@Override
	public List<Funcionario> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		try {
			TypedQuery<Funcionario> query = em.createQuery("SELECT s FROM Funcionario s", Funcionario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os funcionarios.", pe);
		}
		return resultado;
	}

}
