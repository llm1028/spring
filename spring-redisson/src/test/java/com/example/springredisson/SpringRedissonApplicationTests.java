package com.example.springredisson;

import com.gome.architect.idgnrt.IDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringRedissonApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        String mobile = "-1234567890-1";
        if (mobile.matches("^([0-9-]{11})|([0-9-]{12})$")) {
            System.out.println("123");
        } else {
            System.out.println("456");
        }
    }

    @Test
    void testIdgenerator() {

        IDGenerator generator = applicationContext.getBean("idGenerator", IDGenerator.class);
        for (int i = 0; i < 100; i++) {
            System.out.println(generator.next());
        }
    }


}
