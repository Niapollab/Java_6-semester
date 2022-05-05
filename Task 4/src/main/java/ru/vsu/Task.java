package ru.vsu;

import java.io.*;
import java.text.*;
import com.opencsv.bean.*;
import ru.vsu.beans.DateBeanField;
import ru.vsu.models.Person;
import java.util.List;

public class Task {
    public static void main(String[] args) throws IOException {
        final char SEPARATOR = ';';
        final String INPUT_FILE_NAME = "resources\\foreign_names.csv";
        final DateFormat BIRTHDATE_FORMAT = new SimpleDateFormat(DateBeanField.PATTERN, DateBeanField.LOCALE);

        try (Reader reader = new FileReader(INPUT_FILE_NAME)) {
            CsvToBean<Person> csvToBean = buildCsvToBean(reader, SEPARATOR);

            try {
                List<Person> persons = csvToBean.parse();

                for (Person person : persons) {
                    System.out.println(String.format(
                            "id=%d::name=%s::sex=%s::birthday=%s::division_id=%d::division_name=%s::salary=%s",
                            person.getId(),
                            person.getName(),
                            person.getGender(),
                            BIRTHDATE_FORMAT.format(person.getBirthDate().getTime()),
                            person.getDivision().getId(),
                            person.getDivision().getName(),
                            person.getSalary()));
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static CsvToBean<Person> buildCsvToBean(Reader reader, char separator) {
        CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                .withType(Person.class)
                .withSeparator(separator)
                .withMappingStrategy(buildMappingStrategy())
                .build();

        return csvToBean;
    }

    private static MappingStrategy<Person> buildMappingStrategy() {
        MappingStrategy<Person> mappingStrategy = new HeaderColumnNameMappingStrategy<Person>();
        mappingStrategy.setType(Person.class);

        return mappingStrategy;
    }
}