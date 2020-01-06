package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Major;
import com.sut62.team07.repository.MajorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MajorTest {

    private Validator validator;

    @Autowired
    private MajorRepository majorRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void nameShouldBeOK() {
        Major major = Major.builder().name("สาขาวิชาวิศวกรรมคอมพิวเตอร์").build();
        major = majorRepository.saveAndFlush(major);

        Optional<Major> found = majorRepository.findById(major.getId());
        assertEquals("สาขาวิชาวิศวกรรมคอมพิวเตอร์", found.get().getName());
    }

    @Test
    void nameMustBeNotNull() {
        Major major = Major.builder().name(null).build();

        Set<ConstraintViolation<Major>> result = validator.validate(major);
        assertEquals(1, result.size());
        ConstraintViolation<Major> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}