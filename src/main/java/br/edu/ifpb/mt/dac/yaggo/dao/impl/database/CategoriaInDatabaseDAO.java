package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.CategoriaDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.filters.CategoriaFilter;

public class CategoriaInDatabaseDAO extends InDatabaseDAO implements CategoriaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Categoria> findBy(CategoriaFilter filter) throws PersistenciaDacException {
		List<Categoria> resultadototal = getAll();
		List<Categoria> resultadoprinc = new ArrayList<Categoria>();
		boolean foifiltrado = false;
		for (int i = 0; i < resultadototal.size(); i++) {
			Categoria categoria = resultadototal.get(i);
			if (notEmpty(filter.getNome())) {
				foifiltrado= true;
				if (categoria.getNome().contains(filter.getNome())) {
					resultadoprinc.add(categoria);
				}
			}
			if (notEmpty(filter.getNomeCategoriaPai())) {
				foifiltrado= true;
				for (int j = 0; j < resultadototal.size(); j++) {
					Categoria pai = resultadototal.get(j);
					if (pai.getNome().contains(filter.getNomeCategoriaPai())){
						if (!resultadoprinc.contains(categoria)&& categoria.getCategoriaPai()!=null) {
							resultadoprinc.add(categoria);
						}
					}
				}
			}

		}
		if (!foifiltrado) {
			resultadoprinc=resultadototal;
		}

		return resultadoprinc;
	}

	@Override
	public void delete(Categoria obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Categoria.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a categoria.", pe);
		}

	}

	@Override
	public Categoria getByID(int id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Categoria resultado = null;
		try {
			resultado = em.find(Categoria.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a categoria com base no ID.",
					pe);
		}

		return resultado;
	}

	@Override
	public void save(Categoria Categoria) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Categoria);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Categoria.", pe);
		}
	}

	@Override
	public Categoria update(Categoria Categoria) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Categoria resultado = Categoria;
		try {
			resultado = em.merge(Categoria);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a Categoria.", pe);
		}
		return resultado;
	}

	@Override
	public List<Categoria> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Categoria> resultado = new ArrayList<Categoria>();
		try {
			TypedQuery<Categoria> query = em.createQuery("SELECT s FROM Categoria s", Categoria.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas as categorias.", pe);
		}
		return resultado;
	}

}
