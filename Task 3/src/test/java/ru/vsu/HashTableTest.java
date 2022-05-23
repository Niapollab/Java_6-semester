package ru.vsu;

import org.junit.Test;
import ru.vsu.collections.HashTable;
import static org.junit.Assert.*;

public class HashTableTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructWithNegativeDensity() {
        new HashTable<Object, Object>(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsKeyIsNull() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        table.containsKey(null);
    }

    @Test
    public void containsKeyTest() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        assertTrue("a", table.containsKey("a"));
        assertTrue("b", table.containsKey("b"));
        assertTrue("c", table.containsKey("c"));
    }

    @Test
    public void containsValueTest() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        assertTrue("a", table.containsValue("1"));
        assertTrue("b", table.containsValue("2"));
        assertTrue("c", table.containsValue("3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNull() {
        HashTable<String, String> table = new HashTable<String, String>(1);

        table.get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putWithNull() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put(null, null);
    }

    @Test
    public void putTest() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        assertEquals(3, table.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeWithNull() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        table.remove(null);
    }

    @Test
    public void removeTest() {
        HashTable<String, String> table = new HashTable<String, String>(1);
        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        table.remove("a");
        assertEquals(2, table.size());
    }
}