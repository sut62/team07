package com.sut62.team07.repository;

import com.sut62.team07.entity.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TypeRepository extends JpaRepository<Type, Long> {
	Type findById(long id);
}