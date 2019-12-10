package com.sut62.team07.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Course;
import com.sut62.team07.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public Collection<Course> findAll() {
        return courseRepository.findAll().stream().collect(Collectors.toList());
    }
}