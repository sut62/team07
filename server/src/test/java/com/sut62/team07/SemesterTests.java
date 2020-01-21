package com.sut62.team07;

import com.sut62.team07.entity.Semester;
import com.sut62.team07.repository.SemesterRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@DataJpaTest
public class SemesterTests {

    private Validator validator;

    @Autowired
    private SemesterRepository semesterRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime registerDate = LocalDateTime.now();

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6007089_testRegisterObjectShouldBeOK() {
        Semester semester = new Semester();
        semester.setSem("1");
        semester = semesterRepository.saveAndFlush(semester);

        Optional<Semester> found = semesterRepository.findById(semester.getId());
        assertEquals("1", found.get().getSem());

    }
    //======================================================================
    //                            [ Test Sem ]                       
    //======================================================================

    @Test
    void B6007089_test_SemeterMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Sem Must Not Be Null");
        System.out.println("\n=======================================\n");
        Semester semester = new Semester();
        semester.setSem(null);


        Set<ConstraintViolation<Semester>> result = validator.validate(semester);
        assertEquals(1, result.size());

        ConstraintViolation<Semester> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sem", v.getPropertyPath().toString());
    }
    

}


















