package ru.vsu;

import org.junit.Test;
import ru.vsu.beans.Converter;
import ru.vsu.models.Gender;
import ru.vsu.beans.*;
import com.opencsv.exceptions.*;
import static org.junit.Assert.*;

public class GenderBeanFieldTest {
    @Test
    public void maleParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "MaLE";
        Converter<Gender> converter = new GenderBeanField<>();

        Gender actual = converter.convert(expression);

        assertEquals(Gender.MALE, actual);
    }

    @Test
    public void femaleParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "fEMaLe";
        Converter<Gender> converter = new GenderBeanField<>();

        Gender actual = converter.convert(expression);

        assertEquals(Gender.FEMALE, actual);
    }

    @Test
    public void otherParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "Whale";
        Converter<Gender> converter = new GenderBeanField<>();

        Gender actual = converter.convert(expression);

        assertEquals(Gender.OTHER, actual);
    }
}