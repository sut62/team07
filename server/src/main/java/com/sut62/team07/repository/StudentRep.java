package com.cpe.backend.repository;

import  com.cpe.backend.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;


import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.*;

@RepositoryRestResource
public
interface StudentRep extends JpaRepository<Student, Long> {
    Student findById(long id);
    

    @Query( value = "SELECT * FROM STUDENT z WHERE z.STUDENT_ID = :username and z.Password = :password",
    nativeQuery = true)
   Collection<Student> findCheck(@Param("username") String username,@Param("password") String password);

}