package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

@RestController
public class TeachtableController {

    @Autowired
    private TeachtableRepository  teachtableRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DaysRepository daysRepository;

    @GetMapping("/teachtable")
    public Collection<Teachtable> teachtables() {
        return teachtableRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/teachtable/{lecturer_id}/{course_id}/{semester_id}/{academicYear}/{days_id}/{room_id}/{START_TIME}/{END_TIME}")
    public Teachtable newTeachtable(Teachtable newTeachtable,
                                        @PathVariable long lecturer_id,
                                        @PathVariable long course_id,
                                        @PathVariable long semester_id,
                                        @PathVariable String academicYear,
                                        @PathVariable long days_id,
                                        @PathVariable long room_id,
                                        @PathVariable String START_TIME,
                                        @PathVariable String END_TIME) {

        Lecturer lecturer = lecturerRepository.findById(lecturer_id).get();
        Course course = courseRepository.findById(course_id).get();
        Semester semester = semesterRepository.findById(semester_id);
        Days days = daysRepository.findById(days_id).get();
        Room room = roomRepository.findById(room_id);

        DateTimeFormatter formatStartTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(START_TIME, formatStartTime);

        DateTimeFormatter formatEndTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime endTime = LocalTime.parse(END_TIME, formatEndTime);

        newTeachtable.setLecturer(lecturer);
        newTeachtable.setCourse(course);
        newTeachtable.setSemester(semester);
        newTeachtable.setAcademicYear(academicYear);
        newTeachtable.setDays(days);
        newTeachtable.setRoom(room);
        newTeachtable.setStartTime(startTime);
        newTeachtable.setEndTime(endTime);

        return teachtableRepository.save(newTeachtable);
    }
}