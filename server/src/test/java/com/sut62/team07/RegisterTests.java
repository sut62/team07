package com.sut62.team07;

import com.sut62.team07.entity.Register;
import com.sut62.team07.repository.RegisterRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RegisterTests {

    private Validator validator;

    @Autowired
    private RegisterRepository registerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    LocalDateTime registerDate = LocalDateTime.now();

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6007089_testRegisterObjectShouldBeOK() {
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote("หมายเหตุ");

        register = registerRepository.saveAndFlush(register);

        Optional<Register> found = registerRepository.findById(register.getId());
        assertEquals("523332", found.get().getSub_num());
        assertEquals("หมายเหตุ", found.get().getNote());

    }
    // ======================================================================
    // [ Test Sub_num ]
    // ======================================================================

    @Test
    void B6007089_test_SubNumMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Sub_num Must Not Be Null");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num(null);
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote("หมายเหตุ");

        Set<ConstraintViolation<Register>> result = validator.validate(register);
        assertEquals(1, result.size());

        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sub_num", v.getPropertyPath().toString());
    }

    // ======================================================================
    // [ Test Credit ]
    // ======================================================================

    @Test
    void B6007089_test_CreditMustNotBeNull() {
        System.out.println("\n==========================================");
        System.out.println("\nTest Credit Must Not Be Null");
        System.out.println("\n==========================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(null);
        register.setRegisterDate(registerDate);
        register.setNote("หมายเตุ");

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("credit", v.getPropertyPath().toString());

    }

    @Test
    void B6007089_test_CreditMustBePositiveOrZero() {
        System.out.println("\n=======================================");
        System.out.println("\nTest note Must Be Positive Or Zero");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(-1);
        register.setRegisterDate(registerDate);
        register.setNote("หมายเหตุ");

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("credit", v.getPropertyPath().toString());
    }

    // ======================================================================
    // [ Test Note ]
    // ======================================================================

    @Test
    void B6007089_test_NoteMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Note Must Not Be Null");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote(null);

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    // Note น้อยกว่า 5  ตัว
    @Test
    void B6007089_test_NoteSizelessthan5() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Note size must be between 5 and 30");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote(" ");

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    // Note มากกว่า 30 ตัว
    @Test
    void B6007089_test_NoteSizeMorethan30() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Note size must be between 5 and 30");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("size must be between 1 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }


     @Test
    void B6007089_testNotePattern() {
        // สร้าง
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(registerDate);
        register.setNote("Nueng Wong");

        Set<ConstraintViolation<Register>> result = validator.validate(register);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

       ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    // ======================================================================
    // =[ Test rentDate] =
    // ======================================================================
    @Test
    void B6007089_test_RegisterDateMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest Note Must Not Be Null");
        System.out.println("\n=======================================\n");
        Register register = new Register();
        register.setSub_num("523332");
        register.setCredit(4);
        register.setRegisterDate(null);
        register.setNote("หมายเหตุ");

        Set<ConstraintViolation<Register>> result = validator.validate(register);
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Register> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("registerDate", v.getPropertyPath().toString());
    }

}
