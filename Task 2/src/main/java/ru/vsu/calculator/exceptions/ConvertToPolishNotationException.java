package ru.vsu.calculator.exceptions;

public class ConvertToPolishNotationException extends Exception {
    public ConvertToPolishNotationException() {
    }

    public ConvertToPolishNotationException(String message) {
        super(message);
    }
}