package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@Table(name="REGISTER")
public class Register {

    @Id
    @SequenceGenerator(name="register_seq",sequenceName="register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="register_seq")
    @Column(name = "REGISTER_ID", unique = true, nullable = true)
    private Long id;

    private @NotNull String sub_num;
    
    @Size(max=30,min=5)
    @Pattern(regexp = "^[ก-๏\\-]+$")
    private @NotNull String note;

    @PositiveOrZero
    @Max(value = 4)
    private@NotNull Integer  credit;


    @Column(name="REGISTER_DATE")
    private @NotNull LocalDateTime registerDate;
    
    // *-1 with semester
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
    @JoinColumn(name = "SEMESTER_ID", insertable = true)
    private Semester inSemester;

    // *-1 with section
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Section.class)
    @JoinColumn(name = "SECTION_ID", insertable = true)
    private Section chooseSec;

    // *-1 with student
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    private Student registerBy;


	

	public void setSemester(Semester inSemester) {
                this.inSemester = inSemester;
	}


	public void setSubNum(String sub_num) {
        this.sub_num = sub_num;
	}


        
}