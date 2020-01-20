package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Prefix;
import com.sut62.team07.repository.PrefixRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PrefixTests {

    private Validator validator;

    @Autowired
    private PrefixRepository prefixRepository;  

    @BeforeEach
    public void setup() {
        ValidatorFactory factory  = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5915088_prefixNameShouldBeOK() {
        Prefix prefix = Prefix.builder().name("นาย").build();
        prefix = prefixRepository.saveAndFlush(prefix);

        Optional<Prefix> found = prefixRepository.findById(prefix.getId());
        assertEquals("นาย", found.get().getName());
    }

    @Test
    void B5915088_prefixNameMustBeNotNull() {
        Prefix prefix = Prefix.builder().name(null).build();

        Set<ConstraintViolation<Prefix>> result = validator.validate(prefix);
        assertEquals(1, result.size());
        ConstraintViolation<Prefix> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}