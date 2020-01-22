package com.sut62.team07;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class RoomTests {

  private Validator validator;

  @Autowired
  private RoomRepository roomRepository;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  // เทส ใส่ค่าถูกต้อง
  @Test
  void B5908042_testNameShouldBeOK() {
    Room room = new Room();
    room.setName("B4101");
    room = roomRepository.saveAndFlush(room);

    Optional<Room> found = roomRepository.findById(room.getId());
    assertEquals("B4101", found.get().getName());
  }

  // เทสค่า name ต้องไม่เป็น Null
  @Test
  void B5908042_testNameMustNotBeNull() {
    Room room = new Room();
    room.setName(null);

    Set<ConstraintViolation<Room>> result = validator.validate(room);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Room> violation = result.iterator().next();
    assertEquals("Name cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
  }

}
