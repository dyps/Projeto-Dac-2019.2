package br.edu.ifpb.mt.dac.yaggo.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.dac.yaggo.entities.Funcionario;
import br.edu.ifpb.mt.dac.yaggo.services.FuncionarioService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter<Funcionario> {

	@Inject
	private FuncionarioService funcionarioService;

	@Override
	public Funcionario getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return funcionarioService.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Funcionario value) {

		if (!(value instanceof Funcionario)) {
			return null;
		}

		Integer id = ((Funcionario) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
