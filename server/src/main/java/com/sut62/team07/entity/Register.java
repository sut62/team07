package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private @NonNull Long id;

    private @NonNull String sub_num;

    private @NonNull int credit;

    @Column(name="REGISTER_DATE")
    private @NonNull LocalDateTime registerDate;
    
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


	public void setRegisterBy(Student registerBy) {
                this.registerBy = registerBy;
	}

	public void setRegisterDate(LocalDateTime date) {
                this.registerDate = date;
	}

	public void setSemester(Semester inSemester) {
                this.inSemester = inSemester;
	}

	public void setChooseSec(Section chooseSec) {
                this.chooseSec = chooseSec;
	}

	public void setSubNum(String sub_num) {
        this.sub_num = sub_num;
	}

	public void setCredit(int credit) {
        this.credit = credit;
	}

	public int getCredit() {
		return credit;
	}
        
}