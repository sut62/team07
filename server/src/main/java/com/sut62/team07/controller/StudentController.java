package com.cpe.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.cpe.backend.entity.Student;
import com.cpe.backend.entity.Major;
import com.cpe.backend.entity.TypeName;
import com.cpe.backend.entity.Year;
import com.cpe.backend.repository.TypeNameRep;
import com.cpe.backend.repository.MajorRep;
import com.cpe.backend.repository.StudentRep;
import com.cpe.backend.repository.YearRep;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class StudentController {
    @Autowired
    private final StudentRep studentRep;
    @Autowired
    private  TypeNameRep typemaeRep;
    @Autowired
    private MajorRep majorRep;
    @Autowired
    private YearRep yearRep;

   
    StudentController(StudentRep studentRep) {
        this.studentRep = studentRep;
    }
    @GetMapping("/student")
    public Collection<Student> Student() {
        return studentRep.findAll().stream().collect(Collectors.toList());
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
    
    
    
    Major major = majorRep.findById(major_id);
    TypeName typename = typemaeRep.findById(typename_id);
    Year year = yearRep.findById(year_id);
    
    newStudent.setMajor(major);
    newStudent.setTypeName(typename);
    newStudent.setYear(year);
    newStudent.setStudent_id((String)student_id);
    newStudent.setName((String)student_name);
    newStudent.setEmail((String)student_email);
    newStudent.setPhone(student_phone);
    newStudent.setPassword((String)password);
    

    return studentRep.save(newStudent);
    
    }
}