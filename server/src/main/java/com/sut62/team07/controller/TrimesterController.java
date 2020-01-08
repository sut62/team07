package com.sut62.team07.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.Collection;

import java.util.stream.Collectors;


import com.sut62.team07.entity.Trimester;
import com.sut62.team07.repository.TrimesterRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TrimesterController {

    @Autowired
    private final TrimesterRepository trimesterRepository;

    public TrimesterController(TrimesterRepository trimesterRepository) {
        this.trimesterRepository = trimesterRepository;
    }

    @GetMapping("/trimester")
    public Collection<Trimester> Trimesters() {
        return trimesterRepository.findAll().stream().collect(Collectors.toList());
    }
    
}