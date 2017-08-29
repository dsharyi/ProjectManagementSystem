package com.sharyi_dmytro.practice.module02.Exceptions;

/**
 * Created by nonal on 31.07.2017.
 */
public class MainException extends Exception {

    private String message;

    public MainException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}