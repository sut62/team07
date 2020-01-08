package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	@Column(name = "START_TIME")
	private @NonNull LocalTime startTime;

	@Column(name = "END_TIME")
	private @NonNull LocalTime endTime;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Lecturer.class)
	@JoinColumn(name = "LECTURER_ID", insertable = true)
	private Lecturer lecturer;

//	@OneToMany(fetch = FetchType.EAGER ,mappedBy="teachtable")
//	@JsonManagedReference
//	private Collection<Room> room;
//
//	@OneToMany(fetch = FetchType.EAGER ,mappedBy="teachtable")
//	@JsonManagedReference
//	private Collection<Section> section;

}
