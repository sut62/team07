package com.sut62.team07.dataloader;

import java.util.stream.Stream;

import com.sut62.team07.entity.Room;
import com.sut62.team07.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoomDataloader implements ApplicationRunner {

    @Autowired private RoomRepository roomRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("B2101", "B2102", "B2103", "B2104", "B3101", "B3102", "B3103", "B3104", "B4101",
                "B5101", "B5102", "B5103", "B5104", "B5105", "B5106", "B5107", "B5108").forEach(name -> {
            Room room = new Room();
            room.setName(name);
            roomRepository.save(room);
        });

        //roomRepository.findAll().forEach(System.out::println);
    }
}
