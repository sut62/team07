package com.sut62.team07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertNotEquals(found.isPresent(), false);
        assertEquals("A0001", found.get().getLecturerCode());
    }

    // @Test
    // void lecturerCodeMustBeNotNull() {
    //     Lecturer lecturer = Lecturer.builder()
    //             .email("gg@gmail.com")
    //             .lecturerCode(null)
    //             .name("John Doe")
    //             .password("12345678")
    //             .personalId("1234567890123")
    //             .tel("0987458745")
    //             .build();
        
    // }
}