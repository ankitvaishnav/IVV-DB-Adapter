package io.mosip.ivv.dba.exception;

public class PersonaNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PersonaNotFoundException(String message){
		super(message);
	}

}
