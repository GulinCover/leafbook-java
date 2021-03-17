package org.leafbook.serviceUserApi;

import org.junit.jupiter.api.Test;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainTest {
    @Test
    void test01() {
        System.out.println(RandomCodeTools.generateRandomCode());
//        System.out.println((char)48);
    }
}
