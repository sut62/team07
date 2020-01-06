package com.cpe.backend.controller;
import com.cpe.backend.entity.Year;
import com.cpe.backend.repository.YearRep;
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
    private final YearRep yearRep;

    public YearController(YearRep yearRep) {
        this.yearRep = yearRep;
    }

    @GetMapping("/year")
    public Collection<Year> Year() {
        return yearRep.findAll().stream().collect(Collectors.toList());
    }

}






