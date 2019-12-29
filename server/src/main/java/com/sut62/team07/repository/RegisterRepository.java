package com.sut62.team07.repository;
import java.util.Collection;
import com.sut62.team07.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findById(long id);

    @Query( value = "SELECT * FROM Register s where s.STUDENT_ID  = :ids",
    nativeQuery = true)
    Collection<Register> findRegister(@Param("ids") Long ids);

}