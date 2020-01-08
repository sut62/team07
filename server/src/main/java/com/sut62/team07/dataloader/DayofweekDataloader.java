package com.sut62.team07.dataloader;

import java.util.stream.Stream;

import com.sut62.team07.entity.Dayofweek;
import com.sut62.team07.repository.DayofweekRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DayofweekDataloader implements ApplicationRunner {

    @Autowired private DayofweekRepository dayofweekRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday").forEach(name -> {
            Dayofweek dayofweek = new Dayofweek(); // สร้าง Object dayofweek
            dayofweek.setName(name); // set ชื่อ (name) ให้ Object ชื่อ dayofweek
            dayofweekRepository.save(dayofweek); // บันทึก Objcet ชื่อ dayofweek
        });
        dayofweekRepository.findAll().forEach(System.out::println);
    }

}

