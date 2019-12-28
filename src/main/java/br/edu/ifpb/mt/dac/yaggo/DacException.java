package br.edu.ifpb.mt.dac.yaggo;

public class DacException extends Exception {

	private static final long serialVersionUID = 1L;

	public DacException(String mensagem) {
		super(mensagem);
	}

	public DacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
