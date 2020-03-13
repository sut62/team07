package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Null;

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
import java.util.Set;
import javax.validation.ConstraintViolation;
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
        trimester.setName("ภาคเรียนที่ 1");
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
    
    @Test
  void B6019440_testcourseCodeMustNotBeNull() {
    //สร้าง object course
    Course course = new Course();
        course.setCourseCode(null);
        course.setCredit(4);
        course.setName("Thai");
        course.setProgramInfo(programInfo);
        course.setTrimester(trimester);
        course.setType(type);
        course.setLecturer(lecturer);

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("CourseCode cannot be null", violation.getMessage());
    assertEquals("courseCode", violation.getPropertyPath().toString());
  }

  @Test
  void B6019440_testcodeMustNoteq6() {
    //สร้าง object course
    Course course = new Course();
        course.setCourseCode("12345");
        course.setCredit(4);
        course.setName("Thai");
        course.setProgramInfo(programInfo);
        course.setTrimester(trimester);
        course.setType(type);
        course.setLecturer(lecturer);

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("must match \"\\d{6}\"", violation.getMessage());
    assertEquals("courseCode", violation.getPropertyPath().toString());
  }

  @Test
//credit null
  void B6019440_testcreditMustNotNull() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(null);
    course.setName("Thai");
    course.setProgramInfo(programInfo);
    course.setTrimester(trimester);
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Credit cannot be null", violation.getMessage());
    assertEquals("credit", violation.getPropertyPath().toString());
  }

  @Test
//credit null
  void B6019440_testcreditMustNotBezero() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(0);
    course.setName("Thai");
    course.setProgramInfo(programInfo);
    course.setTrimester(trimester);
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Credit cannot be 0", violation.getMessage());
    assertEquals("credit", violation.getPropertyPath().toString());
  }

    @Test
//credit มากกว่า 4
  void B6019440_testcreditMustNotmorethenfour() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(5);//ใส่ค่า มากกว่า 1 ตัว
    course.setName("Thai");
    course.setProgramInfo(programInfo);
    course.setTrimester(trimester);
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Credit should not be than 4", violation.getMessage());
    assertEquals("credit", violation.getPropertyPath().toString());
  }

  @Test
// name = null
  void B6019440_testnameMustNotBeNull() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(4);
    course.setName(null);//ใส่ค่า null
    course.setProgramInfo(programInfo);
    course.setTrimester(trimester);
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Name cannot be null", violation.getMessage());
    assertEquals("name", violation.getPropertyPath().toString());
  }

  @Test
  // programInfo = null
  void B6019440_testprogramMustNotBeNull() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(4);
    course.setName("Thai");
    course.setProgramInfo(null);//ใส่ค่า null
    course.setTrimester(trimester);
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("ProgramInfo cannot be null", violation.getMessage());
    assertEquals("programInfo", violation.getPropertyPath().toString());
  }

  @Test
  // trimester = null
  void B6019440_testTrimesterMustNotBeNull() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(4);
    course.setName("Thai");
    course.setProgramInfo(programInfo);
    course.setTrimester(null);//ใส่ค่า null
    course.setType(type);
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Trimester cannot be null", violation.getMessage());
    assertEquals("trimester", violation.getPropertyPath().toString());
  }

  @Test
  // type = null
  void B6019440_testTypeMustNotBeNull() {
    //สร้าง object course
    Course course = new Course();
    course.setCourseCode("123456");
    course.setCredit(4);
    course.setName("Thai");
    course.setProgramInfo(programInfo);
    course.setTrimester(trimester);
    course.setType(null);//ใส่ค่า null
    course.setLecturer(lecturer);
  

    Set<ConstraintViolation<Course>> result = validator.validate(course);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Course> violation = result.iterator().next();
    assertEquals("Type cannot be null", violation.getMessage());
    assertEquals("type", violation.getPropertyPath().toString());
  }
}