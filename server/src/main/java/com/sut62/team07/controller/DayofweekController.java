package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sut62.team07.repository.*;
import com.sut62.team07.entity.*;
import com.sut62.team07.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DayofweekController {

    @Autowired
    private final DayofweekRepository  dayofweekRepository;

    public DayofweekController(DayofweekRepository  dayofweekRepository) {
            this.dayofweekRepository = dayofweekRepository;
    }
    @GetMapping("/dayofweek")
    public Collection<Dayofweek> getDayofweekAll() {
        return dayofweekRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/dayofweek/{id}")
    public Dayofweek getDayofweekById(@PathVariable("id") Long id) {
        return dayofweekRepository.findById(id).get();
    }
}