package ru.vsu.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HashTable<K, V> implements Iterable<Map.Entry<K, V>>, Map<K, V> {
    private final List<List<HashTableEntry>> _entries;

    private int _size;

    public HashTable(int density) {
        if (density < 0)
            throw new IllegalArgumentException();

        _entries = new ArrayList<List<HashTableEntry>>(density);
    }

    public int density() {
        return _entries.size();
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            throw new IllegalArgumentException();

        HashTableEntry entry = internalGet(key);

        return entry != null;
    }

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

    @Override
    public V get(Object key) {
        if (key == null)
            throw new IllegalArgumentException();

        HashTableEntry entry = internalGet(key);

        return entry != null ? entry.getValue() : null;
    }

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

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public void clear() {
        for (int i = 0; i < _entries.size(); ++i)
            _entries.set(i, null);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                keys.add(entry.getKey());

        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<V>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                values.add(entry.getValue());

        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<Entry<K, V>>();

        for (List<HashTableEntry> entriesByHashCode : _entries)
            for (HashTableEntry entry : entriesByHashCode)
                entries.add(entry);

        return entries;
    }

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