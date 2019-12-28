package br.edu.ifpb.mt.dac.yaggo.services;

import br.edu.ifpb.mt.dac.yaggo.entities.Telefone;

public interface TelefoneService {

	Telefone getByID(int id) throws ServiceDacException;

	void delete(Telefone Telefone) throws ServiceDacException;

	void save(Telefone Telefone) throws ServiceDacException;

}
