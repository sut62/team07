package com.sut62.team07.repository;

import java.util.Optional;

import com.sut62.team07.entity.RegistrationOfficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegistrationOfficerRepository extends JpaRepository<RegistrationOfficer, Long>{

    Optional<RegistrationOfficer> findByOfficerCode(String officerCode);
    Optional<RegistrationOfficer> findByName(String name);
}