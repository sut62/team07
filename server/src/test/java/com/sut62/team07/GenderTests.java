package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
    void genderNameShouldBeOK(){
        Gender gender = Gender.builder().name("ชาย").build();

        gender = genderRepository.saveAndFlush(gender);

        Optional<Gender> found = genderRepository.findById(gender.getId());
        assertEquals("ชาย", found.get().getName());
    }
}