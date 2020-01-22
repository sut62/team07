package com.sut62.team07.dataloader;

import java.util.stream.Stream;

import com.sut62.team07.entity.PetitionType;
import com.sut62.team07.repository.PetitionTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PetitionTypeDataloader implements ApplicationRunner {

  @Autowired private PetitionTypeRepository petitionTypeRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Stream.of("คำร้องทั่วไป", "คำร้องขอลาออกจากการเป็นนักศึกษา", "คำร้องขอลาพักการศึกษา/รักษาสภาพการเป็นนักศึกษา", "คำร้องขอกลับเข้าศึกษา",
              "คำร้องขอโอนย้ายสาขาวิชา/คณะ", "คำร้องขอโอนย้ายสถานศึกษา", "คำร้องขอลงทะเบียนเกิน/ต่ำกว่าหน่วยกิต", "คำร้องขอลาป่วย/ลากิจ",
              "คำร้องขอเปิดรายวิชา", "คำร้องขอใบปริญญาบัตร/ใบประกาศนียบัตร", "คำร้องขอหนังสือมอบอำนาจ").forEach(name -> {
      PetitionType petitionType = new PetitionType();
      petitionType.setName(name);
      petitionTypeRepository.save(petitionType);
    });

    //petitionTypeRepository.findAll().forEach(System.out::println);
  }
}
