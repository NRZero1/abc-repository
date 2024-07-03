package com.phincon.backend.bootcamp.abc_hospital.exception;

public class NoSuchObjectExists extends RuntimeException {
    private String message;

    public NoSuchObjectExists(String message) {
        super(message);
    }
}
