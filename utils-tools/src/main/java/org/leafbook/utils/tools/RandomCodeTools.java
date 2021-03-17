package org.leafbook.utils.tools;

import java.util.Random;

public class RandomCodeTools {
    public static String generateRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; ++i) {
            int alpha = new Random().nextInt(24)+65;
            int number = new Random().nextInt(10)+48;

            stringBuilder.append((char) (new Random().nextBoolean() ? alpha : number));
        }
        return stringBuilder.toString();
    }
}
