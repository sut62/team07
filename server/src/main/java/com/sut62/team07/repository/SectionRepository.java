package com.sut62.team07.repository;

import java.util.Collection;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SectionRepository extends JpaRepository<Section, Long> {
    Section findById(long id);
    

    @Query( value = "SELECT * FROM SECTION s where s.ID  = :sub",
            nativeQuery = true)
    Collection<Section> findSectionBySubInSec(@Param("sub") Long sub);

    Collection<Section> findBySubInSec(Course course);
    
}