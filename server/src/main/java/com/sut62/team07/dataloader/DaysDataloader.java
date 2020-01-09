package com.sut62.team07.dataloader;

import java.util.stream.Stream;

import com.sut62.team07.entity.Days;
import com.sut62.team07.repository.DaysRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DaysDataloader implements ApplicationRunner {

    @Autowired private DaysRepository daysRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday").forEach(name -> {
            Days days= new Days(); // สร้าง Object days
            days.setName(name); // set ชื่อ (name) ให้ Object ชื่อ days
            daysRepository.save(days); // บันทึก Objcet ชื่อ days
        });
        daysRepository.findAll().forEach(System.out::println);
    }

}

