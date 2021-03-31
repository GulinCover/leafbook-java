package org.leafbook.serviceGatewayApi.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.interfaces.RSAPrivateKey;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Component
public class CustomGatewayFilter implements GlobalFilter, Ordered {
    @Autowired
    private RSAPrivateKey privateKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
        ServerHttpRequest request;
        System.out.println("=============");
        if (Objects.isNull(authorization) || authorization.isEmpty()) {
            request = exchange.getRequest().mutate().header("userId","0").build();
        } else {
            try {
                String jwt = authorization.get(0);
                String[] jwtList = jwt.split("Bearer ");
                if (jwtList.length >= 2) {
                    jwt = jwtList[1];
                }

                RSADecrypter decrypter = new RSADecrypter(privateKey);
                EncryptedJWT ret = EncryptedJWT.parse(jwt);
                decrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
                ret.decrypt(decrypter);

                System.out.println(ret.getJWTClaimsSet().getSubject());
                System.out.println(ret.getJWTClaimsSet().getClaim("randomString"));
                System.out.println(ret.getJWTClaimsSet().getExpirationTime());
                System.out.println(ret.getJWTClaimsSet().getIssuer());
                request = exchange.getRequest().mutate().header("userId","321412").build();
            } catch (JOSEException | ParseException e) {
                e.printStackTrace();
                request = exchange.getRequest().mutate().header("userId","0").build();
            }

        }

        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
