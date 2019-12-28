package br.edu.ifpb.mt.dac.yaggo.beans.item;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Item;
import br.edu.ifpb.mt.dac.yaggo.entities.Pedido;
import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.filters.ProdutoFilter;
import br.edu.ifpb.mt.dac.yaggo.services.ItemService;
import br.edu.ifpb.mt.dac.yaggo.services.PedidoService;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ItemEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Item item;
	@Inject
	private ItemService itemService;
	private Pedido pedido;
	@Inject
	private PedidoService pedidoService;
	@Inject
	private ProdutoService produtoService;

	public void init() {

		try {
			if (item == null) {
				item = new Item();
				item.setPedido(pedidoService.getByID(pedido.getId()));
			} else {
				item = itemService.getByID(item.getId());
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveItem() {
		try {
			item.setValor(item.getQuantidade()*item.getProduto().getValorUnitario());
			if (isEdicaoDeItem()) {
				itemService.update(item);
			} else {
				itemService.save(item);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Item '" + item.getQuantidade() + " * " + item.getProduto().getNome() + "' saved");

		return EnderecoPaginas.PAGINA_EDIT_PEDIDO + "pedido=" + pedido.getId();

	}

	public boolean isEdicaoDeItem() {
		return item != null && item.getId() != null;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Produto> getAllProdutosDisponiveis() {
		try {
			ProdutoFilter filter = new ProdutoFilter();
			filter.setDisponivel(true);
			return produtoService.findBy(filter);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

	}

}
