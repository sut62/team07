package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.sut62.team07.entity.Days;
import com.sut62.team07.repository.DaysRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DaysTests {

    private Validator validator;

    @Autowired private DaysRepository daysRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // เทส ใส่ค่าถูกต้อง
    @Test
    void B5906406_testDaysShouldBeOK() {
        Days days = new Days();
        days.setName("Monday");
        days = daysRepository.saveAndFlush(days);

        Optional<Days> found = daysRepository.findById(days.getId());
        assertEquals("Monday", found.get().getName());
    }
    // เทสค่า name ต้องไม่เป็น Null
    @Test
    void B5906406_testNameofDaysMustNotBeNull() {
        Days days = new Days();
        days.setName(null);

        Set<ConstraintViolation<Days>> result = validator.validate(days);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Days> violation = result.iterator().next();
        assertEquals("Name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }
}