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
    private @NonNull Long id;

    @Column(name = "ACADEMIC_YEAR")
    private @NonNull String academicYear;

    @Column(name = "DATE")
    private @NonNull LocalDate date;

    @Column(name = "START_TIME")
    private @NonNull LocalTime startTime;

    @Column(name = "END_TIME")
    private @NonNull LocalTime endTime;

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
