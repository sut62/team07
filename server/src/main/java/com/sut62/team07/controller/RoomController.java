package com.sut62.team07.controller;

import com.sut62.team07.entity.Room;
import com.sut62.team07.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public Collection<Room> Room() {
        return roomRepository.findAll().stream().collect(Collectors.toList());
    }

}
