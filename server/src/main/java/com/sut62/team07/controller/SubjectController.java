package com.sut62.team07.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.sut62.team07.entity.Subject;
import com.sut62.team07.entity.Trimester;
import com.sut62.team07.entity.Type;
import com.sut62.team07.entity.Grade;
import com.sut62.team07.repository.SubjectRepository;
import com.sut62.team07.repository.TrimesterRepository;
import com.sut62.team07.repository.TypeRepository;
import com.sut62.team07.repository.GradeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SubjectController {
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private TrimesterRepository trimesterRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private GradeRepository gradeRepository;

    SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/subject")
    public Collection<Subject> Subjects() {
        return subjectRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/subject/{trimester_id}/{type_id}/{grade_id}")
    public Subject newSubject(Subject newSubject,
    @PathVariable long teacher_id,
    @PathVariable long grade_id,
    @PathVariable String code,
    @PathVariable String subj,
    @PathVariable String year,
    @PathVariable long type_id) {
    

    Trimester trimester = trimesterRepository.findById(teacher_id);
    Type type = typeRepository.findById(type_id);
    Grade grade = gradeRepository.findById(grade_id);


    newSubject.setTrimester(trimester);
    newSubject.setType(type);
    newSubject.setGrade(grade);

    return subjectRepository.save(newSubject); 
    
    }
}