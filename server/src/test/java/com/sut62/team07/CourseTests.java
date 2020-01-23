package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.Trimester;
import com.sut62.team07.entity.ProgramInfo;
import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.entity.Type;
import com.sut62.team07.repository.CourseRepository;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.MajorRepository;
import com.sut62.team07.repository.PrefixRepository;
import com.sut62.team07.repository.ProgramInfoRepository;
import com.sut62.team07.repository.RegistrationOfficerRepository;
import com.sut62.team07.repository.TrimesterRepository;
import com.sut62.team07.repository.TypeRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CourseTests {

    private Validator validator;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProgramInfoRepository programInfoRepository;

    @Autowired
    private TrimesterRepository trimesterRepository;

    @Autowired
    private TypeRepository typeRepository;

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

    private ProgramInfo programInfo;
    private Trimester trimester;
    private Type type;
    private Lecturer lecturer;
    private Major major;
    private Prefix prefix;
    private RegistrationOfficer registrationOfficer;
    private Gender gender;
    private Institute institute;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        trimester = new Trimester();
        trimester.setName("ภาคกเรียนที่ 1");
        trimester = trimesterRepository.saveAndFlush(trimester);

        type = new Type();
        type.setName("วิชาภาคบังคับ");
        type = typeRepository.saveAndFlush(type);

        programInfo = new ProgramInfo();
        programInfo.setName("2554");
        programInfo = programInfoRepository.saveAndFlush(programInfo);

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

        lecturer = Lecturer.builder()
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
        lecturer = lecturerRepository.saveAndFlush(lecturer);
    }

    @AfterEach
    public void destroy() {
        trimesterRepository.deleteAll();
        typeRepository.deleteAll();
        programInfoRepository.deleteAll();
        lecturerRepository.deleteAll();
        instituteRepository.deleteAll();
        majorRepository.deleteAll();
        genderRepository.deleteAll();
        prefixRepository.deleteAll();
        registrationOfficerRepository.deleteAll();
    }

    @Test
    void B6019440_courseShouldBeOK() {
        Course course = new Course();
        course.setCourseCode("123456");
        course.setCredit(4);
        course.setName("Thai");
        course.setProgramInfo(programInfo);
        course.setTrimester(trimester);
        course.setType(type);
        course.setLecturer(lecturer);

        course = courseRepository.saveAndFlush(course);

        Optional<Course> found = courseRepository.findById(course.getId());
        assertEquals("123456", found.get().getCourseCode());
        assertEquals(4, found.get().getCredit());
        assertEquals("Thai", found.get().getName());
        assertEquals(programInfo, found.get().getProgramInfo());
        assertEquals(trimester, found.get().getTrimester());
        assertEquals(type, found.get().getType());
    }
}