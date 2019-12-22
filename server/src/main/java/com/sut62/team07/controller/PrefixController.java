package com.sut62.team07.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut62.team07.entity.Prefix;
import com.sut62.team07.repository.PrefixRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prefixs")
public class PrefixController {

    @Autowired
    private PrefixRepository prefixRepository;

    @GetMapping
    public Collection<Prefix> findAll() {
        return prefixRepository.findAll().stream().collect(Collectors.toList());
    }
}