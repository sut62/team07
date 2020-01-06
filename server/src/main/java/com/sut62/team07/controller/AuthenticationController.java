package com.sut62.team07.controller;

import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.RegistrationOfficerRepository;
import com.sut62.team07.repository.StudentRepository;
import com.sut62.team07.request.AuthRequest;
import com.sut62.team07.response.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

    @PostMapping("/auth")
    public AuthResponse login(@RequestBody AuthRequest auth) throws Exception {
        AuthResponse response = new AuthResponse();
        lecturerRepository.findByLecturerCode(auth.getUsername()).ifPresent(l -> {
            if (l.getPassword().equals(auth.getPassword())) {
                response.setUsername(auth.getUsername());
                response.setRole("Lecturer");
            }
        });

        registrationOfficerRepository.findByOfficerCode(auth.getUsername()).ifPresent(r -> {
            if (r.getPassword().equals(auth.getPassword())) {
                response.setUsername(auth.getUsername());
                response.setRole("RegistrationOfficer");
            }
        });

        studentRepository.findByStudentId(auth.getUsername()).ifPresent(s -> {
            if (s.getPassword().equals(auth.getPassword())) {
                response.setUsername(auth.getUsername());
                response.setRole("Student");
            }
        });

        return response;

    }
}