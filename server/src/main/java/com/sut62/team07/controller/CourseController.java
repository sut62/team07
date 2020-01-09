package com.sut62.team07.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Trimester;
import com.sut62.team07.entity.Type;
import com.sut62.team07.entity.ProgramInfo;
import com.sut62.team07.repository.CourseRepository;
import com.sut62.team07.repository.TrimesterRepository;
import com.sut62.team07.repository.TypeRepository;
import com.sut62.team07.repository.ProgramInfoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TrimesterRepository trimesterRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProgramInfoRepository programInfoRepository;

    @GetMapping
    public Collection<Course> findAll() {
        return courseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/course/{programInfo_id}/{personalId}/{courseCode}/{credit}/{name}/{trimester_id}/{type_id}")
    public Course newCourse(Course newCourse,
    @PathVariable long programInfo_id,
    @PathVariable String personalId,
    @PathVariable String courseCode,
    @PathVariable int credit,
    @PathVariable String name,
    @PathVariable long trimester_id,
    @PathVariable long type_id) {
    

    Trimester trimester = trimesterRepository.findById(trimester_id);
    Type type = typeRepository.findById(type_id);
    ProgramInfo programInfo = programInfoRepository.findById(programInfo_id);

    newCourse.setSubNum(courseCode); 
    newCourse.setSubName(name); 
    newCourse.setCredit(credit);
    newCourse.setTrimester(trimester);
    newCourse.setType(type);
    newCourse.setProgramInfo(programInfo);

    return courseRepository.save(newCourse); 
    }
}