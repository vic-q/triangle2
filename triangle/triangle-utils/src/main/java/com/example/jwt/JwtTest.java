package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangqing
 */
public class JwtTest {

    private String issuer = "wangqing";

    private String subject = "jwt";

    private String secret = "123456";

    public String getToken() {

        final Algorithm algorithm = Algorithm.HMAC256(secret);

        final Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("owner", "auth0");
        headerClaims.put("version", "1.0");

        final String token = JWT.create().withHeader(headerClaims)
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withSubject(subject)
                .withArrayClaim("role", new String[]{"1", "2"})
                .sign(algorithm);

        return token;
    }

    public DecodedJWT verifyToken(String token) {
        final Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt;
    }

    public static void main(String[] args) {
        JwtTest jwtTest = new JwtTest();
        String token = jwtTest.getToken();
        System.out.println(token);

        DecodedJWT jwt = jwtTest.verifyToken(token);
        System.out.println("token=" + jwt.getToken());
        System.out.println("header=" + jwt.getHeader());
        System.out.println("payLoad=" + jwt.getPayload());
        System.out.println("signature=" + jwt.getSignature());
    }

}
