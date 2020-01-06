package com.cpe.backend.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import  com.cpe.backend.entity.Major;
import  com.cpe.backend.entity.TypeName;
import  com.cpe.backend.entity.Year;
@Data
@Entity
@NoArgsConstructor
@Table(name="STUDENT")
public class Student {

    @Id
    @SequenceGenerator(name="id_student_seq",sequenceName="id_student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_student_seq")
    @Column(name = "ID_STUDENT_ID", unique = true, nullable = true)
    private @NonNull Long id;
    

    @Column(name="STUDENT_ID")
    private @NonNull String student_id;

    @Column(name="STUDENT_PHONE")
    private @NonNull String student_phone;

    @Column(name="STUDENT_NAME")
    private @NonNull String student_name;
    
    @Column(name="STUDENT_EMAIL")
    private @NonNull String student_email;
    
    @Column(name="Password")
    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "MAJOR_ID", insertable = true)
    private Major major;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "YEAR_ID", insertable = true)
    private Year year;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeName.class)
    @JoinColumn(name = "TYPENAME_ID", insertable = true)
    private TypeName typeName;

        public void setMajor(Major major) {
        this.major = major;
	}

	public void setStudent_id(String student_id) {
        this.student_id=student_id;
	}

	public void setName(String student_name) {
        this.student_name=student_name;
	}

	public void setEmail(String student_email) {
        this.student_email=student_email;
	}

        public void setPhone(String student_phone) {
                this.student_phone=student_phone;
                }
	public void setPassword(String password2) {
        this.password=password2;
	}

        public void setTypeName(TypeName typename) {
        this.typeName=typename;
	}

        public void setYear(Year year) {
        this.year=year;
	}

    
}
