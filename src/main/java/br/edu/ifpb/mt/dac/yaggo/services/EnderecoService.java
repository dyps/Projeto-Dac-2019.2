package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.filters.EnderecoFilter;

public interface EnderecoService {

	Endereco getByID(int id) throws ServiceDacException;

	void save(Endereco Endereco) throws ServiceDacException;

	void delete(Endereco Endereco) throws ServiceDacException;

	List<Endereco> findBy(EnderecoFilter enderecoFilter) throws ServiceDacException;

}
