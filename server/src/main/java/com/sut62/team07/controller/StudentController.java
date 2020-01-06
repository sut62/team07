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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
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
    @PostMapping("/student/{student_id}/{typename_id}/{student_name}/{major_id}/{year_id}/{student_email}/{student_phone}/{password}")
    public Student newStudent(Student newStudent,
    @PathVariable String student_id,
    @PathVariable long typename_id,
    @PathVariable String student_name,
    @PathVariable long major_id,
    @PathVariable long year_id,
    @PathVariable String student_email,
    @PathVariable String student_phone,
    @PathVariable String password){
    
    
    
    Optional<Major> major = majorRepository.findById(major_id);
    Prefix typename = prefixRepository.findById(typename_id).get();
    Year year = yearRepository.findById(year_id);
    
    newStudent.setMajor(major);
    newStudent.setTypeName(typename);
    newStudent.setYear(year);
    newStudent.setStudent_id((String)student_id);
    newStudent.setName((String)student_name);
    newStudent.setEmail((String)student_email);
    newStudent.setPhone(student_phone);
    newStudent.setPassword((String)password);
    

    return studentRepository.save(newStudent);
    
    }
}