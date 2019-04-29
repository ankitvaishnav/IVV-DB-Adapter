package io.mosip.ivv.dba.exception;

public class GlobalIdNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GlobalIdNotFound(String message){
        super(message);

    }
}
