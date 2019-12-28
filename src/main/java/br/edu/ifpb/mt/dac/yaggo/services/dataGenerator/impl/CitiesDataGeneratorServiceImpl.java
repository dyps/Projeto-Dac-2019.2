package br.edu.ifpb.mt.dac.yaggo.services.dataGenerator.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.entities.Categoria;
import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.entities.Item;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.entities.TipoFuncionario;
import br.edu.ifpb.mt.dac.yaggo.services.CategoriaService;
import br.edu.ifpb.mt.dac.yaggo.services.FuncionarioService;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.services.dataGenerator.FuncionarioDataGeneratorService;

public class CitiesDataGeneratorServiceImpl implements Serializable, FuncionarioDataGeneratorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190366123863872308L;

	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private CategoriaService categoriaService;
	@Inject
	private ProdutoService produtoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.mt.dac.services.dataGenerator.CitiesDataGeneratorService#
	 * generateData()
	 */
	@Override
	public void generateData() throws ServiceDacException {
		List<Funcionario> states = funcionarioService.getAll();

		if (!states.isEmpty()) {
			return;
		}
		Funcionario funcionario = new Funcionario();
		funcionario.setLogin("yaggo");
		funcionario.setSenha("123");
		funcionario.setNome("yaggo");
		funcionario.setTipo(TipoFuncionario.GERENTE);

		funcionarioService.save(funcionario);
		
		Categoria cate = new Categoria();
		
		cate.setNome("bebida");
		
		categoriaService.save(cate);
		
		Produto produto= new Produto();
		
		produto.setDisponivel(true);
		
		produto.setNome("agua");
		
		produto.setValorUnitario(2);
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(cate);
		produto.setCategorias(categorias);
		
		produtoService.save(produto);
		
		

	}
}
