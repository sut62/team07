package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.validation.Validator;

import com.sut62.team07.entity.Lecturer;
import com.sut62.team07.repository.LecturerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LecturerTests {

    // @Autowired
    // private Validator validator;

    @Autowired
    private LecturerRepository lecturerRepository;

    @BeforeEach
    public void setup() {

    }

    @Test
    void lecturerCodeShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("A0001", found.get().getLecturerCode());
    }

    @Test
    void emailShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("gg@gmail.com", found.get().getEmail());
    }

    @Test
    void nameShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("John Doe", found.get().getName());
    }

    @Test
    void passwordShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("12345678", found.get().getPassword());
    }

    @Test
    void personalIdShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("1234567890123", found.get().getPersonalId());
    }

    @Test
    void telIdShouldBeOK() {
        Lecturer lecturer = Lecturer.builder()
                .email("gg@gmail.com")
                .lecturerCode("A0001")
                .name("John Doe")
                .password("12345678")
                .personalId("1234567890123")
                .tel("0987458745")
                .build();
        lecturer = lecturerRepository.saveAndFlush(lecturer);
        Optional<Lecturer> found = lecturerRepository.findById(lecturer.getId());
        assertEquals("0987458745", found.get().getTel());
    }
}