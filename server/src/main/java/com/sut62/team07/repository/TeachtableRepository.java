package com.sut62.team07.repository;
import com.sut62.team07.entity.Teachtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TeachtableRepository extends JpaRepository<Teachtable, Long> {
    
}