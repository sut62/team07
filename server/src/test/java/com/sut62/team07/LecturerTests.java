package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

    // lecturerCode สามารถ save ได้
    @Test
    void lecturerCodeShouldBeOK() {
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
        // ทดสอบ field lecturerCode และหวังไว้ว่าต้องเท่ากับ A0001
        assertEquals("A0001", found.get().getLecturerCode());
    }

    // lecturerCode ต้องไม่ใช่ค่า null
    @Test
    void lecturerCodeMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("lecturer code must be not null", validation.getMessage());
        assertEquals("lecturerCode", validation.getPropertyPath().toString());
    }

    @Test
    void emailShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("gg@gmail.com", found.get().getEmail());
    }

    // email ต้องไม่ใช่ค่า null
    @Test
    void emailMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("email must be not null", validation.getMessage());
        assertEquals("email", validation.getPropertyPath().toString());
    }

    @Test
    void emailMustBeMatch() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("email is invalid", validation.getMessage());
        assertEquals("email", validation.getPropertyPath().toString());
    }

    // name ต้องสามารถ save ได้
    @Test
    void nameShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("John Doe", found.get().getName());
    }

    @Test
    void nameMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("name must be not null", validation.getMessage());
        assertEquals("name", validation.getPropertyPath().toString());
    }

    @Test
    void passwordShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("12345678", found.get().getPassword());
    }

    @Test
    void passwordMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("password must be not null", validation.getMessage());
        assertEquals("password", validation.getPropertyPath().toString());
    }

    @Test
    void passwordMustBeAtLeast8Characters() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("password at least 8 characters", validation.getMessage());
        assertEquals("password", validation.getPropertyPath().toString());
    }

    @Test
    void personalIdShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("1234567890123", found.get().getPersonalId());
    }

    @Test
    void personalIdMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("personalId must be not null", validation.getMessage());
        assertEquals("personalId", validation.getPropertyPath().toString());
    }

    @Test
    void personalIdMustBeNotCharacters() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("personalId must be match", validation.getMessage());
        assertEquals("personalId", validation.getPropertyPath().toString());
    }

    @Test
    void personalIdMustBeNot12Digits() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("personalId must be match", validation.getMessage());
        assertEquals("personalId", validation.getPropertyPath().toString());
    }

    @Test
    void personalIdMustBeNot14Digits() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("personalId must be match", validation.getMessage());
        assertEquals("personalId", validation.getPropertyPath().toString());
    }

    @Test
    void telIdShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("0987458745", found.get().getTel());
    }

    @Test
    void telMustBeNotNull() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("tel must be not null", validation.getMessage());
        assertEquals("tel", validation.getPropertyPath().toString());
    }

    @Test
    void telMustBeDigit() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("tel must be match", validation.getMessage());
        assertEquals("tel", validation.getPropertyPath().toString());
    }

    @Test
    void telMustNotBe9Digit() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("tel must be match", validation.getMessage());
        assertEquals("tel", validation.getPropertyPath().toString());
    }

    @Test
    void telMustNotBe11Digit() {
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
        ConstraintViolation<Lecturer> validation = result.iterator().next();
        assertEquals("tel must be match", validation.getMessage());
        assertEquals("tel", validation.getPropertyPath().toString());
    }
}