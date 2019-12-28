package br.edu.ifpb.mt.dac.yaggo.beans;

public final class EnderecoPaginas {
	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}

	private static final String REDIRECT_TRUE = "?faces-redirect=true";

	public static final String PAGINA_PRINCIPAL_FUNCIONARIO = "/paginas/protegidas/funcionario/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_CATEGORIA = "/paginas/protegidas/categoria/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_PRODUTO = "/paginas/protegidas/produto/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_CLIENTE = "/paginas/protegidas/cliente/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_TELEFONE = "/paginas/protegidas/cliente/gerente/cliente_edit_Telefones.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_ENDERECO = "/paginas/protegidas/cliente/gerente/cliente_edit_Enderecos.xhtml"
			+ REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_PEDIDO = "/paginas/protegidas/pedido/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_MESA = "/paginas/protegidas/mesa/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_ITEM = "/paginas/protegidas/item/gerente/item_edit.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_EDIT_PEDIDO = "/paginas/protegidas/pedido/gerente/pedido_edit.xhtml" + REDIRECT_TRUE;
}
