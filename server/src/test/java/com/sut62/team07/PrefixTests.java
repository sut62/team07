package com.sut62.team07;

import com.sut62.team07.repository.PrefixRepository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PrefixTests {

    private PrefixRepository prefixRepository;  

    @BeforeEach
    public void setup() {

    }
}