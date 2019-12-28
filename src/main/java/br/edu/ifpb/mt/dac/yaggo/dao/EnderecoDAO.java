package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.filters.EnderecoFilter;

public interface EnderecoDAO {

	void save(Endereco endereco) throws PersistenciaDacException;

	Endereco getByID(int id) throws PersistenciaDacException;

	void delete(Endereco endereco) throws PersistenciaDacException;

	List<Endereco> findBy(EnderecoFilter filter) throws PersistenciaDacException;

}
