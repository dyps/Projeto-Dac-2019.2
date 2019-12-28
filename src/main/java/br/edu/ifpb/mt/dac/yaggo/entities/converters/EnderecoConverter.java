package br.edu.ifpb.mt.dac.yaggo.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.entities.Endereco;
import br.edu.ifpb.mt.dac.yaggo.services.EnderecoService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter<Endereco> {

	@Inject
	private EnderecoService EnderecoService;

	@Override
	public Endereco getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return EnderecoService.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Endereco value) {

		if (!(value instanceof Endereco)) {
			return null;
		}

		Integer id = ((Endereco) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
