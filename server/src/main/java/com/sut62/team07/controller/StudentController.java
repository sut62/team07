package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Optional;

import java.util.stream.Collectors;
import com.sut62.team07.entity.Student;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.Year;
import com.sut62.team07.repository.MajorRepository;
import com.sut62.team07.repository.PrefixRepository;
import com.sut62.team07.repository.StudentRepository;
import com.sut62.team07.repository.YearRepository;

@RestController
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private  PrefixRepository prefixRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private YearRepository yearRepository;

    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public Collection<Student> Student() {
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/student/{student_id}")
    public Optional<Student> findBystudentId(@PathVariable String student_id){
        return studentRepository.findByStudentId(student_id);
    }
 
    @PostMapping("/student/{student_id}/{Prefixs}/{student_name}/{majors}/{id}/{student_email}/{student_phone}/{password}")
    public Student newStudent(
    @PathVariable String student_id,
    @PathVariable long Prefixs,
    @PathVariable String student_name,
    @PathVariable long majors,
    @PathVariable long id,
    @PathVariable String student_email,
    @PathVariable String student_phone,
    @PathVariable String password){
    
    
        Student newStudent = new Student();
        Major major = majorRepository.findById(majors).get();
        Prefix Prefix = prefixRepository.findById(Prefixs).get();
        Year year = yearRepository.findById(id);
        
        
        
        newStudent.setStudent_id((String)student_id);
        newStudent.setPrefix(Prefix);
        newStudent.setStudent_name((String)student_name);
        newStudent.setMajor(major);
        newStudent.setYear(year);
        newStudent.setStudent_email((String)student_email);
        newStudent.setStudent_phone(student_phone);
        newStudent.setPassword((String)password);
        
    
        return studentRepository.save(newStudent);
        
        }
}
