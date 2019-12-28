package br.edu.ifpb.mt.dac.yaggo.services;

import br.edu.ifpb.mt.dac.yaggo.DacException;

public class ServiceDacException extends DacException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3082351960302866350L;

	public ServiceDacException(String mensagem) {
		super(mensagem);
	}

	public ServiceDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
