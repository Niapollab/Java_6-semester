package ru.vsu.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import javafx.util.Pair;

public final class Matrix<E> implements Iterable<E>, RandomAccess {

    private final ArrayList<ArrayList<E>> _raw_matrix;

    /** 
     * Create square matrix.
     * @param rowsCount Rows count.
     * @param columnsCount Columns Count.
     */
    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1)
            throw new IllegalArgumentException("\"rowsCount\" must be greater than zero.");

        if (columnsCount < 1)
            throw new IllegalArgumentException("\"columnsCount\" must be greater than zero.");
            
        _raw_matrix = buildMatrix(rowsCount, columnsCount);
    }

    /** 
     * Create square matrix.
     * @param rowsCount Rows count.
     */
    public Matrix(int rowsCount) {
        this(rowsCount, rowsCount);
    }
    
    /** 
     * Returns rows count.
     * @return Rows count.
     */
    public final int get_rows_count() {
        return _raw_matrix.size();
    }
    
    /** 
     * Returns columns count.
     * @return Columns count.
     */
    public final int get_columns_count() {
        return _raw_matrix.get(0).size();
    }

    /**
     * Returns <tt>true</tt> if matrix contains the specified element.
     * More formally, returns <tt>true</tt> if and only if matrix contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param obj element whose presence in matrix is to be tested.
     * @return <tt>true</tt> if matrix contains the specified element.
     */
    public final boolean contains(Object obj) {
        return indexOf(obj).equals(new Pair<Integer, Integer>(-1, -1));
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * in matrix, or pair (-1, -1) if matrix does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or (-1, -1) if there is no such index.
     * @param obj element whose presence in matrix is to be tested.
     * @return Index of whose presence in matrix is to be tested or (-1, -1).
     */
    public final Pair<Integer, Integer> indexOf(Object obj) {
        if (obj == null) {
            for (int i = 0; i < get_rows_count(); ++i) {
                for (int j = 0; j < get_columns_count(); ++j) {
                    if (get(i, j) == null)
                        return new Pair<Integer, Integer>(i, j);
                }
            }
        } else {
            for (int i = 0; i < get_rows_count(); ++i) {
                for (int j = 0; j < get_columns_count(); ++j) {
                    if (obj.equals(get(i, j)))
                        return new Pair<Integer, Integer>(i, j);
                }
            }
        }

        return new Pair<Integer, Integer>(-1, -1);
    }

    
    /**
     * Returns the index of the last occurrence of the specified element
     * in matrix, or pair (-1, -1) if matrix does not contain the element.
     * More formally, returns the higher index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or (-1, -1) if there is no such index.
     * @param obj element whose presence in matrix is to be tested.
     * @return Index of whose presence in matrix is to be tested or (-1, -1).
     */
    public final Pair<Integer, Integer> lastIndexOf(Object obj) {
        if (obj == null) {
            for (int i = get_rows_count() - 1; i >= 0; --i) {
                for (int j = get_columns_count() - 1; j >= 0; --j) {
                    if (get(i, j) == null)
                        return new Pair<Integer, Integer>(i, j);
                }
            }
        } else {
            for (int i = get_rows_count() - 1; i >= 0; --i) {
                for (int j = get_columns_count() - 1; j >= 0; --j) {
                    if (obj.equals(get(i, j)))
                        return new Pair<Integer, Integer>(i, j);
                }
            }
        }

        return new Pair<Integer, Integer>(-1, -1);
    }

    
    /** 
     * Gets element from matrix by index.
     * @param row Matrix row.
     * @param column Matrix column.
     * @return Matrix element.
     */
    public final E get(int row, int column) {
        rangeCheck(row, column);
        return _raw_matrix.get(row).get(column);
    }

    
    /** 
     * Sets element to matrix by index.
     * @param row Matrix row.
     * @param column Matrix column.
     * @param element New element.
     * @return New element.
     */
    public final E set(int row, int column, E element) {
        rangeCheck(row, column);
        return _raw_matrix.get(row).set(column, element);
    }

    
    /** 
     * Returns transpose matrix.
     * @return Transpose matrix.
     */
    public final Matrix<E> transpose() {
        Matrix<E> matrix = new Matrix<E>(get_columns_count(), get_rows_count());

        for (int i = 0; i < matrix.get_rows_count(); ++i)
            for (int j = 0; j < matrix.get_columns_count(); ++j)
                matrix.set(i, j, get(j, i));

        return matrix;
    }

    
    /** 
     * Returns matrix iterator.
     * @return Matrix iterator.
     */
    @Override
    public final Iterator<E> iterator() {
        return new MatrixIterator(this);
    }

    
    /** 
     * Build new empty matrix.
     * @param rowsCount Rows count.
     * @param columnsCount Columns count.
     * @return New empty matrix.
     */
    private final ArrayList<ArrayList<E>> buildMatrix(int rowsCount, int columnsCount) {
        ArrayList<ArrayList<E>> matrix = new ArrayList<ArrayList<E>>(rowsCount);

        for (int i = 0; i < rowsCount; ++i) {
            ArrayList<E> row_array = new ArrayList<E>(columnsCount);

            for (int j = 0; j < columnsCount; ++j)
                row_array.add(null);

                matrix.add(row_array);
        }

        return matrix;
    }

    
    /** 
     * Checks and throws exception when indexes out of range.
     * @param row Row index.
     * @param column Column index.
     */
    private final void rangeCheck(int row, int column) {
        if (row < 0)
            throw new IndexOutOfBoundsException("\"row\" must be greater than zero.");

        if (row >= get_rows_count())
            throw new IndexOutOfBoundsException("\"row\" must be less than rows count.");

        if (column < 0)
            throw new IndexOutOfBoundsException("\"column\" must be greater than zero.");

        if (column >= get_columns_count())
            throw new IndexOutOfBoundsException("\"column\" must be less than columns count.");
    }

    private final class MatrixIterator implements Iterator<E> {
        
        private Pair<Integer, Integer> _index;

        private Matrix<E> _matrix;

        public MatrixIterator(Matrix<E> matrix) {
            _index = new Pair<Integer, Integer>(0, 0);
            _matrix = matrix;
        }

        @Override
        public final boolean hasNext() {
            if (_index.getKey() >= _matrix.get_rows_count())
                return false;
            
            return true;
        }

        @Override
        public final E next() {
            if (_index.getKey() >= _matrix.get_rows_count())
                throw new NoSuchElementException();

            E value = _matrix.get(_index.getKey(), _index.getValue());

            _index = new Pair<Integer, Integer>(_index.getKey(), _index.getValue() + 1);

            if (_index.getValue() >= _matrix.get_columns_count())
                _index = new Pair<Integer, Integer>(_index.getKey() + 1, 0);

            return value;
        }

    }

}