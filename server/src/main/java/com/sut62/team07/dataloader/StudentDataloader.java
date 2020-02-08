package com.sut62.team07.dataloader;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentDataloader implements ApplicationRunner {

  @Autowired private PrefixRepository prefixRepository;
  @Autowired private MajorRepository majorRepository;
  @Autowired private YearRepository yearRepository;
  @Autowired private StudentRepository studentRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

      long id1 = 1L;
      long id2 = 2L;

      Prefix  prefix1 = prefixRepository.findById(id1).get();
      Prefix  prefix2 = prefixRepository.findById(id2).get();
      Major major1 = majorRepository.findById(id1).get();
      Major major2 = majorRepository.findById(id2).get();
      Year year1 = yearRepository.findById(id1);
      Year year2 = yearRepository.findById(id2);

      // Student student = new Student();
      //   student.setStudent_id("B5900000");
      //   student.setPrefix(prefix1);
      //   student.setStudent_name("สมชาย ใจดี");
      //   student.setMajor(major1);
      //   student.setYear(year1);
      //   student.setStudent_email("B5900000@gmail.com");
      //   student.setStudent_phone("0988888888");
      //   student.setPassword("zx012345");
      // studentRepository.save(student);

      // Student student2 = new Student();
      //   student2.setStudent_id("B5987654");
      //   student2.setPrefix(prefix2);
      //   student2.setStudent_name("สมหญิง จริงใจ");
      //   student2.setMajor(major2);
      //   student2.setYear(year2);
      //   student2.setStudent_email("B5987654@gmail.com");
      //   student2.setStudent_phone("0987654321");
      //   student2.setPassword("12345678");
      // studentRepository.save(student2);

      // Student student3 = new Student();
      //  student3.setStudent_id("B5999999");
      //  student3.setPrefix(prefix1);
      //  student3.setStudent_name("สมศรี มีตังค์");
      //  student3.setMajor(major2);
      //  student3.setYear(year2);
      //  student3.setStudent_email("B5999999@gmail.com");
      //  student3.setStudent_phone("0980808080");
      //  student3.setPassword("12341234");
      // studentRepository.save(student3);

    //studentRepository.findAll().forEach(System.out::println);
  }
}
