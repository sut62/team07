package com.sut62.team07.dataloader;

import java.util.Arrays;
import java.util.stream.Stream;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.entity.Major;
import com.sut62.team07.repository.CourseRepository;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LecturerDataloader implements ApplicationRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("ชาย", "หญิง").forEach(sex -> {
            Gender gender = Gender.builder().name(sex).build();
            genderRepository.save(gender);
        });

        Stream.of("สำนักวิชาวิทยาศาสตร์", "สำนักวิชาเทคโนโลยีสังคม", "สำนักวิชาเทคโนโลยีการเกษตร",
                "สำนักวิชาแพทย์ศาสตร์", "สำนักวิชาวิศวกรรมศาสตร์", "สำนักวิชาพยาบาลศาสตร์", "สำนักวิชาทันตแพทย์ศาสตร์",
                "สำนักวิชาสาธารณสุขศาสตร์").forEach(name -> {
                    Institute institute = Institute.builder().name(name).build();
                    instituteRepository.save(institute);
                });

        Stream.of("วิทยาศาสตรบัณฑิตแบบก้าวหน้า", "วิทยาศาสตรบัณฑิต", "เคมี", "คณิตศาสตร์", "ชีววิทยา", "ฟิสิกส์",
                "วิทยาศาสตร์การกีฬา").forEach(name -> {
                    Institute institute = instituteRepository.findByName("สำนักวิชาวิทยาศาสตร์").get();
                    Major major = Major.builder().institute(institute).name(name).build();
                    majorRepository.save(major);
                });

        Stream.of("ยังไม่สังกัดหลักสูตร-เทคโนโลยีสารสนเทศ", "เทคโนโลยีสารสนเทศ (นิเทศศาสตร์)",
                "วิทยาการสารสนเทศ (นิเทศศาสตร์)", "เทคโนโลยีสารสนเทศ (ระบบสารสนเทศเพื่อการจัดการ)",
                "วิทยาการสารสนเทศ (ระบบสารสนเทศเพื่อการจัดการ)", "เทคโนโลยีสารสนเทศ (สารสนเทศศึกษา)",
                "วิทยาการสารสนเทศ (สารสนเทศศึกษา)", "วิทยาการสารสนเทศ (ซอฟต์แวร์วิสาหกิจ)",
                "วิทยาการสารสนเทศ (ธุรกิจอัจฉริยะและการวิเคราะห์ข้อมูล)", "วิทยาการสารสนเทศ (นิเทศศาสตร์ดิจิทัล)",
                "เทคโนโลยีการจัดการ", "เทคโนโลยีการจัดการ (การจัดการการตลาด)",
                "เทคโนโลยีการจัดการ (การจัดการโลจิสติกส์)", "เทคโนโลยีการจัดการ (การจัดการผู้ประกอบการ)",
                "เทคโนโลยีการจัดการ (การจัดการธุรกิจใหม่และภาวการณ์ประกอบการ)", "เทคโนโลยีการจัดการ (การประกอบการ)",
                "วิทยาการสารสนเทศบัณฑิตแบบก้าวหน้า").forEach(name -> {
                    Institute institute = instituteRepository.findByName("สำนักวิชาเทคโนโลยีสังคม").get();
                    Major major = Major.builder().institute(institute).name(name).build();
                    majorRepository.save(major);
                });
        Stream.of("ยังไม่สังกัดสาขา-เทคโนโลยีการเกษตร", "เทคโนโลยีการผลิตพืช", "เทคโนโลยีการผลิตสัตว์",
                "เทคโนโลยีและนวัตกรรมทางสัตว์", "เทคโนโลยีอาหาร").forEach(name -> {
                    Institute institute = instituteRepository.findByName("สำนักวิชาเทคโนโลยีการเกษตร").get();
                    Major major = Major.builder().institute(institute).name(name).build();
                    majorRepository.save(major);
                });

        Stream.of("แพทยศาสตร์", "วิทยาศาสตร์การแพทย์").forEach(name -> {
            Institute institute = instituteRepository.findByName("สำนักวิชาแพทย์ศาสตร์").get();
            Major major = Major.builder().institute(institute).name(name).build();
            majorRepository.save(major);
        });

        Stream.of("ยังไม่สังกัดสาขา-วิศวกรรมศาสตร์", "วิศวกรรมการผลิต", "วิศวกรรมเกษตร", "วิศวกรรมขนส่ง",
                "วิศวกรรมคอมพิวเตอร์", "วิศวกรรมเคมี", "วิศวกรรมเครื่องกล", "วิศวกรรมเซรามิก", "วิศวกรรมโทรคมนาคม",
                "วิศวกรรมพอลิเมอร์", "วิศวกรรมไฟฟ้า", "วิศวกรรมโยธา", "วิศวกรรมโลหการ", "วิศวกรรมสิ่งแวดล้อม",
                "วิศวกรรมอิเล็กทรอนิกส์", "วิศวกรรมอุตสาหการ", "เทคโนโลยีธรณี", "วิศวกรรมยานยนต์",
                "วิศวกรรมเมคคาทรอนิกส์", "วิศวกรรมอากาศยาน", "วิศวกรรมเกษตรและอาหาร", "วิศวกรรมขนส่งและโลจิสติกส์",
                "วิศวกรรมธรณี", "วิศวกรรมออกแบบผลิตภัณฑ์", "วิศวกรรมเครื่องมือ", "วิศวกรรมปิโตรเลียมและเทคโนโลยีธรณี",
                "วิศวกรรมการผลิตอัตโนมัติและหุ่นยนต์").forEach(name -> {
                    Institute institute = instituteRepository.findByName("สำนักวิชาวิศวกรรมศาสตร์").get();
                    Major major = Major.builder().institute(institute).name(name).build();
                    majorRepository.save(major);
                });

        Stream.of("พยาบาลศาสตรบัณฑิต").forEach(name -> {
            Institute institute = instituteRepository.findByName("สำนักวิชาพยาบาลศาสตร์").get();
            Major major = Major.builder().institute(institute).name(name).build();
            majorRepository.save(major);
        });

        Stream.of("ทันตแพทยศาสตรบัณฑิต").forEach(name -> {
            Institute institute = instituteRepository.findByName("สำนักวิชาทันตแพทย์ศาสตร์").get();
            Major major = Major.builder().institute(institute).name(name).build();
            majorRepository.save(major);
        });

        Stream.of("ยังไม่สังกัดสาขา-สาธารณสุข", "อาชีวอนามัยและความปลอดภัย", "อนามัยสิ่งแวดล้อม").forEach(name -> {
            Institute institute = instituteRepository.findByName("สำนักวิชาสาธารณสุขศาสตร์").get();
            Major major = Major.builder().institute(institute).name(name).build();
            majorRepository.save(major);
        });

        Course course1 = Course.builder().courseCode("523211")
                .major(majorRepository.findByName("วิศวกรรมคอมพิวเตอร์").get())
                .name("DATABASE SYSTEM")
                .weight(4)
                .build();
        courseRepository.save(course1);

        Course course2 = Course.builder().courseCode("523231")
                .major(majorRepository.findByName("วิศวกรรมคอมพิวเตอร์").get())
                .name("DATA STRUCTURES AND ALGORITHMS")
                .weight(4)
                .build();
        courseRepository.save(course2);

        Course course3 = Course.builder().courseCode("523232")
                .major(majorRepository.findByName("วิศวกรรมคอมพิวเตอร์").get())
                .name("OBJECT-ORIENTED TECHNOLOGY")
                .weight(4)
                .build();
        courseRepository.save(course3);

        Lecturer lecturer = Lecturer.builder()
                    .email("lecturer_1@sut.ac.th")
                    .gender(genderRepository.findById(1L).get())
                    .lecturerCode("A0001")
                    .major(majorRepository.findByName("วิศวกรรมคอมพิวเตอร์").get())
                    .name("ผศ.ดร.อนันตกิจ ดวงดี")
                    .password("123456789")
                    .personalId("1896655748447")
                    .courses(Arrays.asList(course1, course2))
                    .tel("0988857444").build();
        lecturerRepository.save(lecturer);
    }

}