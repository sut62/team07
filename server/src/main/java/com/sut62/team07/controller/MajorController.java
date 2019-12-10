package com.sut62.team07.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Major;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/majors")
public class MajorController {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @GetMapping
    public Collection<Major> findAll() {
        return majorRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/institute/{id}")
    public Collection<Major> findByInstitute(@PathVariable Long id) {
        Optional<Institute> institute = instituteRepository.findById(id);
        return majorRepository.findByInstitute(institute.get());
    }
}