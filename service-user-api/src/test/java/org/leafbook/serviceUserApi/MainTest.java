package org.leafbook.serviceUserApi;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.junit.jupiter.api.Test;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.dao.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MainTest {
    @Test
    void test01() {
        //$2a$31$1voz2UOuqqmYVeySFWfTvOPqu2qSebU0jOOIKrUmw2RlcxJkP22v2
        //$2a$31$dGph/VNPXwLQdYJp8A9HeeTz6xaf/PMjB3QLno3lutL8EX6KLgbVa
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(31, secureRandom);
        String dsadas = encoder.encode("12345678");
        System.out.println(dsadas);
        StringBuffer charSequence = new StringBuffer();
        charSequence.append("12345678");
    }

    private final static byte[] mod = {
            (byte) 177, (byte) 119, (byte) 33, (byte) 13, (byte) 164, (byte) 30, (byte) 108, (byte) 121,
            (byte) 207, (byte) 136, (byte) 107, (byte) 242, (byte) 12, (byte) 224, (byte) 19, (byte) 226,
            (byte) 198, (byte) 134, (byte) 17, (byte) 71, (byte) 173, (byte) 75, (byte) 42, (byte) 61,
            (byte) 48, (byte) 162, (byte) 206, (byte) 161, (byte) 97, (byte) 108, (byte) 185, (byte) 234,
            (byte) 226, (byte) 219, (byte) 118, (byte) 206, (byte) 118, (byte) 5, (byte) 169, (byte) 224,

            (byte) 60, (byte) 181, (byte) 90, (byte) 85, (byte) 51, (byte) 123, (byte) 6, (byte) 224,
            (byte) 4, (byte) 122, (byte) 29, (byte) 230, (byte) 151, (byte) 12, (byte) 244, (byte) 127,
            (byte) 121, (byte) 25, (byte) 4, (byte) 85, (byte) 220, (byte) 144, (byte) 215, (byte) 110,
            (byte) 130, (byte) 17, (byte) 68, (byte) 228, (byte) 129, (byte) 138, (byte) 7, (byte) 130,
            (byte) 231, (byte) 40, (byte) 212, (byte) 214, (byte) 17, (byte) 179, (byte) 28, (byte) 124,

            (byte) 151, (byte) 178, (byte) 207, (byte) 20, (byte) 14, (byte) 154, (byte) 222, (byte) 113,
            (byte) 176, (byte) 24, (byte) 198, (byte) 73, (byte) 211, (byte) 113, (byte) 9, (byte) 33,
            (byte) 178, (byte) 80, (byte) 13, (byte) 25, (byte) 21, (byte) 25, (byte) 153, (byte) 212,
            (byte) 206, (byte) 67, (byte) 154, (byte) 147, (byte) 70, (byte) 194, (byte) 192, (byte) 183,
            (byte) 160, (byte) 83, (byte) 98, (byte) 236, (byte) 175, (byte) 85, (byte) 23, (byte) 97,

            (byte) 75, (byte) 199, (byte) 177, (byte) 73, (byte) 145, (byte) 50, (byte) 253, (byte) 206,
            (byte) 32, (byte) 179, (byte) 254, (byte) 236, (byte) 190, (byte) 82, (byte) 73, (byte) 67,
            (byte) 129, (byte) 253, (byte) 252, (byte) 220, (byte) 108, (byte) 136, (byte) 138, (byte) 11,
            (byte) 192, (byte) 1, (byte) 36, (byte) 239, (byte) 228, (byte) 55, (byte) 81, (byte) 113,
            (byte) 17, (byte) 25, (byte) 140, (byte) 63, (byte) 239, (byte) 146, (byte) 3, (byte) 172,

            (byte) 96, (byte) 60, (byte) 227, (byte) 233, (byte) 64, (byte) 255, (byte) 224, (byte) 173,
            (byte) 225, (byte) 228, (byte) 229, (byte) 92, (byte) 112, (byte) 72, (byte) 99, (byte) 97,
            (byte) 26, (byte) 87, (byte) 187, (byte) 123, (byte) 46, (byte) 50, (byte) 90, (byte) 202,
            (byte) 117, (byte) 73, (byte) 10, (byte) 153, (byte) 47, (byte) 224, (byte) 178, (byte) 163,
            (byte) 77, (byte) 48, (byte) 46, (byte) 154, (byte) 33, (byte) 148, (byte) 34, (byte) 228,

            (byte) 33, (byte) 172, (byte) 216, (byte) 89, (byte) 46, (byte) 225, (byte) 127, (byte) 68,
            (byte) 146, (byte) 234, (byte) 30, (byte) 147, (byte) 54, (byte) 146, (byte) 5, (byte) 133,
            (byte) 45, (byte) 78, (byte) 254, (byte) 85, (byte) 55, (byte) 75, (byte) 213, (byte) 86,
            (byte) 194, (byte) 218, (byte) 215, (byte) 163, (byte) 189, (byte) 194, (byte) 54, (byte) 6,
            (byte) 83, (byte) 36, (byte) 18, (byte) 153, (byte) 53, (byte) 7, (byte) 48, (byte) 89,

            (byte) 35, (byte) 66, (byte) 144, (byte) 7, (byte) 65, (byte) 154, (byte) 13, (byte) 97,
            (byte) 75, (byte) 55, (byte) 230, (byte) 132, (byte) 3, (byte) 13, (byte) 239, (byte) 71};

    private static final byte[] exp = {1, 0, 1};

    private static final byte[] modPriv = {
            (byte) 84, (byte) 80, (byte) 150, (byte) 58, (byte) 165, (byte) 235, (byte) 242, (byte) 123,
            (byte) 217, (byte) 55, (byte) 38, (byte) 154, (byte) 36, (byte) 181, (byte) 221, (byte) 156,
            (byte) 211, (byte) 215, (byte) 100, (byte) 164, (byte) 90, (byte) 88, (byte) 40, (byte) 228,
            (byte) 83, (byte) 148, (byte) 54, (byte) 122, (byte) 4, (byte) 16, (byte) 165, (byte) 48,
            (byte) 76, (byte) 194, (byte) 26, (byte) 107, (byte) 51, (byte) 53, (byte) 179, (byte) 165,

            (byte) 31, (byte) 18, (byte) 198, (byte) 173, (byte) 78, (byte) 61, (byte) 56, (byte) 97,
            (byte) 252, (byte) 158, (byte) 140, (byte) 80, (byte) 63, (byte) 25, (byte) 223, (byte) 156,
            (byte) 36, (byte) 203, (byte) 214, (byte) 252, (byte) 120, (byte) 67, (byte) 180, (byte) 167,
            (byte) 3, (byte) 82, (byte) 243, (byte) 25, (byte) 97, (byte) 214, (byte) 83, (byte) 133,
            (byte) 69, (byte) 16, (byte) 104, (byte) 54, (byte) 160, (byte) 200, (byte) 41, (byte) 83,

            (byte) 164, (byte) 187, (byte) 70, (byte) 153, (byte) 111, (byte) 234, (byte) 242, (byte) 158,
            (byte) 175, (byte) 28, (byte) 198, (byte) 48, (byte) 211, (byte) 45, (byte) 148, (byte) 58,
            (byte) 23, (byte) 62, (byte) 227, (byte) 74, (byte) 52, (byte) 117, (byte) 42, (byte) 90,
            (byte) 41, (byte) 249, (byte) 130, (byte) 154, (byte) 80, (byte) 119, (byte) 61, (byte) 26,
            (byte) 193, (byte) 40, (byte) 125, (byte) 10, (byte) 152, (byte) 174, (byte) 227, (byte) 225,

            (byte) 205, (byte) 32, (byte) 62, (byte) 66, (byte) 6, (byte) 163, (byte) 100, (byte) 99,
            (byte) 219, (byte) 19, (byte) 253, (byte) 25, (byte) 105, (byte) 80, (byte) 201, (byte) 29,
            (byte) 252, (byte) 157, (byte) 237, (byte) 69, (byte) 1, (byte) 80, (byte) 171, (byte) 167,
            (byte) 20, (byte) 196, (byte) 156, (byte) 109, (byte) 249, (byte) 88, (byte) 0, (byte) 3,
            (byte) 152, (byte) 38, (byte) 165, (byte) 72, (byte) 87, (byte) 6, (byte) 152, (byte) 71,

            (byte) 156, (byte) 214, (byte) 16, (byte) 71, (byte) 30, (byte) 82, (byte) 51, (byte) 103,
            (byte) 76, (byte) 218, (byte) 63, (byte) 9, (byte) 84, (byte) 163, (byte) 249, (byte) 91,
            (byte) 215, (byte) 44, (byte) 238, (byte) 85, (byte) 101, (byte) 240, (byte) 148, (byte) 1,
            (byte) 82, (byte) 224, (byte) 91, (byte) 135, (byte) 105, (byte) 127, (byte) 84, (byte) 171,
            (byte) 181, (byte) 152, (byte) 210, (byte) 183, (byte) 126, (byte) 24, (byte) 46, (byte) 196,

            (byte) 90, (byte) 173, (byte) 38, (byte) 245, (byte) 219, (byte) 186, (byte) 222, (byte) 27,
            (byte) 240, (byte) 212, (byte) 194, (byte) 15, (byte) 66, (byte) 135, (byte) 226, (byte) 178,
            (byte) 190, (byte) 52, (byte) 245, (byte) 74, (byte) 65, (byte) 224, (byte) 81, (byte) 100,
            (byte) 85, (byte) 25, (byte) 204, (byte) 165, (byte) 203, (byte) 187, (byte) 175, (byte) 84,
            (byte) 100, (byte) 82, (byte) 15, (byte) 11, (byte) 23, (byte) 202, (byte) 151, (byte) 107,

            (byte) 54, (byte) 41, (byte) 207, (byte) 3, (byte) 136, (byte) 229, (byte) 134, (byte) 131,
            (byte) 93, (byte) 139, (byte) 50, (byte) 182, (byte) 204, (byte) 93, (byte) 130, (byte) 89};

    static RSAPublicKey publicKey;
    static RSAPrivateKey privateKey;

    static {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(1, mod), new BigInteger(1, exp));
            RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(1, mod), new BigInteger(1, modPriv));
            publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test02() throws JOSEException, ParseException {
        String iss = "https://openid.net";
        String sub = "alice";
        List<String> aud = new ArrayList<>();
        aud.add("https://app-one.com");
        aud.add("https://app-two.com");
        final Date NOW = new Date(new Date().getTime() / 1000 * 1000);
        Date exp = new Date(NOW.getTime() + 1000 * 60 * 10);
        Date nbf = NOW;
        Date iat = NOW;
        String jti = UUID.randomUUID().toString();
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer("alex")
                .subject("alex")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000 * 60))
                .claim("randomString", "abcdefghijklmnopqrstuvwxyz")
                .build();
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);


        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);
        RSAEncrypter encrypter = new RSAEncrypter(publicKey);
        encrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());

        jwt.encrypt(encrypter);

        String s = jwt.serialize();

        //eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.no4hKBVE4-SN-IriLLy6GBy82dRW0aA_HMYfuKLxIqs5epLWy-UiPYnQe-2utSlFYreFTMHxlMeqYF-B3Qu7bA7DGF1apISmxtJHvGQme07paGPx3suEtEiFGS5mUEAExkwqZQRrsCLCoT_Cdg-6glZY9TTREXUb0Xl2USLL0wNh_0df7vwr0nnwqOCq8RqC2Y5v46aZVR3CCMt5258dyeEEmq5KMiapyzMsyaMmMw_UPS_jLwmzxHfgCgPj38gpTJHwS_fvHuxHYPrIpyPCvERfhNDUULdYvpPZ5X9VXPZok12u2Fjvd0BYFkCXxRYj3zm1wuVUrXBQFPd1mceHZA.-CpiHIvaN3I4wod7.C4yAE1E-LR6WpokRwzx1nsGazDbgkEi4LOyjV6hSpUHzyVevzHFl7YopcjA-r3Xljl3e_fIUwGPIY0qDvF5OItEGN9IcbcGTGC-q-tBxVK0ExZsd0i25Es8i-At66PxBxwcFRSV7HNtUf0mCD_YKTvku8-TP8GbYFCtrxekiEmwladq7_wZHxa3GocSYCE0DOzw2x-p4IBnslOlPUPttlPpd1ck6Y6RsNJyrcDMxVT4JpPG_hj_-RLXEWatCKs-CnZfX1Q.fPhB2UJy9FUe7mLv473JoA
        System.out.println(s);

