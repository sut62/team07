package com.sut62.team07.repository;

import com.sut62.team07.entity.Semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findById(long id);
}