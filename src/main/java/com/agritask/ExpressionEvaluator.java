package com.agritask;

public class ExpressionEvaluator {

    private static final char PLUS = '+';
    private static final char MULTIPLY = '*';
    private static final char WHITESPACE = ' ';

    private String expression;
    private int cursor = -1;
    private char currentChar;

    public ExpressionEvaluator(String expression) {
        this.expression = expression;
    }

    public long evaluate() {
        nextChar();
        long result = parseSummands();
        if (cursor < expression.length()) {
            throw new UnexpectedCharacterException(currentChar , cursor);
        }
        return result;
    }

    private long parseSummands() {
        long value = parseMultiplicands();
        while (true){
            if (isNextChar(PLUS)) {
                value += parseMultiplicands();
            } else {
                return value;
            }
        }
    }

    private long parseMultiplicands() {
        long value = parseNumber();
        while (true){
            if (isNextChar(MULTIPLY)) {
                value *= parseNumber();
            } else {
                return value;
            }
        }
    }

    private long parseNumber() {
        while (currentChar == WHITESPACE) {
            nextChar();
        }
        long result;
        StringBuilder sb = new StringBuilder(currentChar);
        if (currentCharIsNumeric()) {
            while (currentCharIsNumeric()) {
                sb.append(currentChar);
                nextChar();
            }
            result = Long.parseLong(sb.toString());
        } else {
            throw new UnexpectedCharacterException(currentChar , cursor);
        }
        return result;
    }

    private void nextChar() {
        currentChar = (++cursor < expression.length()) ? expression.charAt(cursor) : 0;
    }
    private boolean isNextChar(char expectedChar) {
        while (currentChar == WHITESPACE) {
            nextChar();
        }
        if (currentChar == expectedChar) {
            nextChar();
            return true;
        }
        return false;
    }

    private boolean currentCharIsNumeric() {
        return (currentChar >= '0' && currentChar <= '9') ;
    }
}
