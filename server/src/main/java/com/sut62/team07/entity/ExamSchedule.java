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
    private Long id;

    @Column(name = "ACADEMIC_YEAR")
    @NotNull(message = "Academic year cannot be null")
    @Pattern(regexp = "\\d{4}")
    private String academicYear;

    @Column(name = "DATE")
    @NotNull(message = "Date cannot be null")
    @Future(message = "The annotated element must be a date in the future.")
    private LocalDate date;

    @Column(name = "START_TIME")
    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @Column(name = "ANNOTATION")
    @NotNull(message = "Annotation cannot be null")
    @Size(min = 5, max = 200, message = "About Me must be between 5 and 200 characters")
    private String annotation;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @NotNull(message = "Room cannot be null")
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
    @NotNull(message = "Semester cannot be null")
    @JoinColumn(name = "SEMESTER_ID", insertable = true)
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @NotNull(message = "Course cannot be null")
    @JoinColumn(name = "COURSE_ID", insertable = true)
    private Course course;

}
