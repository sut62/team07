package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Institute;
import com.sut62.team07.repository.InstituteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class InstituteTests {

    private Validator validator;

    @Autowired
    private InstituteRepository instituteRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5915088_nameShouldBeOK() {
        Institute institute = Institute.builder().name("สำนักวิชาวิศวกรรมศาสตร์").build();
        institute = instituteRepository.saveAndFlush(institute);

        Optional<Institute> found = instituteRepository.findById(institute.getId());
        assertEquals("สำนักวิชาวิศวกรรมศาสตร์", found.get().getName());
    }

    @Test
    void B5915088_nameMustBeNotNull() {
        Institute institute = Institute.builder().name(null).build();

        Set<ConstraintViolation<Institute>> result = validator.validate(institute);
        assertEquals(1, result.size());
        ConstraintViolation<Institute> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}