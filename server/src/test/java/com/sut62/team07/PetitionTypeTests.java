package com.sut62.team07;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PetitionTypeTests {

  private Validator validator;

  @Autowired
  private PetitionTypeRepository petitionTypeRepository;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  // เทส ใส่ค่าถูกต้อง
  @Test
  void B5908042_testPetitionTypeNameShouldBeOK() {
    PetitionType petitionType = new PetitionType();
    petitionType.setName("คำร้องขอลาออก");
    petitionType = petitionTypeRepository.saveAndFlush(petitionType);

    Optional<PetitionType> found = petitionTypeRepository.findById(petitionType.getId());
    assertEquals("คำร้องขอลาออก", found.get().getName());
  }

  // เทสค่า name ต้องไม่เป็น Null
  @Test
  void B5908042_testPetTypeNameMustNotBeNull() {
    PetitionType petitionType = new PetitionType();
    petitionType.setName(null);

    Set<ConstraintViolation<PetitionType>> result = validator.validate(petitionType);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<PetitionType> violation = result.iterator().next();
    assertEquals("Name cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
  }

}