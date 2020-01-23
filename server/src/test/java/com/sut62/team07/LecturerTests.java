package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.MajorRepository;
import com.sut62.team07.repository.PrefixRepository;
import com.sut62.team07.repository.RegistrationOfficerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LecturerTests {

    private Validator validator;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

     //initial object
    private Major major;
    private Prefix prefix;
    private RegistrationOfficer registrationOfficer;
    private Gender gender;
    private Institute institute;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory  = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        institute = Institute.builder().name("Engineering").build();
        institute = instituteRepository.saveAndFlush(institute);

        major = Major.builder().name("Computer Engineering").institute(institute).build();
        major = majorRepository.saveAndFlush(major);

        prefix = Prefix.builder().name("Dr.").build();
        prefix = prefixRepository.saveAndFlush(prefix);

        gender = Gender.builder().name("Male").build();
        gender = genderRepository.saveAndFlush(gender);

        registrationOfficer = RegistrationOfficer.builder()
                .gender(gender)
                .name("Albert Wesker")
                .officerCode("R0025")
                .password("12345678")
                .prefix(prefix)
                .build();
        registrationOfficer = registrationOfficerRepository.saveAndFlush(registrationOfficer);
    }

    // ทำลาย Object เมื่อ Test เสร็จแล้ว
    @AfterEach
    public void destroy() {
        instituteRepository.deleteAll();
        majorRepository.deleteAll();
        genderRepository.deleteAll();
        prefixRepository.deleteAll();
        registrationOfficerRepository.deleteAll();
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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

                .build();
        // save และ flush แล้วเก็บในตัวแปร lecturer
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        // ค้นหา lecturer
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        
        // ตรวจสอบข้อมูล Object
        assertEquals("A0001", found.get().getLecturerCode());
        assertEquals("gg@gmail.com", found.get().getEmail());
        assertEquals("12345678", found.get().getPassword());
        assertEquals("John Doe", found.get().getName());
        assertEquals("1234567890123", found.get().getPersonalId());
        assertEquals("0987458745", found.get().getTel());
        assertEquals("Albert Wesker", found.get().getCreatedBy().getName());
        assertEquals("Engineering", found.get().getMajor().getInstitute().getName());
        assertEquals("Computer Engineering", found.get().getMajor().getName());
        assertEquals("Dr.", found.get().getPrefix().getName());
        assertEquals("Male", found.get().getGender().getName());
    }

    // lecturerCode ต้องไม่ใช่ค่า null
    @Test
    void B5915088_lecturerCodeMustBeNotNull() {
        // สร้าง Lecturer Object และใส่ข้อมูล
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode(null) // กำหนดให้ lecturerCode เป็นค่า null เพื่อตรวจสอบว่า field นี้ต้องไม่ null และตรวจสอบเจอ
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

                .build();

        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        // result ต้องมี 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        // ข้อความแจ้งเมื่อข้อมูล field นี้เป็น null จะแสดงข้อความนี้
        assertEquals("lecturer code must be not null", violation.getMessage());
        // ตรวจสอบว่า invalid field จะต้องเป็น field ชื่อว่า lecturerCode
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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

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

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(prefix)

                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("tel must be match", violation.getMessage());
        assertEquals("tel", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_genderMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")

                .createdBy(registrationOfficer)
                .gender(null)
                .major(major)
                .prefix(prefix)

                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("gender must be not null", violation.getMessage());
        assertEquals("gender", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_createdByMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")

                .createdBy(null)
                .gender(gender)
                .major(major)
                .prefix(prefix)

                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("registration officer must be not null", violation.getMessage());
        assertEquals("createdBy", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_majorMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(null)
                .prefix(prefix)

                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("major must be not null", violation.getMessage());
        assertEquals("major", violation.getPropertyPath().toString());
    }

    @Test
    void B5915088_prefixMustBeNotNull() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")

                .createdBy(registrationOfficer)
                .gender(gender)
                .major(major)
                .prefix(null)

                .build();
        Set<ConstraintViolation<Lecturer>> result = validator.validate(lecturer);
        assertEquals(1, result.size());
        ConstraintViolation<Lecturer> violation = result.iterator().next();
        assertEquals("prefix must be not null", violation.getMessage());
        assertEquals("prefix", violation.getPropertyPath().toString());
    }
}