package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

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

        @Pattern(regexp = "\\d{6}")
        private @NotNull String courseCode;

        private @NotNull String name;

        @Min(1)
        private @NotNull int credit;

        // @ManyToOne
        // private Major major;

        public void setSubNum(String courseCode) {
                this.courseCode = courseCode;
        }

        public void setSubName(String string) {
                name = string;
        }

        public void setCredit(int string) {
                credit = string;
        }


        @ManyToOne(fetch = FetchType.EAGER, targetEntity = Trimester.class)
         @JoinColumn(name = "TRIMESTER_ID", insertable = true)
        private Trimester trimester;

         @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
        @JoinColumn(name = "TYPE_ID", insertable = true)
         private Type type;


         @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProgramInfo.class)
         @JoinColumn(name = "ProgramInfo_ID", insertable = true)
        private ProgramInfo programInfo;

}