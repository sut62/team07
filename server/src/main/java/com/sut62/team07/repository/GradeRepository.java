package com.sut62.team07.repository;
import com.sut62.team07.entity.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface GradeRepository extends JpaRepository<Grade, Long> {
	Grade findById(long id);
}