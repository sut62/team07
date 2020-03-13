package com.sut62.team07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "course_seq")
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
        private Long id;

        @NotNull(message = "CourseCode cannot be null")
        @Pattern(regexp = "\\d{6}")
        private String courseCode;

        @NotNull(message = "Name cannot be null")
        private String name;

        
        @Max(value = 4, message = "Credit should not be than 4")
        @Min(value = 1, message = "Credit cannot be 0")
        @NotNull(message = "Credit cannot be null")
        private Integer credit;

        // @ManyToOne
        // private Major major;

        public void setSubNum(String courseCode) {
                this.courseCode = courseCode;
        }

        public void setSubName(String string) {
                name = string;
        }


        @NotNull(message = "Trimester cannot be null")
        @ManyToOne
        private Trimester trimester;

        @NotNull(message = "Type cannot be null")
        @ManyToOne
        private Type type;

        @NotNull(message = "ProgramInfo cannot be null")
        @ManyToOne
        private ProgramInfo programInfo;

        @NotNull
        @ManyToOne
        private Lecturer lecturer;

}
