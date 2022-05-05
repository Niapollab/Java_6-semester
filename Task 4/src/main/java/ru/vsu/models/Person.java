package ru.vsu.models;

import com.opencsv.bean.*;
import ru.vsu.beans.*;
import java.util.Date;

public final class Person {
    @CsvBindByName(column = "id")
    private final long _id;

    @CsvBindByName(column = "name")
    private final String _name;

    @CsvCustomBindByName(column = "gender", converter = GenderBeanField.class)
    private final Gender _gender;

    @CsvCustomBindByName(column = "BirtDate", converter = DateBeanField.class)
    private final Date _birthDate;

    @CsvCustomBindByName(column = "Division", converter = DivisionBeanField.class)
    private final Division _division;

    @CsvBindByName(column = "Salary")
    private final long _salary;

    public Person() {
        this(0, null, Gender.MALE, null, null, 0);
    }

    public Person(long id, String name, Gender gender, Date birthDate, Division division, long salary) {
        _id = id;
        _name = name;
        _gender = gender;
        _birthDate = birthDate;
        _division = division;
        _salary = salary;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Gender getGender() {
        return _gender;
    }

    public Date getBirthDate() {
        return _birthDate;
    }

    public Division getDivision() {
        return _division;
    }

    public long getSalary() {
        return _salary;
    }
}