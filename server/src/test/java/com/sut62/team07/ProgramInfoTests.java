package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.ProgramInfo;
import com.sut62.team07.repository.ProgramInfoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProgramInfoTests {

    private Validator validator;

    @Autowired
    private ProgramInfoRepository programInfoRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6019440_testProgramInfoShouldBeOK(){
        ProgramInfo programInfo = new ProgramInfo();
        programInfo.setName("2556");

        programInfo = programInfoRepository.saveAndFlush(programInfo);

        Optional<ProgramInfo> found = programInfoRepository.findById(programInfo.getId());
        assertEquals("2556", found.get().getName());
    }

    @Test
    void B6019440_testProgramInfoMustBeNotNull() {
        ProgramInfo programInfo = new ProgramInfo();
        programInfo.setName(null);

    Set<ConstraintViolation<ProgramInfo>> result = validator.validate(programInfo);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation< ProgramInfo> violation = result.iterator().next();
    assertEquals("ProgramInfo cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
    }
}