package ru.vsu.collections;

import java.util.function.BinaryOperator;

public final class MatrixMath {

    public static final <E> Matrix<E> applyOperation(Matrix<E> first, Matrix<E> second, BinaryOperator<E> operation) {
        if (first == null)
            throw new NullPointerException("\"first\" can't be null.");

        if (second == null)
            throw new NullPointerException("\"second\" can't be null.");

        if (first.get_rows_count() != second.get_rows_count()
                || first.get_columns_count() != second.get_columns_count())
            throw new IllegalArgumentException("\"first\" and \"second\" must have same length.");

        int rows_count = first.get_rows_count();
        int columns_count = first.get_columns_count();

        Matrix<E> resultMatrix = new Matrix<E>(rows_count, columns_count);
        for (int i = 0; i < rows_count; ++i)
            for (int j = 0; j < columns_count; ++j)
                resultMatrix.set(i, j, operation.apply(first.get(i, j), second.get(i, j)));

        return resultMatrix;
    }

    public static final <E> Matrix<E> applyOperation(Matrix<E> matrix, E element, BinaryOperator<E> operation) {
        if (matrix == null)
            throw new NullPointerException("\"first\" can't be null.");

        int rows_count = matrix.get_rows_count();
        int columns_count = matrix.get_columns_count();

        Matrix<E> resultMatrix = new Matrix<E>(rows_count, columns_count);
        for (int i = 0; i < rows_count; ++i)
            for (int j = 0; j < columns_count; ++j)
                resultMatrix.set(i, j, operation.apply(matrix.get(i, j), element));

        return resultMatrix;
    }

    public static final <E> Matrix<E> multiplication(Matrix<E> first, Matrix<E> second, BinaryOperator<E> summator,
            BinaryOperator<E> multiplicator) {
        if (first == null)
            throw new NullPointerException("\"first\" can't be null.");

        if (second == null)
            throw new NullPointerException("\"second\" can't be null.");

        if (first.get_columns_count() != second.get_rows_count())
            throw new IllegalArgumentException("Columns count \"first\" must be equal rows count \"second\".");

        int rows_count = first.get_rows_count();
        int columns_count = second.get_columns_count();

        Matrix<E> resultMatrix = new Matrix<E>(rows_count, columns_count);
        for (int i = 0; i < rows_count; ++i) {
            for (int j = 0; j < columns_count; ++j) {
                resultMatrix.set(i, j, multiplicator.apply(first.get(i, 0), second.get(0, j)));

                for (int k = 1; k < first.get_columns_count(); ++k) {
                    E mulResult = multiplicator.apply(first.get(i, k), second.get(k, j));
                    resultMatrix.set(i, j, summator.apply(resultMatrix.get(i, j), mulResult));
                }
            }
        }

        return resultMatrix;
    }

    public static final Matrix<Integer> sumInteger(Matrix<Integer> first, Integer element) {
        return applyOperation(first, element, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        });
    }

    public static final Matrix<Integer> sumInteger(Matrix<Integer> first, Matrix<Integer> second) {
        return applyOperation(first, second, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        });
    }

    public static final Matrix<Integer> multiplicationInteger(Matrix<Integer> first, Integer element) {
        return applyOperation(first, element, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t * u;
            }
        });
    }

    public static final Matrix<Integer> multiplicationInteger(Matrix<Integer> first, Matrix<Integer> second) {
        return multiplication(first, second, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        }, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t * u;
            }
        });
    }
}
