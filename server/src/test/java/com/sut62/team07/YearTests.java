package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Year;
import com.sut62.team07.repository.YearRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class YearTests {

    @Autowired
    private YearRepository yearRepository;

    private Validator validator;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6015305_yearShouldBeOK() {
        Year year = Year.builder().year_name("ชั้นปีที่1").build();

        year = yearRepository.saveAndFlush(year);

        final Optional<Year> found = yearRepository.findById(year.getId());
        assertEquals("ชั้นปีที่1", found.get().getYear_name());
    }

    @Test
    void B6015305_yearNameMustBeNotNull() {
        final Year year = Year.builder().year_name(null).build();

        final Set<ConstraintViolation<Year>> result = validator.validate(year);
        assertEquals(1, result.size());
        final ConstraintViolation<Year> violation = result.iterator().next();
        assertEquals("year name must be not null", violation.getMessage());
        assertEquals("year_name", violation.getPropertyPath().toString());
    }
}