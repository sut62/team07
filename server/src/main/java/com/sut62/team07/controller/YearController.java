package com.sut62.team07.controller;
import com.sut62.team07.entity.Year;
import com.sut62.team07.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class YearController {

    @Autowired
    private final YearRepository yearRep;

    public YearController(YearRepository yearRep) {
        this.yearRep = yearRep;
    }

    @GetMapping("/year")
    public Collection<Year> Year() {
        return yearRep.findAll().stream().collect(Collectors.toList());
    }

}






