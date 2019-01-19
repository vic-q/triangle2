package com.example.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

/**
 * @author wangqing
 */
public final class JWT implements JWTOperation {

    /**
     * 发布方
     */
    private final String iss = JWTConstants.ISS;

    /**
     * 主题
     */
    private final String sub = JWTConstants.SUBJECT;

    /**
     * token到期时间
     */
    private final Date exp;

    /**
     * token发布时间
     */
    private final Date iat;

    /**
     * 密匙
     */
    private final String secret;

    /**
     * token header
     */
    private final Map<String, Object> header;

    /**
     * 身份标识
     */
    private static final String IDENTITY = "identity";

    public JWT(Date exp, String secret, Map<String, Object> header) {
        this.iat = new Date();
        this.exp = exp;
        this.secret = secret;
        this.header = header;
    }

    @Override
    public String getToken(String identity) {
        final Algorithm algorithm = Algorithm.HMAC256(secret);
        return com.auth0.jwt.JWT.create()
                .withIssuer(iss)
                .withHeader(header)
                .withIssuedAt(iat)
                .withSubject(sub)
                .withExpiresAt(exp)
                .withClaim(IDENTITY, identity)
                .sign(algorithm);
    }

    @Override
    public String refreshToken(String identity) {
        return null;
    }

    @Override
    public DecodedJWT verify(String token) {
        final Algorithm algorithm = Algorithm.HMAC256(secret);
        final JWTVerifier jwtVerifier = com.auth0.jwt.JWT.require(algorithm).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Invalid token");
        } catch (Exception e) {
            throw new InvalidTokenException("Invalid token");
        }

        verifyExpireAt(decodedJWT);

        return decodedJWT;
    }

    @Override
    public String getIdentity(String token) {
        final DecodedJWT decodedJWT = verify(token);
        final Claim claim = decodedJWT.getClaim(IDENTITY);
        if (claim.isNull()) {
            throw new InvalidTokenException("Get token failed");
        }
        return claim.asString();
    }

    private void verifyExpireAt(DecodedJWT decodedJWT) {
        final Date expireAt = decodedJWT.getExpiresAt();
        if (expireAt == null || expireAt.before(new Date())) {
            throw new InvalidTokenException("Token expired");
        }
    }

}
