package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.sut62.team07.repository.*;
import com.sut62.team07.entity.*;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class DaysController {

    @Autowired
    private final DaysRepository  daysRepository;

    public DaysController(DaysRepository  daysRepository) {
            this.daysRepository = daysRepository;
    }
    @GetMapping("/days")
    public Collection<Days> getsAll() {
        return daysRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/days/{id}")
    public Days getDaysById(@PathVariable("id") Long id) {
        return daysRepository.findById(id).get();
    }
}