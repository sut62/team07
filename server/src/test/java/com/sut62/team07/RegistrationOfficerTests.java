package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.PrefixRepository;
import com.sut62.team07.repository.RegistrationOfficerRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RegistrationOfficerTests {

    private Validator validator;

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    Gender gender;
    Prefix prefix;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        gender = Gender.builder().name("Female").build();
        gender = genderRepository.saveAndFlush(gender);

        prefix = Prefix.builder().name("ผศ.ดร.").build();
        prefix = prefixRepository.saveAndFlush(prefix);
    }

    @AfterEach
    public void destroy() {
        genderRepository.deleteAll();
        prefixRepository.deleteAll();
    }

    @Test
    void B5915088_registrationOfficerObjectShouldBeOK() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode("R0001")
                .password("12345678")

                .prefix(prefix)
                .gender(gender)

                .build();

        registrationOfficer = registrationOfficerRepository.saveAndFlush(registrationOfficer);
        Optional<RegistrationOfficer> found = registrationOfficerRepository.findById(registrationOfficer.getId());
        assertEquals("R0001", found.get().getOfficerCode());
        assertEquals("John Doe", found.get().getName());
        assertEquals("12345678", found.get().getPassword());
        assertEquals("Female", found.get().getGender().getName());
        assertEquals("ผศ.ดร.", found.get().getPrefix().getName());
    }

    @Test
    void B5915088_nameMustBeNotNull() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name(null)
                .officerCode("R0001")
                .password("12345678")

                .prefix(prefix)
                .gender(gender)

                .build();
    
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("name must be not null", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_officerCodeMustBeNotNull() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode(null)
                .password("12345678")

                .prefix(prefix)
                .gender(gender)

                .build();
    
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("officer code must be not null", violation.getMessage());
        assertEquals("officerCode", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_passwordMustBeNotNull() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode("R0001")
                .password(null)

                .prefix(prefix)
                .gender(gender)

                .build();
    
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("password must be not null", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_passwordMustBeAtLeast8Characters() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode("R0001")
                .password("1234567")

                .prefix(prefix)
                .gender(gender)

                .build();
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("password at least 8 characters", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_prefixMustBeNotNull() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode("R0001")
                .password("12345678")

                .prefix(null)
                .gender(gender)

                .build();
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("prefix must be not null", violation.getMessage());
        assertEquals("prefix", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_genderMustBeNotNull() {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .name("John Doe")
                .officerCode("R0001")
                .password("12345678")

                .prefix(prefix)
                .gender(null)

                .build();
        Set<ConstraintViolation<RegistrationOfficer>> result = validator.validate(registrationOfficer);
        assertEquals(1, result.size());
        ConstraintViolation<RegistrationOfficer> violation = result.iterator().next();
        assertEquals("gender must be not null", violation.getMessage());
        assertEquals("gender", violation.getPropertyPath().toString());
    }
}