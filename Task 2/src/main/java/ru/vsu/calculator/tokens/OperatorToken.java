package ru.vsu.calculator.tokens;

import java.util.function.Function;

public class OperatorToken extends Token {
    private final Function<Double[], Double> _function;

    private final OperatorAssociativity _operatorAssociativity;

    private final int _priority;

    private final int _paramsCount;

    public OperatorToken(Function<Double[], Double> function, OperatorAssociativity operatorAssociativity, int priority, int paramsCount) {
        super(TokenType.OPERATOR);
        _function = function;
        _operatorAssociativity = operatorAssociativity;
        _priority = priority;
        _paramsCount = paramsCount;
    }

    public OperatorAssociativity getOperatorAssociativity() {
        return _operatorAssociativity;
    }

    public int getPriority() {
        return _priority;
    }

    public int getParamsCount() {
        return _paramsCount;
    }

    public double apply(Double[] params) {
        if (params.length != _paramsCount)
            throw new IllegalArgumentException();

        return _function.apply(params);
    }
}