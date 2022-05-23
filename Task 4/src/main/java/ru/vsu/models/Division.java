package ru.vsu.models;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents division model.
 */
public final class Division {
    @CsvBindByName(column = "id")
    private final long _id;

    @CsvBindByName(column = "name")
    private final String _name;

    /**
     * Represents division model constructor.
     */
    public Division() {
        this(0, null);
    }

    /**
     * Represents division model constructor.
     * @param id Division identifier.
     * @param name Division name.
     */
    public Division(long id, String name) {
        _id = id;
        _name = name;
    }

    /**
     * Gets division identifier.
     * @return Division identifier.
     */
    public long getId() {
        return _id;
    }

    /**
     * Gets division name.
     * @return Division name.
     */
    public String getName() {
        return _name;
    }
}