package org.leafbook.utils.tools;

import java.util.Objects;
import java.util.regex.Pattern;

public class VerifyTools {
    public static boolean EmailVerify(String email) {
        if (Objects.isNull(email) || "".equals(email)) return false;
        String pattern = "\\w+@(\\w+.)+[a-z]{2,3}";
        Pattern compile = Pattern.compile(pattern);
        return compile.matcher(email).find();
    }

    public static boolean PasswordVerify(String password) {
        if (Objects.isNull(password) || "".equals(password)) return false;
        if (password.length() > 20 || password.length() < 8) return false;

        boolean upperAlphaFlag = false;
        boolean lowerAlphaFlag = false;
        boolean numberFlag = false;

        for (char c : password.toCharArray()) {
            if ((byte)c >= 48 && (byte)c <= 57) numberFlag = true;
            if ((byte)c >= 65 && (byte)c <= 90) upperAlphaFlag = true;
            if ((byte)c >= 97 && (byte)c <= 122) lowerAlphaFlag = true;
        }

        return upperAlphaFlag && lowerAlphaFlag && numberFlag;
    }

    public static boolean EmailCodeVerify(String code) {
        if (Objects.isNull(code) || "".equals(code)) return false;
        return code.length() == 6;
    }
}
