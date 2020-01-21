package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Major;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.MajorRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MajorTest {

    private Validator validator;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    private Institute institute;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        institute = Institute.builder().name("สำนักวิชาวิศวกรรมศาสตร์").build();
        institute = instituteRepository.saveAndFlush(institute);
    }

    @AfterEach
    public void destroy() {
        instituteRepository.deleteAll();
    }

    @Test
    void B5915088_majorShouldBeOK() {
        Major major = Major.builder().name("สาขาวิชาวิศวกรรมคอมพิวเตอร์").institute(institute).build();
        major = majorRepository.saveAndFlush(major);

        Optional<Major> found = majorRepository.findById(major.getId());
        assertEquals("สาขาวิชาวิศวกรรมคอมพิวเตอร์", found.get().getName());
        assertEquals("สำนักวิชาวิศวกรรมศาสตร์", found.get().getInstitute().getName());
    }

    @Test
    void B5915088_nameMustBeNotNull() {
        Major major = Major.builder().name(null).institute(institute).build();

        Set<ConstraintViolation<Major>> result = validator.validate(major);
        assertEquals(1, result.size());
        ConstraintViolation<Major> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_instituteMustBeNotNull() {
        Major major = Major.builder().name("สาขาวิชาวิศวกรรมคอมพิวเตอร์").institute(null).build();

        Set<ConstraintViolation<Major>> result = validator.validate(major);
        assertEquals(1, result.size());
        ConstraintViolation<Major> violation = result.iterator().next();
        assertEquals("institute must be not null", violation.getMessage());
        assertEquals("institute", violation.getPropertyPath().toString());
    }
}