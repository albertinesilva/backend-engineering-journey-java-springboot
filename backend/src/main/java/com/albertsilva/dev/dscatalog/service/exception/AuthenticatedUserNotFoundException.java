package com.albertsilva.dev.dscatalog.service.exception;

public class AuthenticatedUserNotFoundException extends RuntimeException {

    public AuthenticatedUserNotFoundException(String message) {
        super(message);
    }
}
