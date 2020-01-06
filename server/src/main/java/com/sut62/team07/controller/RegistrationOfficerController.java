package com.sut62.team07.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.repository.RegistrationOfficerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/registrationofficers")
public class RegistrationOfficerController {

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

    @GetMapping
    public Collection<RegistrationOfficer> findAll() {
        return registrationOfficerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<RegistrationOfficer> findOne(@PathVariable Long id) {
        return registrationOfficerRepository.findById(id);
    }
    

}