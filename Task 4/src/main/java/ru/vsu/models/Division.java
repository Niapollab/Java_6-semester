package ru.vsu.models;

import com.opencsv.bean.CsvBindByName;

public final class Division {
    @CsvBindByName(column = "id")
    private final long _id;

    @CsvBindByName(column = "name")
    private final String _name;

    public Division() {
        this(0, null);
    }

    public Division(long id, String name) {
        _id = id;
        _name = name;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }
}