//        jwt = EncryptedJWT.parse(s);
//        RSADecrypter decrypter = new RSADecrypter(privateKey);
//        decrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
//        jwt.decrypt(decrypter);
//        System.out.println(jwt.getJWTClaimsSet().getSubject());
    }


    @Test
    void test03() throws ParseException, JOSEException, InvalidKeyException {
        String s = "eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.qOHcTjhFnfb-Tj_tL0dVwFW98IkKtqGh4CKI1T9kynpQ9HsLz9Lkr4O8Bc2L4Sk33ZUDeg7vzmd39Jnk6q6HRVmi1KJtspD471H7Z0RpQA-mjS2aa3LOHs59gSVjDrwAh9zphIkYSnHnIbfdMz_n6mNOt2G-mQAtFB9XQk2i61I6n1bb4Bmk1SCCSMM93gsfdJUp_o7fvHi5ykqhaUErU0gNrDGLH8XaDYmThOn_CKVRzcNveNXGBDgQnTDZ9i_3SCGq48nz77wWzBR27nvL1kCk6UR1xxUfzyUUymBB8568617rtib5-F7xo2cMyS_gqhrxy6FvDWtQd23mNqm4vQ.1Wm_fQPjbpg8rThh.6Rf5XaTJuPxBU3GGGf2k8Jb91gDuXQg2ojxiMal6ccIw7v87QcMtWaSCLupoJyakvGkmwbN4zEKJ8asPyzku3gRJGDODUK-ECRjckbBOSXm9G7-hHAEeZA.tpJJUMSoqQkDIfGZDWQdyQ";
        RSADecrypter decrypter = new RSADecrypter(privateKey);
        decrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
        EncryptedJWT jwt = EncryptedJWT.parse(s);
        jwt.decrypt(decrypter);

        System.out.println(jwt.getJWTClaimsSet().getSubject());

    }


    @Autowired
    private UserModelMapper userModelMapper;

    @Test
    void test04() {
        UserModel userModel = new UserModel();
        userModel.setId(141775024993337344L);
        userModel.setUsername("alex");
        userModel.setEmail("1208625327@qq.com");
        userModel.setPassword("abcssadasdqw");
        userModel.setVersion(1);
//        System.out.println(userModelMapper.insert(userModel));
        userModelMapper.updateUserPhone(141775024993337344L, "123456789", new Date().getTime());
//        userModelMapper.updateById(userModel);
    }
}
