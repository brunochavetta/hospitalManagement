package com.example.laboratory4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.data.mongodb.uri=mongodb://localhost:27017/Hospital")
public class Laboratory4ApplicationTests {
    @Test
    void contextLoads() {
    }
}

