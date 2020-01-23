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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Lecturer.class)
	@NotNull(message = "Lecturer cannot be null")
	@JoinColumn(name = "LECTURER_ID", insertable = true)
	private Lecturer lecturer;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
	@NotNull(message = "Course cannot be null")
	@JoinColumn(name = "COURSE_ID", insertable = true)
	private Course course;

	@Column(name = "ACADEMIC_YEAR")
	@NotNull(message = "Academic year cannot be null")
	@Pattern(regexp = "\\d{4}")
	private String academicYear;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
	@NotNull(message = "Semester cannot be null")
	@JoinColumn(name = "SEMESTER_ID", insertable = true)
	private Semester semester;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Days.class)
	@NotNull(message = "Days cannot be null")
	@JoinColumn(name = "DAYS_ID", insertable = true)
	private Days days;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
	@NotNull(message = "Room cannot be null")
	@JoinColumn(name = "ROOM_ID", insertable = true)
	private Room room;

	@Column(name = "START_TIME")
	@NotNull(message = "Start time cannot be null")
	private LocalTime startTime;

	@Column(name = "END_TIME")
	@NotNull(message = "End time cannot be null")
	private LocalTime endTime;

}
