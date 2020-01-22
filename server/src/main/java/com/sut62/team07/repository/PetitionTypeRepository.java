package com.sut62.team07.repository;

import com.sut62.team07.entity.PetitionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PetitionTypeRepository extends JpaRepository<PetitionType, Long> {
  PetitionType findById(long id);
}
