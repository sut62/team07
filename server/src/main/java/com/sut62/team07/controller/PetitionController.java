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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sut62.team07.entity.*;
import com.sut62.team07.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

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
  @PostMapping("/petition/{student_id}/{petitionType_id}/{title}/{detail}")
  public Petition newPetition(Petition newPetition,
                              @PathVariable long student_id,
                              @PathVariable long petitionType_id,
                              @PathVariable String titile,
                              @PathVariable String detail) {

    Student student = studentRepository.findById(student_id);
    PetitionType petitionType = petitionTypeRepository.findById(petitionType_id);

    LocalDate petitionDate = LocalDate.now();

    newPetition.setStudent(student);
    newPetition.setPetitionType(petitionType);
    newPetition.setTitle(titile);
    newPetition.setDetail(detail);
    newPetition.setPetitionDate(petitionDate);

    return petitionRepository.save(newPetition);
  }
}
