package br.edu.ifpb.mt.dac.yaggo.dao;

import br.edu.ifpb.mt.dac.yaggo.entities.Telefone;

public interface TelefoneDAO {

	void delete(Telefone telefone) throws PersistenciaDacException;

	Telefone getByID(int id) throws PersistenciaDacException;

	void save(Telefone telefone) throws PersistenciaDacException;

}
