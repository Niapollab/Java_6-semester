package ru.vsu.calculator.parsing;

import java.util.*;
import java.util.function.*;
import ru.vsu.calculator.tokens.*;

public class OperatorTokenBuilderFactory implements TokenBuilderFactory {
    private final static Map<Character, OperatorToken> OperatorsList;

    static {
        OperatorsList = new HashMap<Character, OperatorToken>();
        OperatorsList.put('+', new OperatorToken(new Function<Double[], Double>() {
            @Override
            public Double apply(Double[] t) {
                return t[0] + t[1];
            }
        }, OperatorAssociativity.LEFT, 1, 2));
        OperatorsList.put('-', new OperatorToken(new Function<Double[], Double>() {
            @Override
            public Double apply(Double[] t) {
                return t[0] - t[1];
            }
        }, OperatorAssociativity.LEFT, 1, 2));
        OperatorsList.put('*', new OperatorToken(new Function<Double[], Double>() {
            @Override
            public Double apply(Double[] t) {
                return t[0] * t[1];
            }
        }, OperatorAssociativity.LEFT, 2, 2));
        OperatorsList.put('/', new OperatorToken(new Function<Double[], Double>() {
            @Override
            public Double apply(Double[] t) {
                return t[0] / t[1];
            }
        }, OperatorAssociativity.LEFT, 2, 2));
        OperatorsList.put('^', new OperatorToken(new Function<Double[], Double>() {
            @Override
            public Double apply(Double[] t) {
                return Math.pow(t[0], t[1]);
            }
        }, OperatorAssociativity.RIGHT, 3, 2));
    }

    @Override
    public TokenBuilder createBuilder(int startPosition, String expression) {
        return new OperatorTokenBuilder(startPosition, expression);
    }

    private class OperatorTokenBuilder extends TokenBuilder {
        public OperatorTokenBuilder(int startPosition, String expression) {
            super(startPosition, expression);
        }

        @Override
        protected void internalPrepare() {
            if (_endPosition >= _expression.length())
                return;

            _endPosition += OperatorsList.containsKey(_expression.charAt(_endPosition)) ? 1 : 0;
        }

        @Override
        protected Token internalBuild() {
            if (_endPosition - _startPosition < 1)
                return null;

            return OperatorsList.get(_expression.charAt(_endPosition - 1));
        }
    }
}