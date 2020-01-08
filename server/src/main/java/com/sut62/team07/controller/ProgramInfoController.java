package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.ProgramInfo;
import com.sut62.team07.repository.ProgramInfoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProgramInfoController {

    @Autowired
    private final ProgramInfoRepository programInfoRepository;

    public ProgramInfoController(ProgramInfoRepository programInfoRepository) {
        this.programInfoRepository = programInfoRepository;
    }

    @GetMapping("/programInfo")
    public Collection<ProgramInfo> ProgramInfos() {
        return programInfoRepository.findAll().stream().collect(Collectors.toList());
    }

}