package com.phincon.backend.bootcamp.abc_hospital.exception;

public class ObjectAlreadyExists extends RuntimeException {
    private String message;

    public ObjectAlreadyExists(String message) {
        super(message);
    }
}
