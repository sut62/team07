package com.sut62.team07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

@RestController
public class PetitionController {
  @Autowired
  private final PetitionRepository petitionRepository;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private PetitionTypeRepository petitionTypeRepository;

  PetitionController(PetitionRepository petitionRepository) {
    this.petitionRepository = petitionRepository;
  }

  @GetMapping("/petition")
  public Collection<Petition> petitions() {
    return petitionRepository.findAll().stream().collect(Collectors.toList());
  }
  
  @PostMapping("/petition/{student_id}/{petitionType_id}/{detail}")
  public Petition newPetition(Petition newPetition,
                              @PathVariable String student_id,
                              @PathVariable long petitionType_id,
                              @PathVariable String detail) {

    Student student = studentRepository.findByStudentId(student_id).get();
    PetitionType petitionType = petitionTypeRepository.findById(petitionType_id);

    newPetition.setStudent(student);
    newPetition.setPetitionType(petitionType);
    newPetition.setDetail(detail);

    return petitionRepository.save(newPetition);
  }
}
