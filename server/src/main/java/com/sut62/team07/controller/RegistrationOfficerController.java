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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping
    public RegistrationOfficer createRegistrationOfficer(@RequestBody RegistrationOfficer request) {
        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder()
                .gender(request.getGender())
                .name(request.getName())
                .officerCode(request.getOfficerCode())
                .password(request.getPassword())
                .prefix(request.getPrefix())
                .build();

        return registrationOfficerRepository.save(registrationOfficer);
    }
    

}