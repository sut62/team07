package com.sut62.team07.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;
// import com.okta.springbootvue.Student.entity.Student;
// import com.okta.springbootvue.Register.repository.StudentRepsitory;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegisterController {
    @Autowired
    private final RegisterRepository registerRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private  SemesterRepository semesterRepository;
    @Autowired
    private  SectionRepository sectionRepository;
  
   RegisterController(RegisterRepository registerRepository){
        this.registerRepository = registerRepository;
    }

    @GetMapping("/register")
    public Collection<Register> Registers() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/register/{id}")
    public Collection<Register> getRegister(@PathVariable("id") Long id) {
        return registerRepository.findRegister(id);
    }
    //save register
    @PostMapping("/register/{student_id}/{semester_id}/{section_id}/{sub_num}/{credit}")
    public Register newRegister(Register newRegister,
    @PathVariable long student_id,
    @PathVariable long semester_id,
    @PathVariable long section_id,
    @PathVariable String courseCode,
    @PathVariable int credit)
    {

    Student registerBy = studentRepository.findById(student_id);
    Semester inSemester = semesterRepository.findById(semester_id);
    Section chooseSec = sectionRepository.findById(section_id);
    LocalDateTime registerDate = LocalDateTime.now();
    
    newRegister.setRegisterBy(registerBy); //student
    newRegister.setSemester(inSemester); //semester
    newRegister.setChooseSec(chooseSec); //section
    newRegister.setRegisterDate(registerDate); //set date
    newRegister.setSubNum((String)courseCode); //set sub_num
    newRegister.setCredit(credit); //set credit
    
    return registerRepository.save(newRegister); //บันทึก Objcet ชื่อ Register
    }
    
}