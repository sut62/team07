package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="TEACHTABLE")
public class Teachtable  {
	@Id
	@SequenceGenerator(name="TEACHTABLE_SEQ",sequenceName="TEACHTABLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TEACHTABLE_SEQ")
	@Column(name = "TEACHTABLE_ID", unique = true, nullable = true)
	private @NonNull Long id;

	@Column(name = "ACADEMIC_YEAR")
	@NotNull(message = "Academic year cannot be null")
	@Pattern(regexp = "\\d{4}")
	private String academicYear;

	@Column(name = "START_TIME")
	private @NonNull LocalTime startTime;

	@Column(name = "END_TIME")
	private @NonNull LocalTime endTime;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Lecturer.class)
	@JoinColumn(name = "LECTURER_ID", insertable = true)
	private Lecturer lecturer;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
	@JoinColumn(name = "COURSE_ID", insertable = true)
	private Course course;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
	@JoinColumn(name = "ROOM_ID", insertable = true)
	private Room room;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
	@JoinColumn(name = "SEMESTER_ID", insertable = true)
	private Semester semester;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Days.class)
	@JoinColumn(name = "DAYS_ID", insertable = true)
	private Days days;

}
