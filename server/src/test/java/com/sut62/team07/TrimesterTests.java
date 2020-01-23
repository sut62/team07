package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Trimester;
import com.sut62.team07.repository.TrimesterRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TrimesterTests {

    private Validator validator;

    @Autowired
    private TrimesterRepository trimesterRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6019440_testTrimesterShouldBeOK(){
        Trimester trimester = new Trimester();
        trimester.setName("ภาคเรียนที่ 1");

        trimester = trimesterRepository.saveAndFlush(trimester);

        Optional<Trimester> found = trimesterRepository.findById(trimester.getId());
        assertEquals("ภาคเรียนที่ 1", found.get().getName());
    }

    @Test
    void B6019440_testTrimesterMustBeNotNull() {
        Trimester trimester = new Trimester();
        trimester.setName(null);

    Set<ConstraintViolation<Trimester>> result = validator.validate(trimester);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Trimester> violation = result.iterator().next();
    assertEquals("Trimester cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
    }
}
