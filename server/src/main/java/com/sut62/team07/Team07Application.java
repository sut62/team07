package com.sut62.team07;

import java.util.stream.Stream;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Section;
import com.sut62.team07.entity.Semester;
import com.sut62.team07.entity.Year;
import com.sut62.team07.repository.CourseRepository;
import com.sut62.team07.repository.SectionRepository;
import com.sut62.team07.repository.SemesterRepository;
import com.sut62.team07.repository.StudentRepository;
import com.sut62.team07.repository.YearRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team07Application {

	public static void main(String[] args) {
		SpringApplication.run(Team07Application.class, args);
	}

	@Bean
	ApplicationRunner init(SemesterRepository semesterRepository, SectionRepository sectionRepository,
			StudentRepository studentRepository, CourseRepository courseRepository, YearRepository yearRepository) {
		return args -> {

			Stream.of("1", "2", "3").forEach(name -> { // aum
				Semester semester = new Semester(); // สร้าง Object Semester
				semester.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Semester
				semesterRepository.save(semester); // บันทึก Objcet ชื่อ Semester
			});

			Stream.of("ชั้นปีการศึกษาที่ 1", "ชั้นปีการศึกษาที่ 2", "ชั้นปีการศึกษาที่ 3", "ชั้นปีการศึกษาที่ 4")
					.forEach(name -> {
						Year year = new Year();
						year.setName(name);
						yearRepository.save(year);
					});

			Course sj1 = new Course();
			sj1.setSubNum("523331");
			sj1.setSubName("SYSTEM ANALYSIS AND DESIGN");
			sj1.setCredit(4);
			sj1 = courseRepository.save(sj1);

			Course sj2 = new Course();
			sj2.setSubNum("523371");
			sj2.setSubName("MICROPROCESSORS");
			sj2.setCredit(4);
			sj2 = courseRepository.save(sj2);

			Course sj3 = new Course();
			sj3.setSubNum("523301");
			sj3.setSubName("COMPUTER STATISTICS");
			sj3.setCredit(2);
			sj3 = courseRepository.save(sj3);

			Course sj4 = new Course();
			sj4.setSubNum("523351");
			sj4.setSubName("FORMAL METHODS AND COMPUTABILITY");
			sj4.setCredit(4);
			sj4 = courseRepository.save(sj4);

			Course sj5 = new Course();
			sj5.setSubNum("202207");
			sj5.setSubName("MAN, ECONOMY AND DEVELOPMENT");
			sj5.setCredit(3);
			sj5 = courseRepository.save(sj5);

			Course sj6 = new Course();
			sj6.setSubNum("523232");
			sj6.setSubName("OBJECT-ORIENTED TECHNOLOGY");
			sj6.setCredit(4);
			sj6 = courseRepository.save(sj6);

			// sa
			Section sec1 = new Section();
			sec1.setSec("1");
			sec1.setSubInSec(sj1);
			sectionRepository.save(sec1);

			Section sec2 = new Section();
			sec2.setSec("2");
			sec2.setSubInSec(sj1);
			sectionRepository.save(sec2);

			Section sec3 = new Section();
			sec3.setSec("3");
			sec3.setSubInSec(sj1);
			sectionRepository.save(sec3);

			// micro
			Section sec1_2 = new Section();
			sec1_2.setSec("1");
			sec1_2.setSubInSec(sj2);
			sectionRepository.save(sec1_2);

			Section sec2_2 = new Section();
			sec2_2.setSec("2");
			sec2_2.setSubInSec(sj2);
			sectionRepository.save(sec2_2);

			Section sec3_2 = new Section();
			sec3_2.setSec("3");
			sec3_2.setSubInSec(sj2);
			sectionRepository.save(sec3_2);

			Section sec4_2 = new Section();
			sec4_2.setSec("4");
			sec4_2.setSubInSec(sj2);
			sectionRepository.save(sec4_2);

			// com stat
			Section sec1_3 = new Section();
			sec1_3.setSec("1");
			sec1_3.setSubInSec(sj3);
			sectionRepository.save(sec1_3);

			Section sec2_3 = new Section();
			sec2_3.setSec("2");
			sec2_3.setSubInSec(sj3);
			sectionRepository.save(sec2_3);

			// formal
			Section sec1_4 = new Section();
			sec1_4.setSec("1");
			sec1_4.setSubInSec(sj4);
			sectionRepository.save(sec1_4);

			Section sec2_4 = new Section();
			sec2_4.setSec("2");
			sec2_4.setSubInSec(sj4);
			sectionRepository.save(sec2_4);

			// man eco
			Section sec1_5 = new Section();
			sec1_5.setSec("1");
			sec1_5.setSubInSec(sj5);
			sectionRepository.save(sec1_5);

			// java
			Section sec1_6 = new Section();
			sec1_6.setSec("1");
			sec1_6.setSubInSec(sj6);
			sectionRepository.save(sec1_6);

		};
	}

}
