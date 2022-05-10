package ru.vsu.collections;

import java.util.*;

/**
 * Represents HashTable.
 */
public final class HashTable<K, V> implements Iterable<Map.Entry<K, V>>, Map<K, V> {
    private final List<List<HashTableEntry>> _entries;

    private int _size;

    /**
     * Represents HashTable constructor.
     */
    public HashTable(int density) {
        if (density < 0)
            throw new IllegalArgumentException();

        _entries = new ArrayList<List<HashTableEntry>>(density);
    }

    /**
     * Gets HashTable density.
     * @return Density.
     */
    public int density() {
        return _entries.size();
    }

    /**
     * Gets HashTable size.
     * @return Size.
     */
    @Override
    public int size() {
        return _size;
    }

    /**
     * Returns <tt>true</tt>, if HashTable is empty.
     * @return <tt>True</tt>, if HashTable is empty.
     */
    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     *         key
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this map
     * @throws NullPointerException if the specified key is null and this map
     *         does not permit null keys
     */
    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            throw new IllegalArgumentException();

        HashTableEntry entry = internalGet(key);

        return entry != null;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     * @throws ClassCastException if the value is of an inappropriate type for
     *         this map
     * @throws NullPointerException if the specified value is null and this
     *         map does not permit null values
     */
    @Override
    public boolean containsValue(Object value) {
        for (List<HashTableEntry> entriesByHashCode : _entries) {
            if (entriesByHashCode == null)
                continue;

            for (HashTableEntry entry : entriesByHashCode)
                if (entry.getValue().equals(value))
                    return true;
        }

        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this map
     * @throws NullPointerException if the specified key is null and this map
     *         does not permit null keys
     */
    @Override
    public V get(Object key) {
        if (key == null)
            throw new IllegalArgumentException();

        HashTableEntry entry = internalGet(key);

        return entry != null ? entry.getValue() : null;
    }


    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>,
     *         if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *         is not supported by this map
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     * @throws NullPointerException if the specified key or value is null
     *         and this map does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException();

        int index = calculateHash(key);

        List<HashTableEntry> entriesByHashCode = _entries.get(index);
        if (entriesByHashCode == null)
            entriesByHashCode = _entries.set(index, new ArrayList<HashTableEntry>());

        HashTableEntry entry = internalGet(entriesByHashCode, key);
        if (entry != null)
            entry.setValue(value);
        else
            entriesByHashCode.add(new HashTableEntry(key, value));

        return value;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this map
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this map
     * @throws NullPointerException if the specified key is null and this
     *         map does not permit null keys
     */
    @Override
    public V remove(Object key) {
        int index = calculateHash(key);

        List<HashTableEntry> entriesByHashCode = _entries.get(index);

        if (entriesByHashCode == null)
            return null;

        int removeIndex = -1;
        for (int i = 0; removeIndex < 0 && i < entriesByHashCode.size(); ++i)
            if (entriesByHashCode.get(i).getKey().equals(key))
                removeIndex = i;

        if (removeIndex < 0)
            return null;

        V value = entriesByHashCode.get(removeIndex).getValue();
        entriesByHashCode.remove(removeIndex);

        return value;
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *         is not supported by this map
     * @throws ClassCastException if the class of a key or value in the
     *         specified map prevents it from being stored in this map
     * @throws NullPointerException if the specified map is null, or if
     *         this map does not permit null keys or values, and the
     *         specified map contains null keys or values
     * @throws IllegalArgumentException if some property of a key or value in
     *         the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this map
     */
    @Override
    public void clear() {
        for (int i = 0; i < _entries.size(); ++i)
            _entries.set(i, null);
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                keys.add(entry.getKey());

        return keys;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<V>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                values.add(entry.getValue());

        return values;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<Entry<K, V>>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                entries.add(entry);

        return entries;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entrySet().iterator();
    }

    private HashTableEntry internalGet(Object key) {
        List<HashTableEntry> entriesByHashCode = _entries.get(calculateHash(key));

        if (entriesByHashCode == null)
            return null;

        return internalGet(entriesByHashCode, key);
    }

    private HashTableEntry internalGet(List<HashTableEntry> entriesByHashCode, Object key) {
        for (HashTableEntry entry : entriesByHashCode)
            if (entry.getKey().equals(key))
                return entry;

        return null;
    }

    private int calculateHash(Object key) {
        return key.hashCode() % _entries.size();
    }

    private class HashTableEntry implements Map.Entry<K, V> {
        private final K _key;

        private V _value;

        public HashTableEntry(K key, V value) {
            _key = key;
            _value = value;
        }

        @Override
        public K getKey() {
            return _key;
        }

        @Override
        public V getValue() {
            return _value;
        }

        @Override
        public V setValue(V value) {
            return (_value = value);
        }
    }
}