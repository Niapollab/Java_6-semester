package ru.vsu;

import org.junit.Test;
import ru.vsu.collections.Matrix;
import ru.vsu.collections.MatrixMath;

import static org.junit.Assert.*;

public class MatrixMathTest {

    @Test
    public void sumMatrixWithInteger() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        Matrix<Integer> newMatrix = MatrixMath.sumInteger(matrix, 1);
        element = 1;
        for (Integer value : newMatrix) {
            assertEquals((Integer) element, value);
            ++element;
        }
    }

    @Test
    public void sumMatrixWithMatrix() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        Matrix<Integer> newMatrix = MatrixMath.sumInteger(matrix, matrix);
        element = 0;
        for (Integer value : newMatrix) {
            assertEquals((Integer) (2 * element), value);
            ++element;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumIntegerWithDiffSizes() {
        Matrix<Integer> first = new Matrix<Integer>(1, 2);
        Matrix<Integer> second = new Matrix<Integer>(2, 1);

        MatrixMath.sumInteger(first, second);
    }

    @Test
    public void multiplicationMatrixWithInteger() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        Matrix<Integer> newMatrix = MatrixMath.multiplicationInteger(matrix, 2);
        element = 0;
        for (Integer value : newMatrix) {
            assertEquals((Integer) (2 * element), value);
            ++element;
        }
    }

    @Test
    public void multiplicationMatrixWithMatrix() {
        Matrix<Integer> matrix = new Matrix<Integer>(2, 2);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i)
            for (int j = 0; j < matrix.get_columns_count(); ++j)
                matrix.set(i, j, element++);

        Matrix<Integer> newMatrix = MatrixMath.multiplicationInteger(matrix, matrix);

        assertEquals((Integer) 2, newMatrix.get(0, 0));
        assertEquals((Integer) 3, newMatrix.get(0, 1));
        assertEquals((Integer) 6, newMatrix.get(1, 0));
        assertEquals((Integer) 11, newMatrix.get(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void multiplicationIntegerWithDiffSizes() {
        Matrix<Integer> first = new Matrix<Integer>(1, 2);
        Matrix<Integer> second = new Matrix<Integer>(3, 4);

        for (int i = 0; i < first.get_rows_count(); ++i)
            for (int j = 0; j < first.get_columns_count(); ++j)
                first.set(i, j, 0);

        for (int i = 0; i < second.get_rows_count(); ++i)
            for (int j = 0; j < second.get_columns_count(); ++j)
                second.set(i, j, 0);

        MatrixMath.multiplicationInteger(first, second);
    }
}
