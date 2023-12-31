package com.example.eloemocional.controllers.exceptions;

import lombok.AllArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ValidationError extends StandardError {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {

    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fielName, String message) {
        this.errors.add(new FieldMessage(fielName, message));
    }
}
