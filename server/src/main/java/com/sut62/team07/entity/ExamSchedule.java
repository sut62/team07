package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="EXAM_SCHEDULE")
public class ExamSchedule  {
    @Id
    @SequenceGenerator(name="EXAM_SCHEDULE_SEQ",sequenceName="EXAM_SCHEDULE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EXAM_SCHEDULE_SEQ")
    @Column(name = "EXAM_SCHEDULE_ID", unique = true, nullable = true)
    private @NotNull Long id;

    @Column(name = "ACADEMIC_YEAR")
    @NotNull(message = "Academic year cannot be null")
    @Pattern(regexp = "\\d{4}")
    private String academicYear;

    @Column(name = "DATE")
    @Future(message = "The annotated element must be a date in the future.")
    private @NotNull LocalDate date;

    @Column(name = "START_TIME")
    private @NotNull LocalTime startTime;

    @Column(name = "END_TIME")
    private @NotNull LocalTime endTime;

    @Column(name = "ANNOTATION")
    @Null(message = "Annotation can be null")
    @Size(min = 0, max = 200, message = "About Me must be between 0 and 200 characters")
    private String annotation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
    @JoinColumn(name = "SEMESTER_ID", insertable = true)
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "COURSE_ID", insertable = true)
    private Course course;

}
