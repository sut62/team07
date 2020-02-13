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

     
  }
}
