package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;

import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.repository.LecturerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LecturerTests {

    private Validator validator;

    @Autowired
    private LecturerRepository lecturerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory  = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // lecturer สามารถ save ได้
    @Test
    void B5915088_lecturerObjectShouldBeOK() {
        // สร้าง Lecturer Object และใส่ข้อมูล
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        // save และ flush แล้วเก็บในตัวแปร lecturer
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        // ค้นหา lecturer
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        
        assertEquals("A0001", found.get().getLecturerCode());
        assertEquals("gg@gmail.com", found.get().getEmail());
        assertEquals("12345678", found.get().getPassword());
        assertEquals("John Doe", found.get().getName());
        assertEquals("1234567890123", found.get().getPersonalId());
        assertEquals("0987458745", found.get().getTel());
    }

    // lecturerCode ต้องไม่ใช่ค่า null
    @Test
    void B5915088_lecturerCodeMustBeNotNull() {
        // สร้าง Lecturer Object และใส่ข้อมูล
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode(null)
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("lecturer code must be not null", violation.getMessage());
        assertEquals("lecturerCode", violation.getPropertyPath().toString());
    }

    // email ต้องไม่ใช่ค่า null
    @Test
    void B5915088_emailMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email(null)
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("email must be not null", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_emailMustBeMatch() {
        Lecturer lecturer = Lecturer.builder()
                .email("email.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("email is invalid", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_nameMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name(null)
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_passwordMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password(null)
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("password must be not null", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_passwordMustBeAtLeast8Characters() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("1234567")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("password at least 8 characters", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_personalIdMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId(null)
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("personalId must be not null", violation.getMessage());
        assertEquals("personalId", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_personalIdMustBeNotCharacters() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("qwertyuiop125")
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("personalId must be match", violation.getMessage());
        assertEquals("personalId", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_personalIdMustBeNot12Digits() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("123456789012")
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("personalId must be match", violation.getMessage());
        assertEquals("personalId", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_personalIdMustBeNot14Digits() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("12345678901234")
                .tel("0987458745")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("personalId must be match", violation.getMessage());
        assertEquals("personalId", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_telMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel(null)
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be not null", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_telMustBeDigit() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("aasqwerwer")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be match", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_telMustNotBe9Digit() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("098745874")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be match", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_telMustNotBe11Digit() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("09874587450")
                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be match", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_telMustBe0AtIndex0() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("1023456789")
                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be match", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    
}