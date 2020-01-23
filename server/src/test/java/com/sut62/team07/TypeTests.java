package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Type;
import com.sut62.team07.repository.TypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TypeTests {

    private Validator validator;

    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6019440_testTypeShouldBeOK(){
        Type type = new Type();
        type.setName("วิชาเลือกเสรี");

        type = typeRepository.saveAndFlush(type);

        Optional<Type> found = typeRepository.findById(type.getId());
        assertEquals("วิชาเลือกเสรี", found.get().getName());
    }

    @Test
    void B6019440_testTypeMustBeNotNull() {
        Type type = new Type();
        type.setName(null);

    Set<ConstraintViolation<Type>> result = validator.validate(type);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Type> violation = result.iterator().next();
    assertEquals("Type cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
    }
}

