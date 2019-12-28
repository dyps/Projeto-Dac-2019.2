package br.edu.ifpb.mt.dac.yaggo.dao;

import java.util.List;

import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.filters.FuncionarioFilter;

public interface FuncionarioDAO {

	public List<Funcionario> findBy(FuncionarioFilter filter) throws PersistenciaDacException;

	public Funcionario getByID(Integer id) throws PersistenciaDacException;

	public boolean existeFuncionarioComLogin(Funcionario func);

	public void save(Funcionario funcionario) throws PersistenciaDacException;

	public Funcionario update(Funcionario funcionario) throws PersistenciaDacException;

	public void delete(Funcionario funcionario) throws PersistenciaDacException;

	public List<Funcionario> getAll() throws PersistenciaDacException;

}
