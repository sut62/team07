package com.sut62.team07.repository;

import com.sut62.team07.entity.Course_Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Course_ScheduleRepository extends JpaRepository<Course_Schedule, Long> {
   // Course_Schedule findById(long id);
}
