package ru.vsu.models;

import com.opencsv.bean.*;
import ru.vsu.beans.*;
import java.util.Date;

/**
 * Represents person model.
 */
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

    /**
     * Represents person model constructor.
     */
    public Person() {
        this(0, null, Gender.MALE, null, null, 0);
    }

    /**
     * Represents person model constructor.
     * @param id Person identifier.
     * @param name Person name.
     * @param gender Person gender.
     * @param birthDate Person birthDate.
     * @param division Person division.
     * @param salary Person salary.
     */
    public Person(long id, String name, Gender gender, Date birthDate, Division division, long salary) {
        _id = id;
        _name = name;
        _gender = gender;
        _birthDate = birthDate;
        _division = division;
        _salary = salary;
    }

    /**
     * Gets person identifier.
     * @return Person identifier.
     */
    public long getId() {
        return _id;
    }

    /**
     * Gets person name.
     * @return Person name.
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets person gender.
     * @return Person gender.
     */
    public Gender getGender() {
        return _gender;
    }

    /**
     * Gets person birthDate.
     * @return Person birthDate.
     */
    public Date getBirthDate() {
        return _birthDate;
    }

    /**
     * Gets person division.
     * @return Person division.
     */
    public Division getDivision() {
        return _division;
    }

    /**
     * Gets person salary.
     * @return Person salary.
     */
    public long getSalary() {
        return _salary;
    }
}