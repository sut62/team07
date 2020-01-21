package com.sut62.team07;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.sut62.team07.entity.Student;
import com.sut62.team07.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class StudentTests {

    private Validator validator;

    @Autowired

    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory  = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6015305_Studentinsert() {
       Student student = new Student();
      

      
        student.setStudent_id("B6015305");
        student.setStudent_phone("0934852619");
        student.setStudent_name("Nattapong");
        student.setStudent_email("ta@gmail.com");
        student.setPassword("12312312");
      
       
        
        studentRepository.saveAndFlush(student);
        Optional<Student> check = studentRepository.findById(student.getId());
        assertEquals("B6015305", check.get().getStudent_id());
        assertEquals("0934852619", check.get().getStudent_phone());
        assertEquals("Nattapong", check.get().getStudent_name());
        assertEquals("ta@gmail.com", check.get().getStudent_email());
        assertEquals("12312312", check.get().getPassword());
      
    }
    @Test
    void B6015305_StudenttestIDMustNotBeNull() {
        Student student = new Student();
        
 
       
         student.setStudent_id(null);
         student.setStudent_phone("0934852619");
         student.setStudent_name("Nattapong");
         student.setStudent_email("ta@gmail.com");
         student.setPassword("12312312");
       
        
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("student_id", v.getPropertyPath().toString());
    }
  


     // MobilePhone กรณีที่ใส่ข้อมูลถูกต้องปกติ
     @Test
     void B6015305_StudenttestMobilePhone() {
        Student student = new Student();
       
        student.setStudent_id("B6015305");
        student.setStudent_phone("0934852619");
        student.setStudent_name("Nattapong");
        student.setStudent_email("ta@gmail.com");
        student.setPassword("12312312");
      
       
        
        studentRepository.saveAndFlush(student);
        Optional<Student> check = studentRepository.findById(student.getId());
        assertEquals("B6015305", check.get().getStudent_id());
        assertEquals("0934852619", check.get().getStudent_phone());
        assertEquals("Nattapong", check.get().getStudent_name());
        assertEquals("ta@gmail.com", check.get().getStudent_email());
        assertEquals("12312312", check.get().getPassword());
     }

     // MobilePhone กรณีที่ 1น้อยกว่า 10 ตัว
     @Test
     void B6015305_StudenttestMobilePhoneSizelessthan10() {
        Student student = new Student();
       
        student.setStudent_id("B6015305");
        student.setStudent_phone("09348526");
        student.setStudent_name("Nattapong");
        student.setStudent_email("ta@gmail.com");
        student.setPassword("12312312");
      
       
       Set<ConstraintViolation<Student>> result = validator.validate(student);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Student> v = result.iterator().next();
       assertEquals("must match \"\\d{10}\"", v.getMessage());
       assertEquals("student_phone", v.getPropertyPath().toString());
   }    

     
 
        // MobilePhone กรณีที่ 2 มากกว่า 10 ตัว
     @Test
     void B6015305_StudenttestMobilePhoneSizelessthan11() {
        Student student = new Student();
       
        student.setStudent_id("B6015305");
        student.setStudent_phone("093485261999");
        student.setStudent_name("Nattapong");
        student.setStudent_email("ta@gmail.com");
        student.setPassword("12312312");
      
       
       Set<ConstraintViolation<Student>> result = validator.validate(student);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Student> v = result.iterator().next();
       assertEquals("must match \"\\d{10}\"", v.getMessage());
       assertEquals("student_phone", v.getPropertyPath().toString());
   }
     
//    @Test
//    void B6015305_StudenttestPassword8() {
//       Student student = new Student();
     
//       student.setStudent_id("B6015305");
//       student.setStudent_phone("0934852619");
//       student.setStudent_name("Nattapong");
//       student.setStudent_email("ta@gmail.com");
//       student.setPassword("1231231");
    
     
//      Set<ConstraintViolation<Student>> result = validator.validate(student);

//      // result ต้องมี error 1 ค่าเท่านั้น
//      assertEquals(1, result.size());

//      // error message ตรงชนิด และถูก field
//      ConstraintViolation<Student> v = result.iterator().next();
//      assertEquals("plassword min8", v.getMessage());
//      assertEquals("student_phone", v.getPropertyPath().toString());
 //}
   










}