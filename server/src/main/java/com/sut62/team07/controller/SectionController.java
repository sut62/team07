package com.sut62.team07.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.sut62.team07.entity.Section;
import com.sut62.team07.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SectionController {

    @Autowired
    private final SectionRepository sectionRepository;
    

    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    
    }

    @GetMapping("/section")
    public Collection<Section> Semesters() {
        return sectionRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/section/{sub}")
    public Collection<Section> getSectionBySubInSec(@PathVariable("sub") Long sub) {
        return sectionRepository.findSectionBySubInSec(sub);
    }

    

}