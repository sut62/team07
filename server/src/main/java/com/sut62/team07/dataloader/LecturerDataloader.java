package com.sut62.team07.dataloader;

import java.util.stream.Stream;

import com.sut62.team07.entity.Course;
import com.sut62.team07.entity.Gender;
import com.sut62.team07.entity.Institute;
import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Prefix;
import com.sut62.team07.entity.ProgramInfo;
import com.sut62.team07.entity.RegistrationOfficer;
import com.sut62.team07.entity.Section;
import com.sut62.team07.entity.Semester;
import com.sut62.team07.entity.Trimester;
import com.sut62.team07.entity.Type;
import com.sut62.team07.entity.Year;
import com.sut62.team07.repository.CourseRepository;
import com.sut62.team07.repository.GenderRepository;
import com.sut62.team07.repository.InstituteRepository;
import com.sut62.team07.repository.LecturerRepository;
import com.sut62.team07.repository.MajorRepository;
import com.sut62.team07.repository.PrefixRepository;
import com.sut62.team07.repository.ProgramInfoRepository;
import com.sut62.team07.repository.RegistrationOfficerRepository;
import com.sut62.team07.repository.SectionRepository;
import com.sut62.team07.repository.SemesterRepository;
import com.sut62.team07.repository.TrimesterRepository;
import com.sut62.team07.repository.TypeRepository;
import com.sut62.team07.repository.YearRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LecturerDataloader implements ApplicationRunner {

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private RegistrationOfficerRepository registrationOfficerRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ProgramInfoRepository programInfoRepository;

    @Autowired
    private TrimesterRepository trimesterRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("ชาย", "หญิง").forEach(sex -> {
            Gender gender = Gender.builder().name(sex).build();
            genderRepository.save(gender);
        });

        Stream.of("นาย", "นาง", "นางสาว").forEach(p -> {
            Prefix prefix = Prefix.builder().name(p).build();
            prefixRepository.save(prefix);
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

        Stream.of("ภาคเรียนที่ 1", "ภาคเรียนที่ 2", "ภาคเรียนที่ 3").forEach(name -> {
            Trimester trimester = new Trimester();
            trimester.setName(name);
            trimesterRepository.save(trimester);
        });

        Stream.of("วิชาภาคบังคับ", "วิชาเลือกเสรี", "วิชาเลือกบังคับ", "วิชาศึกษาทั่วไปแบบเลือก").forEach(name -> {
            Type type = new Type();
            type.setName(name);
            typeRepository.save(type);
        });

        Stream.of("2554", "2555", "2556", "2557", "2558", "2559", "2560", "2561", "2562").forEach(name -> {
            ProgramInfo programInfo = new ProgramInfo();
            programInfo.setName(name);
            programInfoRepository.save(programInfo);
        });

        Stream.of("ชั้นปีการศึกษาที่ 1", "ชั้นปีการศึกษาที่ 2", "ชั้นปีการศึกษาที่ 3", "ชั้นปีการศึกษาที่ 4")
                .forEach(name -> {
                    Year year = new Year();
                    year.setName(name);
                    yearRepository.save(year);
                });

        Stream.of("1", "2", "3").forEach(name -> { // aum
            Semester semester = new Semester(); // สร้าง Object Semester
            semester.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Semester
            semesterRepository.save(semester); // บันทึก Objcet ชื่อ Semester
        });

        RegistrationOfficer registrationOfficer = RegistrationOfficer.builder().gender(genderRepository.getOne(1L))
                .name("Jack Daw").officerCode("R0001").password("password1").prefix(prefixRepository.getOne(1L))

                .build();

        registrationOfficerRepository.save(registrationOfficer);

        Lecturer lecturer1 = Lecturer.builder().email("gg@gmail.com").lecturerCode("A0001").name("Tom")
                .password("12345678").personalId("1234567890123").tel("0123456789")

                .createdBy(registrationOfficerRepository.getOne(1L)).gender(genderRepository.getOne(1L))
                .major(majorRepository.getOne(1L)).prefix(prefixRepository.getOne(1L))

                .build();
        lecturer1 = lecturerRepository.save(lecturer1);

        Lecturer lecturer2 = Lecturer.builder().email("eieiza@gmail.com").lecturerCode("A0002").name("Sompong")
                .password("99999999").personalId("2345678901234").tel("0888888888")

                .createdBy(registrationOfficerRepository.getOne(1L)).gender(genderRepository.getOne(1L))
                .major(majorRepository.getOne(1L)).prefix(prefixRepository.getOne(1L))

                .build();
        lecturer2 = lecturerRepository.save(lecturer2);

        Course course1 = Course.builder()
                .courseCode("523331")
                .credit(4)
                .name("SYSTEM ANALYSIS AND DESIGN")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course1 = courseRepository.save(course1);

        Course course2 = Course.builder()
                .courseCode("523371")
                .credit(4)
                .name("MICROPROCESSORS")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course2 = courseRepository.save(course2);

        Course course3 = Course.builder()
                .courseCode("523301")
                .credit(2)
                .name("COMPUTER STATISTICS")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course3 = courseRepository.save(course3);

        Course course4 = Course.builder()
                .courseCode("523351")
                .credit(4)
                .name("FORMAL METHODS AND COMPUTABILITY")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course4 = courseRepository.save(course4);

        Course course5 = Course.builder()
                .courseCode("202212")
                .credit(3)
                .name("MAN, ECONOMY AND DEVELOPMENT")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course5 = courseRepository.save(course5);

        Course course6 = Course.builder()
                .courseCode("523232")
                .credit(4)
                .name("OBJECT-ORIENTED TECHNOLOGY")
                .lecturer(lecturer1)
                .programInfo(programInfoRepository.getOne(1L))
                .trimester(trimesterRepository.getOne(1L))
                .type(typeRepository.getOne(1L)).build();
        course6 = courseRepository.save(course6);

        Course course7 = Course.builder()
                .courseCode("202111")
                .credit(4)
                .name("THAI FOR COMMUNICATION")
                .lecturer(lecturer2)
                .programInfo(programInfoRepository.getOne(7L))
                .trimester(trimesterRepository.getOne(2L))
                .type(typeRepository.getOne(4L)).build();
        course7 = courseRepository.save(course7);

        Course course8 = Course.builder()
        .courseCode("202324")
        .credit(4)
        .name("PLURI-CULTURAL THAI STUDIES")
        .lecturer(lecturer2)
        .programInfo(programInfoRepository.getOne(7L))
        .trimester(trimesterRepository.getOne(2L))
        .type(typeRepository.getOne(4L)).build();
        course8 = courseRepository.save(course8);

        // sa
        Section section1 = new Section();
        section1.setSec("1");
        section1.setSubInSec(course1);
        section1.setTime("Tu12:00-13:00 B4101, We17:00-20:00 Lab_Com1");
        section1 = sectionRepository.save(section1);

        Section sec2 = new Section();
        sec2.setSec("2");
        sec2.setSubInSec(course1);
        sec2.setTime("Tu12:00-13:00 B4101, We17:00-20:00 Lab_Com2");
        sec2 = sectionRepository.save(sec2);

        Section sec3 = new Section();
        sec3.setSec("3");
        sec3.setSubInSec(course1);
        sec3.setTime("Tu12:00-13:00 B4101, We17:00-20:00 Lab_Com3");
        sec3 = sectionRepository.save(sec3);

        // micro
        Section sec1_2 = new Section();
        sec1_2.setSec("1");
        sec1_2.setSubInSec(course2);
        sec1_2.setTime("Tu13:00-15:00 B1215, We13:00-15:00 B1215");
        sec1_2 = sectionRepository.save(sec1_2);

        Section sec2_2 = new Section();
        sec2_2.setSec("2");
        sec2_2.setSubInSec(course2);
        sec2_2.setTime("Tu13:00-15:00 B1231, We13:00-15:00 B1125");
        sec2_2 = sectionRepository.save(sec2_2);

        Section sec3_2 = new Section();
        sec3_2.setSec("3");
        sec3_2.setSubInSec(course2);
        sec3_2.setTime("Tu13:00-15:00 B1127, We13:00-15:00 B1127	");
        sec3_2 = sectionRepository.save(sec3_2);

        Section sec4_2 = new Section();
        sec4_2.setSec("4");
        sec4_2.setSubInSec(course2);
        sec4_2.setTime("Tu13:00-15:00 B1119, We13:00-15:00 B1119	");
        sec4_2 = sectionRepository.save(sec4_2);

        // com stat
        Section sec1_3 = new Section();
        sec1_3.setSec("1");
        sec1_3.setSubInSec(course3);
        sec1_3.setTime("Th09:00-12:00 B1211, Fr13:00-16:00 F11-422.Software");
        sec1_3 = sectionRepository.save(sec1_3);

        Section sec2_3 = new Section();
        sec2_3.setSec("2");
        sec2_3.setSubInSec(course3);
        sec2_3.setTime("Th09:00-12:00 B1211, Fr16:00-18:00 F11-422.Software");
        sec2_3 = sectionRepository.save(sec2_3);

        // formal
        Section sec1_4 = new Section();
        sec1_4.setSec("1");
        sec1_4.setSubInSec(course4);
        sec1_4.setTime("Th09:00-12:00 B1211, Fr13:00-15:00 F11-422.Software");
        sec1_4 = sectionRepository.save(sec1_4);

        Section sec2_4 = new Section();
        sec2_4.setSec("2");
        sec2_4.setSubInSec(course4);
        sec2_4.setTime("Tu12:00-13:00 B4101, We17:00-20:00 Lab_Com3");
        sec2_4 = sectionRepository.save(sec2_4);

        // man eco
        Section sec1_5 = new Section();
        sec1_5.setSec("1");
        sec1_5.setSubInSec(course5);
        sec1_5.setTime("Mo10:00-12:00 B1127, We13:00-15:00 B1121");
        sec1_5 = sectionRepository.save(sec1_5);

        // java
        Section sec1_6 = new Section();
        sec1_6.setSec("1");
        sec1_6.setSubInSec(course6);
        sec1_6.setTime("Mo09:00-12:00 F11-422.Software, Mo13:00-15:00 F11-422.Software");
        sec1_6 = sectionRepository.save(sec1_6);

    };

}