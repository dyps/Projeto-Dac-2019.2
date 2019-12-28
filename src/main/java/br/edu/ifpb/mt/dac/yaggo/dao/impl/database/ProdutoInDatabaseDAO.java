package br.edu.ifpb.mt.dac.yaggo.dao.impl.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.yaggo.dao.ProdutoDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;

public class ProdutoInDatabaseDAO extends InDatabaseDAO implements ProdutoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Produto> findBy(ProdutoFilter filter) throws PersistenciaDacException {

		EntityManager em = getEntityManager();
		List<Produto> resultado = new ArrayList<Produto>();
		try {
			
			String jpql = "SELECT u FROM Produto u JOIN u.categorias c WHERE ";
			
			String outrasConsultas = " 1 = 1 ";
			// name
			if (notEmpty(filter.getNome())) {
				outrasConsultas += " AND u.nome LIKE :nome";
			}
			if (notEmpty(filter.getValorMaxUnitario())) {
				outrasConsultas += " AND u.valorUnitario <= :maxvalor ";
			}
			if (notEmpty(filter.getValorMinUnitario())) {
				outrasConsultas += " AND u.valorUnitario >= :minvalor ";
			}
			if (notEmpty(filter.isDisponivel())) {
				outrasConsultas += " AND u.disponivel = ";
				boolean disp = filter.isDisponivel();
				if (disp) {
					outrasConsultas += "1";
					
				} else {
					outrasConsultas += "0";
					
				}
			}
			jpql += outrasConsultas;
			if (notEmpty(filter.getCategorias())) {
				if (filter.getCategorias().size() >= 1) {
					for (int i = 0; i < filter.getCategorias().size(); i++) {
						String categoria = filter.getCategorias().get(i);
						if (i != 0) {
							jpql += " OR " + outrasConsultas;
						}
						jpql += " AND c.nome = '" + categoria + "' ";
						
					}
				}
			}
			
			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
			
			if (notEmpty(filter.getValorMaxUnitario())) {
				query.setParameter("maxvalor", Float.valueOf(filter.getValorMaxUnitario()));
				
			}
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
				
			}
			if (notEmpty(filter.getValorMinUnitario())) {
				query.setParameter("minvalor", Float.valueOf(filter.getValorMinUnitario()));
			}
			
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os produtos.", pe);
		} catch (NumberFormatException pe) {
			throw new PersistenciaDacException("Formato de dado incorreto", pe);
		}
		resultado = tirarRepetidos(resultado);
		return resultado;

	}

	private List<Produto> tirarRepetidos(List<Produto> resultado) {
		for (Produto produto : resultado) {
			int cont = 0;
			for (Produto produto2 : resultado) {
				if (produto.equals(produto2)) {
					cont++;
				}
			}
			if (cont > 1) {
				resultado.remove(produto);
				return tirarRepetidos(resultado);
			}
		}
		return resultado;
	}

	@Override
	public void delete(Produto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			obj = em.find(Produto.class, obj.getId());
			em.remove(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o Produto.", pe);
		}

	}

	@Override
	public Produto getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Produto resultado = null;
		try {
			resultado = em.find(Produto.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o Produto com base no ID.", pe);
		}

		return resultado;
	}

	@Override
	public void save(Produto Produto) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(Produto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o Produto.", pe);
		}
	}

	@Override
	public Produto update(Produto Produto) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Produto resultado = Produto;
		try {
			resultado = em.merge(Produto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o Produto.", pe);
		}
		return resultado;
	}

	@Override
	public List<Produto> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Produto> resultado = new ArrayList<Produto>();
		try {
			TypedQuery<Produto> query = em.createQuery("SELECT s FROM Produto s", Produto.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todas os Produtos.", pe);
		}
		return resultado;
	}

}
