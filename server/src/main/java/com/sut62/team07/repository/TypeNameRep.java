package com.cpe.backend.repository;

import  com.cpe.backend.entity.TypeName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TypeNameRep extends JpaRepository<TypeName, Long> {
    TypeName findById(long id);
}