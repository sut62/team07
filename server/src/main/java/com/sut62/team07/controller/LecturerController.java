package com.sut62.team07.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.MajorRepository;
import com.sut62.team07.repository.PrefixRepository;
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
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

    @GetMapping
    public Collection<Lecturer> findAll() {
        return lecturerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Lecturer> findOne(@PathVariable Long id) {
        return lecturerRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public Collection<Lecturer> findByName(@PathVariable String name) {
        return lecturerRepository.findByName(name);
    }

    @GetMapping("/lecturer/code/{lecturerCode}")
    public Optional<Lecturer> findByLecturerCode(@PathVariable String lecturerCode) {
        return lecturerRepository.findByLecturerCode(lecturerCode);
    }

    @PostMapping
    public Lecturer lecturerRegister(@RequestBody LecturerRequest request) {
        Optional<Major> major = majorRepository.findById(request.getMajor());
        Optional<Gender> gender = genderRepository.findById(request.getGender());
        Optional<Prefix> prefix = prefixRepository.findById(request.getPrefix());
        Optional<RegistrationOfficer> registrationOfficer = registrationOfficerRepository.findByOfficerCode(request.getCreatedBy());

        Lecturer lecturer = Lecturer.builder()
                    .email(request.getEmail())
                    .gender(gender.get())
                    .lecturerCode(request.getLecturerCode())
                    .name(request.getName())
                    .major(major.get())
                    .password(request.getPassword())
                    .personalId(request.getPersonalId())
                    .prefix(prefix.get())
                    .tel(request.getTel())
                    .createdBy(registrationOfficer.get())
                    .build();
        return lecturerRepository.save(lecturer);
    }


}