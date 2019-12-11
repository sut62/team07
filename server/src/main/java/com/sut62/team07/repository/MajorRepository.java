package com.sut62.team07.repository;

import java.util.Collection;
import java.util.Optional;

import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Major;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MajorRepository extends JpaRepository<Major, Long>{

    Collection<Major> findByInstitute(Institute institute);
    Optional<Major> findByName(String name);
}