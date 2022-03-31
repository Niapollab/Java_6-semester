package ru.vsu;

import org.junit.Test;
import ru.vsu.collections.Matrix;
import javafx.util.Pair;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void buildSquareMatrix() {
        new Matrix<Integer>(5);

        assertTrue(true);
    }

    @Test
    public void buildMatrix() {
        new Matrix<Integer>(5, 2);

        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildMatrixWithNegativeValues() {
        new Matrix<Integer>(-1, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildEmptyMatrix() {
        new Matrix<Integer>(0, 0);
    }

    @Test
    public void successfulIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        assertEquals(new Pair<Integer, Integer>(3, 0), matrix.indexOf(15));
    }

    @Test
    public void failedIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        assertEquals(new Pair<Integer, Integer>(-1, -1),
                matrix.indexOf(matrix.get_rows_count() * matrix.get_columns_count()));
    }

    @Test
    public void findFirstIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, 0);
            }
        }

        assertEquals(new Pair<Integer, Integer>(0, 0), matrix.indexOf(0));
    }

    @Test
    public void successfulLastIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        assertEquals(new Pair<Integer, Integer>(3, 0), matrix.lastIndexOf(15));
    }

    @Test
    public void failedLastIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        assertEquals(new Pair<Integer, Integer>(-1, -1),
                matrix.lastIndexOf(matrix.get_rows_count() * matrix.get_columns_count()));
    }

    @Test
    public void findLastIndexOf() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, 0);
            }
        }

        assertEquals(new Pair<Integer, Integer>(matrix.get_rows_count() - 1, matrix.get_columns_count() - 1),
                matrix.lastIndexOf(0));
    }

    @Test
    public void getElement() {
        Matrix<Integer> matrix = new Matrix<Integer>(1, 1);

        matrix.get(0, 0);

        assertTrue(true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementOutOfRange() {
        Matrix<Integer> matrix = new Matrix<Integer>(1, 1);

        matrix.get(matrix.get_rows_count(), matrix.get_columns_count());
    }

    @Test
    public void setElement() {
        Matrix<Integer> matrix = new Matrix<Integer>(1, 1);

        matrix.set(0, 0, 1);

        assertEquals(new Integer(1), matrix.get(0, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setElementOutOfRange() {
        Matrix<Integer> matrix = new Matrix<Integer>(1, 1);

        matrix.set(matrix.get_rows_count(), matrix.get_columns_count(), 1);

        assertEquals(new Integer(1), matrix.get(0, 0));
    }

    @Test
    public void iterator() {
        Matrix<Integer> matrix = new Matrix<Integer>(5, 5);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i) {
            for (int j = 0; j < matrix.get_columns_count(); ++j) {
                matrix.set(i, j, element++);
            }
        }

        int expectedValue = 0;
        for (Integer value : matrix) {
            assertEquals((Integer) expectedValue, value);
            ++expectedValue;
        }
    }

    @Test
    public void transpose() {
        Matrix<Integer> matrix = new Matrix<Integer>(3, 1);
        int element = 0;

        for (int i = 0; i < matrix.get_rows_count(); ++i)
            matrix.set(i, 0, element++);

        element = 0;
        Matrix<Integer> transposed = matrix.transpose();
        for (int i = 0; i < transposed.get_columns_count(); ++i) {
            assertEquals((Integer) element, transposed.get(0, i));
            ++element;
        }
    }
}
