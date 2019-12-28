package br.edu.ifpb.mt.dac.yaggo.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.entities.Produto;
import br.edu.ifpb.mt.dac.yaggo.services.ProdutoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {

	@Inject
	private ProdutoService ProdutoService;

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return ProdutoService.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto value) {

		if (!(value instanceof Produto)) {
			return null;
		}

		Integer id = ((Produto) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
