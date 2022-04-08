package com.example.springredisson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRedissonApplicationTests {

    @Test
    void contextLoads() {
        String mobile = "-1234567890-1";
        if (mobile.matches("^([0-9-]{11})|([0-9-]{12})$")) {
            System.out.println("123");
        } else {
            System.out.println("456");
        }
    }


}
