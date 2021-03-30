package org.leafbook.serviceUserApi;

import org.junit.jupiter.api.Test;
import org.leafbook.utils.tools.RandomCodeTools;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@SpringBootTest
public class MainTest {
    @Test
    void test01() {
        //$2a$31$1voz2UOuqqmYVeySFWfTvOPqu2qSebU0jOOIKrUmw2RlcxJkP22v2
        //$2a$31$dGph/VNPXwLQdYJp8A9HeeTz6xaf/PMjB3QLno3lutL8EX6KLgbVa
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(31,secureRandom);
        String dsadas = encoder.encode("12345678");
        System.out.println(dsadas);
        StringBuffer charSequence = new StringBuffer();
        charSequence.append("12345678");
        System.out.println(encoder.matches(charSequence, "$2a$31$LcXnP9OEqLjzhUt98pKiJudlVbujX.9TXQAQrvoS0M57zYILRMiHu"));
    }
}
