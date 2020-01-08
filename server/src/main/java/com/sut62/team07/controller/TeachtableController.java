package com.sut62.team07.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team07.repository.*;
import com.sut62.team07.entity.*;
import com.sut62.team07.repository.RegistrationOfficerRepository;
import com.sut62.team07.request.LecturerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachtables")
public class TeachtableController {

    @Autowired
    private TeachtableRepository  teachtableRepository;

    @GetMapping
    public Collection<Teachtable> findAll() {
        return teachtableRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Teachtable> findOne(@PathVariable Long id) {
        return teachtableRepository.findById(id);
    }
}