package com.cpe.backend.repository;

import  com.cpe.backend.entity.Year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface YearRep extends JpaRepository<Year, Long> {
    Year findById(long id);
}