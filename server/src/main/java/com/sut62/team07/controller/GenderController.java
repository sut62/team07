package com.sut62.team07.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Gender;
import com.sut62.team07.repository.GenderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    @GetMapping
    public Collection<Gender> findAll() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }

}