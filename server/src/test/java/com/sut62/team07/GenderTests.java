package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Gender;
import com.sut62.team07.repository.GenderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class GenderTests {

    private Validator validator;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5915088_genderNameShouldBeOK(){
        Gender gender = Gender.builder().name("ชาย").build();

        gender = genderRepository.saveAndFlush(gender);

        Optional<Gender> found = genderRepository.findById(gender.getId());
        assertEquals("ชาย", found.get().getName());
    }

    @Test
    void B5915088_genderNameMustBeNotNull() {
        Gender gender = Gender.builder().name(null).build();

        Set<ConstraintViolation<Gender>> result = validator.validate(gender);
        assertEquals(1, result.size());
        ConstraintViolation<Gender> violation = result.iterator().next();
        assertEquals("gender name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}