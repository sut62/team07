package com.sut62.team07;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

import org.junit.jupiter.api.AfterEach;
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

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ExamScheduleTests {

  private Validator validator;

  @Autowired private ExamScheduleRepository examScheduleRepository;
  @Autowired private SemesterRepository semesterRepository;
  @Autowired private RoomRepository roomRepository;

  @Autowired private CourseRepository courseRepository;
  @Autowired private TrimesterRepository trimesterRepository;
  @Autowired private TypeRepository typeRepository;
  @Autowired private ProgramInfoRepository programInfoRepository;

  @Autowired private LecturerRepository lecturerRepository;
  @Autowired private MajorRepository majorRepository;
  @Autowired private InstituteRepository instituteRepository;
  @Autowired private PrefixRepository prefixRepository;
  @Autowired private GenderRepository genderRepository;
  @Autowired private RegistrationOfficerRepository registrationOfficerRepository;
  //สร้าง object สำหรับใช้ใน Lecturer
  private Lecturer lecturer;
  private Major major;
  private Prefix prefix;
  private RegistrationOfficer registrationOfficer;
  private Gender gender;
  private Institute institute;
  // สร้าง object สำหรับใช้ใน Course
  Course course = new Course();
  Trimester trimester = new Trimester();
  Type type = new Type();
  ProgramInfo programInfo = new ProgramInfo();
  // สร้าง object สำหรับใช้ใน ExamSchedule
  Semester semester = new Semester();
  Room room = new Room();

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    //เตรียมข้อมูลสำหรับใช้ใน Lecturer
    institute = Institute.builder().name("Engineering").build();
    institute = instituteRepository.saveAndFlush(institute);
    major = Major.builder().name("Computer Engineering").institute(institute).build();
    major = majorRepository.saveAndFlush(major);
    prefix = Prefix.builder().name("Dr.").build();
    prefix = prefixRepository.saveAndFlush(prefix);
    gender = Gender.builder().name("Male").build();
    gender = genderRepository.saveAndFlush(gender);
    //เตรียมข้อมูลสำหรับใช้ใน RegistrationOfficer
    registrationOfficer = RegistrationOfficer.builder()
        .gender(gender)
        .name("Albert Wesker")
        .officerCode("R0025")
        .password("12345678")
        .prefix(prefix)
        .build();
    registrationOfficer = registrationOfficerRepository.saveAndFlush(registrationOfficer);
    //เตรียมข้อมูล lecturer
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
    //เตรียมข้อมูลสำหรับ course
    trimester.setName("ภาคกเรียนที่ 1");
    trimester = trimesterRepository.saveAndFlush(trimester);
    type.setName("วิชาภาคบังคับ");
    type = typeRepository.saveAndFlush(type);
    programInfo.setName("2554");
    programInfo = programInfoRepository.saveAndFlush(programInfo);
    //เตรียมข้อมูล course
    course.setSubNum("523351");
    course.setSubName("FORMAL METHODS AND COMPUTABILITY");
    course.setCredit(4);
    course.setTrimester(trimester);
    course.setType(type);
    course.setProgramInfo(programInfo);
    course.setLecturer(lecturer);
    course = courseRepository.saveAndFlush(course);
    //เตรียมข้อมูลสำหรับ ExamSchedule
    semester.setSem("1");
    semester = semesterRepository.saveAndFlush(semester);
    room.setName("B4104");
    room = roomRepository.saveAndFlush(room);

  }
  // set Date and Time สำหรับ ExamSchedule
  LocalDate today =  LocalDate.now();
  LocalDate date = today.plusMonths(1);  // วันที่ต้องเป็นวันในอนาคตเท่านั้นโดยเลื่อนไป 1 เดือน
  LocalTime startTime = LocalTime.of(9, 30);
  LocalTime endTime = LocalTime.of(11, 30);

  // ทำลาย Object เมื่อ Test เสร็จแล้ว
  @AfterEach
  public void destroy() {
    instituteRepository.deleteAll();
    majorRepository.deleteAll();
    genderRepository.deleteAll();
    prefixRepository.deleteAll();
    registrationOfficerRepository.deleteAll();
    lecturerRepository.deleteAll();
    trimesterRepository.deleteAll();
    typeRepository.deleteAll();
    programInfoRepository.deleteAll();
    semesterRepository.deleteAll();
    roomRepository.deleteAll();
    courseRepository.deleteAll();
  }

    // เทส ใส่ค่าถูกต้อง examSchedule สามารถ save ได้
  @Test
  void B5908042_examScheduleShouldBeOK() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");
    //save and flush ไว้ที่ examSchedule
    examSchedule = examScheduleRepository.saveAndFlush(examSchedule);
    //ค้นหา examShedule ด้วย id
    Optional<ExamSchedule> found = examScheduleRepository.findById(examSchedule.getId());
    //ตรวจสอบข้อมูล
    assertEquals(semester, found.get().getSemester());
    assertEquals("2020", found.get().getAcademicYear());
    assertEquals(course, found.get().getCourse());
    assertEquals(room, found.get().getRoom());
    assertEquals(date, found.get().getDate());
    assertEquals(startTime, found.get().getStartTime());
    assertEquals(endTime, found.get().getEndTime());
    assertEquals("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ", found.get().getAnnotation());
  }
  // เทสค่า semester ต้องไม่เป็น Null
  @Test
  void B5908042_testSemesterMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(null); // test ใส่ค่า semester ที่เป็น null
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Semester cannot be null", violation.getMessage());
    assertEquals("semester", violation.getPropertyPath().toString());
  }

  // เทสค่า course ต้องไม่เป็น Null
  @Test
  void B5908042_testCourseMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(null);  // test ใส่ค่า course ที่เป็น null
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Course cannot be null", violation.getMessage());
    assertEquals("course", violation.getPropertyPath().toString());
  }

  // เทสค่า room ต้องไม่เป็น Null
  @Test
  void B5908042_testRoomMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(null);  // test ใส่ค่า semester ที่เป็น null
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Room cannot be null", violation.getMessage());
    assertEquals("room", violation.getPropertyPath().toString());
  }

  // เทสค่า academicYear ต้องไม่เป็น Null
  @Test
  void B5908042_testAcademicYearMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear(null); // test ใส่ค่า academicYear ที่เป็น null
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Academic year cannot be null", violation.getMessage());
    assertEquals("academicYear", violation.getPropertyPath().toString());
  }

  // เทสค่า academicYear ต้องตรงกับ pattern
  @Test
  void B5908042_testAcademicYearmustNotBePattern() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("Twenty and Twenty"); // test ใส่ค่า academicYear เป็นตัวอักษร
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("must match \"\\d{4}\"", violation.getMessage());
    assertEquals("academicYear", violation.getPropertyPath().toString());
  }

  // เทสค่า academicYear ต้องไม่เป็น 3 digit
  @Test
  void B5908042_testAcademicYearmustNotBe3Digits() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("250"); // test ใส่ค่า academicYear เป็นตัวเลข 3 digit
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("must match \"\\d{4}\"", violation.getMessage());
    assertEquals("academicYear", violation.getPropertyPath().toString());
  }

  // เทสค่า academicYear ต้องไม่เป็น 5 digit
  @Test
  void B5908042_testAcademicYearmustNotBe5Digits() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("20210"); // test ใส่ค่า academicYear เป็นตัวเลข 5 digit
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("must match \"\\d{4}\"", violation.getMessage());
    assertEquals("academicYear", violation.getPropertyPath().toString());
  }

  // เทสค่า date ต้องไม่เป็น Null
  @Test
  void B5908042_testDateMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(null);// test ใส่ค่า date ที่เป็น null
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Date cannot be null", violation.getMessage());
    assertEquals("date", violation.getPropertyPath().toString());
  }

  // เทสค่า date ต้องไม่เป็นวันในอดีต
  @Test
  void B5908042_testDateMustNotBeADateInPast() {
    LocalDate datePast = LocalDate.of(2019, 10, 19); // test ใส่ค่า date ที่เป็นวันในอดีต
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(datePast);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("The annotated element must be a date in the future.", violation.getMessage());
    assertEquals("date", violation.getPropertyPath().toString());
  }

  // เทสค่า startTime ต้องไม่เป็น Null
  @Test
  void B5908042_testStartTimeMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(null); // test ใส่ค่า StartTime ที่เป็น null
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Start time cannot be null", violation.getMessage());
    assertEquals("startTime", violation.getPropertyPath().toString());
  }

  // เทสค่า endTime ต้องไม่เป็น Null
  @Test
  void B5908042_testEndTimeMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(null); // test ใส่ค่า endTime ที่เป็น null
    examSchedule.setAnnotation("ห้ามนำเครื่องคิดเลขเข้าห้องสอบ");

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("End time cannot be null", violation.getMessage());
    assertEquals("endTime", violation.getPropertyPath().toString());
  }

  // เทสค่า annotation ต้องไม่เป็น Null
  @Test
  void B5908042_testAnnotationMustNotBeNull() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation(null); // test ใส่ค่า annotation ที่เป็น null

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("Annotation cannot be null", violation.getMessage());
    assertEquals("annotation", violation.getPropertyPath().toString());
  }

  // เทสค่า annotation ต้องไม่น้อยกว่า 5 ตัวอักษร
  @Test
  void B5908042_testAnnotationMustNotLessThan5Characters() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้าม"); // test ใส่ค่า annotation ที่มี 4 ตัวอักษร

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("About Me must be between 5 and 200 characters", violation.getMessage());
    assertEquals("annotation", violation.getPropertyPath().toString());
  }

  // เทสค่า annotation ต้องไม่เกิน 200 ตัวอักษร
  @Test
  void B5908042_testAnnotationMustNotMoreThan5Characters() {
    //สร้าง object examSchedule
    ExamSchedule examSchedule = new ExamSchedule();
    examSchedule.setSemester(semester);
    examSchedule.setAcademicYear("2020");
    examSchedule.setCourse(course);
    examSchedule.setRoom(room);
    examSchedule.setDate(date);
    examSchedule.setStartTime(startTime);
    examSchedule.setEndTime(endTime);
    examSchedule.setAnnotation("ห้ามขีดเขียน หรือทำเครื่องหมาย หรือสัญลักษณ์ อย่างใดอย่างหนึ่ง ไว้ในบัตรประชาชน หรือบัตรประจำตัวนักเรียนที่มีรูปถ่าย " +
        "หรือบัตรที่ออกโดยทางราชการที่มีรูปถ่าย หรือตัวผู้เข้าสอบ หรือสิ่งอื่นใดที่ผู้เข้าสอบนำติดตัวเข้าห้องสอบ ถ้าฝ่าฝืนถือว่ามีเจตนาทุจริตในการสอบ ไม่อนุญาตให้ผู้เข้าสอบเข้าห้องสอบ " +
        "หลังจากเวลากำหนดเริ่มสอบผ่านไปแล้ว 30 นาที และไม่อนุญาตให้ผู้เข้าสอบทุกคนออกจากห้องสอบจนกว่าจะหมดเวลาสอบ หากมีเหตุจำเป็นระหว่างการสอบ ให้ผู้เข้าสอบแจ้งกรรมการคุมสอบทราบ " +
        "และให้กรรมการคุมสอบ พิจารณาดำเนินการตามเหตุจำเป็นเป็นรายกรณี"); // test ใส่ค่า annotation ที่มีมากกว่า 200 ตัวอักษร

    Set<ConstraintViolation<ExamSchedule>> result = validator.validate(examSchedule);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ExamSchedule> violation = result.iterator().next();
    assertEquals("About Me must be between 5 and 200 characters", violation.getMessage());
    assertEquals("annotation", violation.getPropertyPath().toString());
  }
}
