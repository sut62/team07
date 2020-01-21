package com.sut62.team07;

import com.sut62.team07.entity.Section;
import com.sut62.team07.repository.SectionRepository;
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
public class SectionTests {

    private Validator validator;

    @Autowired
    private SectionRepository sectionRepository;

    @BeforeEach
    public void setup() { 
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime registerDate = LocalDateTime.now();

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6007089_testRegisterObjectShouldBeOK() {
        Section section = new Section();
        section.setSec("1");
        section.setTime("Tu13:00-15:00 B1231 -- We13:00-15:00 B1125");
        section = sectionRepository.saveAndFlush(section);

        Optional<Section> found = sectionRepository.findById(section.getId());
        assertEquals("1", found.get().getSec());
        assertEquals("Tu13:00-15:00 B1231 -- We13:00-15:00 B1125", found.get().getTime());

    }
    //======================================================================
    //                            [ Test Sec ]                       
    //======================================================================

    @Test
    void B6007089_test_SecMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Sec Must Not Be Null");
        System.out.println("\n=======================================\n");
        Section section = new Section();
        section.setSec(null);
        section.setTime("Tu13:00-15:00 B1231 -- We13:00-15:00 B1125");


        Set<ConstraintViolation<Section>> result = validator.validate(section);
        assertEquals(1, result.size());

        ConstraintViolation<Section> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sec", v.getPropertyPath().toString());
    }
    
    //======================================================================
    //                            [ Test Time ]                       
    //======================================================================

    @Test
    void B6007089_test_SemeterMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Time Must Not Be Null");
        System.out.println("\n=======================================\n");
        Section section = new Section();
        section.setSec("1");
        section.setTime(null);


        Set<ConstraintViolation<Section>> result = validator.validate(section);
        assertEquals(1, result.size());

        ConstraintViolation<Section> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("time", v.getPropertyPath().toString());
    }
    
    



}


















