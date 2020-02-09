package com.agritask;

public class UnexpectedCharacterException extends RuntimeException {

    public UnexpectedCharacterException(char ch, int index) {
        super("Unexpected character: " + ch + " at position " + index);
    }
}
