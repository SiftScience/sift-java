package com.siftscience.exception;

public class MissingFieldException extends FieldException {
    public MissingFieldException(String fieldName) {
        super("Required field \"" + fieldName + "\" is missing.");
    }
}
