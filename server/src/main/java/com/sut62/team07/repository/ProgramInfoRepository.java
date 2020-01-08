package com.sut62.team07.repository;
import com.sut62.team07.entity.ProgramInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ProgramInfoRepository extends JpaRepository<ProgramInfo, Long> {
	ProgramInfo findById(long id);
}