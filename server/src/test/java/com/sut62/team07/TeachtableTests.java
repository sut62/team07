package com.sut62.team07;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;
import org.junit.jupiter.api.AfterEach;
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
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class TeachtableTests {

    private Validator validator;

    @Autowired private TeachtableRepository teachtableRepository;
    @Autowired private SemesterRepository semesterRepository;
    @Autowired private DaysRepository daysRepository;
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
    // สร้าง object สำหรับใช้ใน Teachtable
    Semester semester = new Semester();
    Days days = new Days();
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
        days.setName("Monday");
        days = daysRepository.saveAndFlush(days);
        room.setName("B4104");
        room = roomRepository.saveAndFlush(room);
    }

    // set Time สำหรับ Teachtable
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
        courseRepository.deleteAll();
        trimesterRepository.deleteAll();
        typeRepository.deleteAll();
        programInfoRepository.deleteAll();
        semesterRepository.deleteAll();
        daysRepository.deleteAll();
        roomRepository.deleteAll();
    }

    // เทส ใส่ค่าถูกต้อง Teachtable สามารถ save ได้
    @Test
    void B5906406_TeachtableShouldBeOK() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        //save and flush ไว้ที่ teachtable
        teachtable = teachtableRepository.saveAndFlush(teachtable);
        //ค้นหา Teachtable ด้วย id
        Optional<Teachtable> found = teachtableRepository.findById(teachtable.getId());
        //ตรวจสอบข้อมูล
        assertEquals(lecturer, found.get().getLecturer());
        assertEquals("B5906406@gmail.com", found.get().getEmail());
        assertEquals(course, found.get().getCourse());
        assertEquals(semester, found.get().getSemester());
        assertEquals("2020", found.get().getAcademicYear());
        assertEquals(days, found.get().getDays());
        assertEquals(room, found.get().getRoom());
        assertEquals(startTime, found.get().getStartTime());
        assertEquals(endTime, found.get().getEndTime());
        assertEquals("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด", found.get().getAnnotation());
    }

    //เทส Lecturer ต้องไม่เป็น Null
    @Test
    void B5906406_testLecturerMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(null); //ลองใส่ค่า Lecturer ที่เป็น null
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Lecturer cannot be null", violation.getMessage());
        assertEquals("lecturer", violation.getPropertyPath().toString());
    }

    //เทส Room ต้องไม่เป็น Null
    @Test
    void B5906406_testRoomMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(null); //ลองใส่ค่า Room ที่เป็น null
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Room cannot be null", violation.getMessage());
        assertEquals("room", violation.getPropertyPath().toString());
    }

    //เทส Semester ต้องไม่เป็น Null
    @Test
    void B5906406_testSemesterMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(null); //ลองใส่ค่า Semester ที่เป็น null
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Semester cannot be null", violation.getMessage());
        assertEquals("semester", violation.getPropertyPath().toString());
    }

    //เทส course ต้องไม่เป็น Null
    @Test
    void B5906406_testCourseMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(null); //ลองใส่ค่า Course ที่เป็น null
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Course cannot be null", violation.getMessage());
        assertEquals("course", violation.getPropertyPath().toString());
    }

    //เทส Days ต้องไม่เป็น Null
    @Test
    void B5906406_testDaysMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(null); //ลองใส่ค่า Days ที่เป็น null
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Days cannot be null", violation.getMessage());
        assertEquals("days", violation.getPropertyPath().toString());
    }

    //เทส AcademicYear ต้องไม่เป็น Null
    @Test
    void B5906406_testAcademicYearMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear(null); //ลองใส่ค่า AcademicYear ที่เป็น null
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Academic year cannot be null", violation.getMessage());
        assertEquals("academicYear", violation.getPropertyPath().toString());
    }


// เทสค่า academicYear ต้องตรงกับ pattern ต้องไม่เป็น String
    @Test
    void B5906406_testAcademicYearmustBeNotCharacter() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("aeiou"); //ลองใส่ค่า AcademicYear ที่เป็น string
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Academic must be match", violation.getMessage());
        assertEquals("academicYear", violation.getPropertyPath().toString());
    }

