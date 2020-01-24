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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PetitionTests {

  private Validator validator;

  @Autowired private PetitionRepository petitionRepository;
  @Autowired private PetitionTypeRepository petitionTypeRepository;

  @Autowired private StudentRepository studentRepository;
  @Autowired private PrefixRepository prefixRepository;
  @Autowired private InstituteRepository instituteRepository;
  @Autowired private MajorRepository majorRepository;
  @Autowired private YearRepository yearRepository;

  //สร้าง object สำหรับใช้ใน Student
  private Major major;
  private Prefix prefix;
  private Institute institute;
  Year year = new Year();
  // สร้าง object สำหรับใช้ใน Petition
  Student student = new Student();
  PetitionType petitionType = new PetitionType();

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    //เตรียมข้อมูลสำหรับใช้ใน Student
    institute = Institute.builder().name("Engineering").build();
    institute = instituteRepository.saveAndFlush(institute);
    major = Major.builder().name("Computer Engineering").institute(institute).build();
    major = majorRepository.saveAndFlush(major);
    prefix = Prefix.builder().name("Dr.").build();
    prefix = prefixRepository.saveAndFlush(prefix);
    year.setYear_name("ปีการศึกษาที่ 1");
    year = yearRepository.saveAndFlush(year);
    //เตรียมข้อมูล Student
    student.setStudent_id("B5999999");
    student.setPrefix(prefix);
    student.setStudent_name("ปลาหยุด จันดารา");
    student.setMajor(major);
    student.setYear(year);
    student.setStudent_email("Playut@gmail.com");
    student.setStudent_phone("0987654321");
    student.setPassword("12341234");
    student = studentRepository.saveAndFlush(student);
    //เตรียมข้อมูลสำหรับ Petition
    petitionType.setName("คำร้องขอลาออก");
    petitionType = petitionTypeRepository.saveAndFlush(petitionType);
  }

  // ทำลาย Object เมื่อ Test เสร็จแล้ว
  @AfterEach
  public void destroy() {
    instituteRepository.deleteAll();
    majorRepository.deleteAll();
    prefixRepository.deleteAll();
    yearRepository.deleteAll();
    studentRepository.deleteAll();
    petitionTypeRepository.deleteAll();
    petitionRepository.deleteAll();
  }

    // เทส ใส่ค่าถูกต้อง petition สามารถ save ได้
  @Test
  void B5908042_testPetitionShouldBeOK() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(student);
    petition.setPetitionType(petitionType);
    petition.setDetail("ผมเรียนไม่ไหวแล้วครับ");
    //save and flush ไว้ที่ petition
    petition = petitionRepository.saveAndFlush(petition);
    //ค้นหา petition ด้วย id
    Optional<Petition> found = petitionRepository.findById(petition.getId());
    //ตรวจสอบข้อมูล
    assertEquals(student, found.get().getStudent());
    assertEquals(petitionType, found.get().getPetitionType());
    assertEquals("ผมเรียนไม่ไหวแล้วครับ", found.get().getDetail());
  }
  // เทสค่า student ต้องไม่เป็น Null
  @Test
  void B5908042_testStudentMustNotBeNull() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(null); // test ใส่ค่า student ที่เป็น null
    petition.setPetitionType(petitionType);
    petition.setDetail("ผมเรียนไม่ไหวแล้วครับ");

    Set<ConstraintViolation<Petition>> result = validator.validate(petition);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Petition> violation = result.iterator().next();
    assertEquals("Student id cannot be null", violation.getMessage());
    assertEquals("student", violation.getPropertyPath().toString());
  }

  // เทสค่า petitionType ต้องไม่เป็น Null
  @Test
  void B5908042_testPetitionTypeMustNotBeNull() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(student);
    petition.setPetitionType(null); // test ใส่ค่า petitionType ที่เป็น null
    petition.setDetail("ผมเรียนไม่ไหวแล้วครับ");

    Set<ConstraintViolation<Petition>> result = validator.validate(petition);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Petition> violation = result.iterator().next();
    assertEquals("Petition type cannot be null", violation.getMessage());
    assertEquals("petitionType", violation.getPropertyPath().toString());
  }

  // เทสค่า detail ต้องไม่เป็น Null
  @Test
  void B5908042_testDetailMustNotBeNull() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(student);
    petition.setPetitionType(petitionType);
    petition.setDetail(null); // test ใส่ค่า detail ที่เป็น null

    Set<ConstraintViolation<Petition>> result = validator.validate(petition);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Petition> violation = result.iterator().next();
    assertEquals("Detail cannot be null", violation.getMessage());
    assertEquals("detail", violation.getPropertyPath().toString());
  }

  // เทสค่า detail ต้องไม่น้อยกว่า 5 ตัวอักษร
  @Test
  void B5908042_testDetailMustNotLessThan5Characters() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(student);
    petition.setPetitionType(petitionType);
    petition.setDetail("-"); // test ใส่ค่า detail ที่มีน้อยกว่า 5 ตัวอักษร

    Set<ConstraintViolation<Petition>> result = validator.validate(petition);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Petition> violation = result.iterator().next();
    assertEquals("About Me must be between 5 and 200 characters", violation.getMessage());
    assertEquals("detail", violation.getPropertyPath().toString());
  }

  // เทสค่า detail ต้องไม่เกิน 200 ตัวอักษร
  @Test
  void B5908042_testDetailMustNotMoreThan5Characters() {
    //สร้าง object petition  
    Petition petition = new Petition();  
    petition.setStudent(student);
    petition.setPetitionType(petitionType);
    petition.setDetail("ต้องการที่จะไปดูแลพ่อแม่ที่แก่ชรา ต้องการหาประสบการณ์การทำงานที่หลากหลาย" +
    "เพื่อความเจริญก้าวหน้าในหน้าที่ทำงาน และงานใหม่มีความท้าทายความสามารถของผมมากกว่า" + 
    "และมีแรงจูงใจที่ดีในด้านผลตอบแทนทีดีเหมาะสมกับความเหนื่อยและทุ่มเทการทำงานเพื่อบริษัท" +
    "เงินเดือนไม่พอยาไส้ 2 ปี ขึ้น 600"); // test ใส่ค่า detail ที่มีมากกว่า 200 ตัวอักษร

    Set<ConstraintViolation<Petition>> result = validator.validate(petition);
    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<Petition> violation = result.iterator().next();
    assertEquals("About Me must be between 5 and 200 characters", violation.getMessage());
    assertEquals("detail", violation.getPropertyPath().toString());
  }

}
