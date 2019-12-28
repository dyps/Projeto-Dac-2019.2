package br.edu.ifpb.mt.dac.yaggo.filters;

import java.io.Serializable;

import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;


public interface Filter extends Serializable {

	default public void validate() throws ServiceDacException {
		// Não fazer validação alguma por padrão.
	}
	
}
