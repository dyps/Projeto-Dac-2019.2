package br.edu.ifpb.mt.dac.yaggo.services.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.dao.FuncionarioDAO;
import br.edu.ifpb.mt.dac.yaggo.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.filters.FuncionarioFilter;
import br.edu.ifpb.mt.dac.yaggo.services.FuncionarioService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.util.TransacionalCdi;

public class FuncionarioServiceImpl implements Serializable, FuncionarioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5853558795440687657L;
	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Override
	public List<Funcionario> findBy(FuncionarioFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return funcionarioDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Funcionario funcionario) throws ServiceDacException {
		try {
			// Verificar se login já existe
			validarLogin(funcionario);
			calcularHashDaSenha(funcionario);
			funcionarioDAO.save(funcionario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	public void validarLogin(Funcionario func) throws ServiceDacException {
		boolean jahExiste = funcionarioDAO.existeFuncionarioComLogin(func);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + func.getLogin());
		}
	}

	private String calcularHashDaSenha(Funcionario funcionario) throws ServiceDacException {
		funcionario.setSenha(hash(funcionario.getSenha()));
		return funcionario.getSenha();
	}

	@Override
	public boolean senhaConfere(Funcionario funcionario, String supostaSenha) throws ServiceDacException {

		// Recuperar verdadeira senha atual (hash)
		String senhaHash = null;
		try {
			senhaHash = funcionarioDAO.getByID(funcionario.getId()).getSenha();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

		// Programação defensiva contra NPE
		if (senhaHash == null && supostaSenha == null) {
			return true;
		}

		if (senhaHash == null || supostaSenha == null) {
			return false;
		}

		// Comparar hash da suposta senha com o verdadeiro hash da senha
		String supostaSenhaHash = hash(supostaSenha);

		return senhaHash.equals(supostaSenhaHash);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException lançada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

	@Override
	public Funcionario getByID(int funcId) throws ServiceDacException {
		try {
			return funcionarioDAO.getByID(funcId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Funcionario update(Funcionario funcionario, boolean passwordChanged) throws ServiceDacException {
		try {
			// Verificar se login já existe
			validarLogin(funcionario);
			if (passwordChanged) {
				calcularHashDaSenha(funcionario);
			}
			return funcionarioDAO.update(funcionario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Funcionario funcionario) throws ServiceDacException {
		try {
			funcionarioDAO.delete(funcionario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Funcionario> getAll() throws ServiceDacException {
		try {
			return funcionarioDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
