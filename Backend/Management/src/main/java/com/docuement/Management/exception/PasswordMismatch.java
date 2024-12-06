package com.docuement.Management.exception;

public class PasswordMismatch extends RuntimeException{
    public PasswordMismatch(String message) {
        super(message);
    }
}
