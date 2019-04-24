package io.mosip.ivv.dba.exception;

public class PersonaIdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PersonaIdNotFoundException(String message){
		super(message);
	
	}

}
