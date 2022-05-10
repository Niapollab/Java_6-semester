package ru.vsu;

import org.junit.Test;
import com.opencsv.exceptions.*;
import ru.vsu.beans.*;
import ru.vsu.models.Division;

import static org.junit.Assert.*;

public class DivisionBeanFieldTest {
    @Test
    public void successfulParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "A";
        Converter<Division> converter = new DivisionBeanField<>();

        Division actual = converter.convert(expression);
        Division excepted = new Division(0, "A");

        assertEquals(excepted.getId(), actual.getId());
        assertEquals(excepted.getName(), actual.getName());
    }
}