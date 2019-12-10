package com.sut62.team07.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Institute;
import com.sut62.team07.repository.InstituteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    @Autowired
    private InstituteRepository instituteRepository;

    @GetMapping
    public Collection<Institute> findAll() {
        return instituteRepository.findAll().stream().collect(Collectors.toList());
    }
}