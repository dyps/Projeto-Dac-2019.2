package br.edu.ifpb.mt.dac.yaggo.services;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.filters.FuncionarioFilter;

public interface FuncionarioService {

	public List<Funcionario> findBy(FuncionarioFilter filter) throws ServiceDacException;

	public void save(Funcionario funcionario) throws ServiceDacException;

	boolean senhaConfere(Funcionario funcionario, String supostaSenha) throws ServiceDacException;

	Funcionario getByID(int funcId) throws ServiceDacException;

	Funcionario update(Funcionario funcionario, boolean passwordChanged) throws ServiceDacException;

	public void validarLogin(Funcionario funcionario) throws ServiceDacException;

	public void delete(Funcionario funcionario) throws ServiceDacException;

	public List<Funcionario> getAll() throws ServiceDacException;

}