// เทสค่า academicYear ต้องตรงกับ pattern ต้องไม่เป็น String
    @Test
    void B5906406_testAcademicYearBe2or3AtIndex0() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("4456"); //ลองใส่ค่าที่ไม่ขึ้นต้นด้วย 2 ที่ Index 0
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Academic must be match", violation.getMessage());
        assertEquals("academicYear", violation.getPropertyPath().toString());
    }

    // เทสค่า academicYear ต้องไม่เป็น 3 digit
    @Test
    void B5906406_testAcademicYearMustNotBe3Digits() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("789"); //ลองใส่ค่า AcademicYear ที่เเป็นตัวเลข 3 digit
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Academic must be match", violation.getMessage());
        assertEquals("academicYear", violation.getPropertyPath().toString());
    }

    // เทสค่า academicYear ต้องไม่เป็น 5 digit
    @Test
    void B5906406_testAcademicYearMustNotBe5Digits() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("67890"); //ลองใส่ค่า AcademicYear ที่เป็นตัวเลข 5 digit
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Academic must be match", violation.getMessage());
        assertEquals("academicYear", violation.getPropertyPath().toString());
    }

    //เทส StartTime ต้องไม่เป็น Null
    @Test
    void B5906406_testStartTimeMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(null); //ลองใส่ค่า StartTime ที่เป็น null
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Start time cannot be null", violation.getMessage());
        assertEquals("startTime", violation.getPropertyPath().toString());
    }

    //เทส EndTime ต้องไม่เป็น Null
    @Test
    void B5906406_testEndTimeMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(null); //ลองใส่ค่า endTime ที่เป็น null
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("End time cannot be null", violation.getMessage());
        assertEquals("endTime", violation.getPropertyPath().toString());
    }

        //เทส Email ต้องไม่เป็น Null
    @Test
    void B5906406_testEmailMustNotBeNull() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail(null); //ลองใส่ค่า Email ที่เป็น null
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("email cannot be null", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    //เทส Email ต้องตรงรูปแบบ => มี '@'
    @Test
    void B5906406_testEmailMustMatch() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("aaa.com"); //ลองใส่ค่า Email ที่ไม่ตรงรูปแบบที่กำหนด
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2020");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("ไม่อนุญาตให้ใช้เครื่องมือสื่อสารทุกชนิด");

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("email is invalid", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

  // เทสค่า annotation ต้องไม่เป็น Null
  @Test
  void B5906406_testAnnotationMustNotBeNull() {
    //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation(null); // ลองใส่ค่า annotation ที่เป็น null

    Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Teachtable> violation = result.iterator().next();
    assertEquals("Annotation cannot be null", violation.getMessage());
    assertEquals("annotation", violation.getPropertyPath().toString());
  }

   //เทส Annotation ต้องไม่น้อยกว่า 2 ตัวอักษร
    @Test
    void B5906406_testAnnotationMustBeMoreThan2Characters() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("A");  //ลองใส่ค่า annotation ที่น้อยกว่า 2 ตัวอักษร

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Annotation must be between 2 and 100 characters", violation.getMessage());
        assertEquals("annotation", violation.getPropertyPath().toString());
    }

   //เทส Annotation ต้องไม่เกิน 100 ตัวอักษร
    @Test
    void B5906406_testAnnotationMustBeLessThan100Characters() {
        //สร้าง object Teachtable
        Teachtable teachtable = new Teachtable();
        teachtable.setLecturer(lecturer);
        teachtable.setEmail("B5906406@gmail.com");
        teachtable.setCourse(course);
        teachtable.setSemester(semester);
        teachtable.setAcademicYear("2563");
        teachtable.setDays(days);
        teachtable.setRoom(room);
        teachtable.setStartTime(startTime);
        teachtable.setEndTime(endTime);
        teachtable.setAnnotation("The Java programming language distinguishes between null and empty strings." + 
                    " An empty string is a string instance of zero length,  whereas a null string has no value at all." + 
                    " See Validating Persistent Fields and Properties for more information on using validation constraints.");  //ลองใส่ค่า annotation ที่เกิน 100 ตัวอักษร

        Set<ConstraintViolation<Teachtable>> result = validator.validate(teachtable);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Teachtable> violation = result.iterator().next();
        assertEquals("Annotation must be between 2 and 100 characters", violation.getMessage());
        assertEquals("annotation", violation.getPropertyPath().toString());
    }
}