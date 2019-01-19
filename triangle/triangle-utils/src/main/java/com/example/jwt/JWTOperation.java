package com.example.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author wangqing
 */
public interface JWTOperation {

    /**
     * 获取token
     * @param identity
     * @return
     */
    String getToken(String identity);

    /**
     * 验证token
     * @param token
     * @throws InvalidTokenException
     */
    DecodedJWT verify(String token);

    /**
     * 刷新token
     * @param identity
     * @return
     */
    String refreshToken(String identity);

    /**
     * 获取identity标识
     * @param token
     * @return
     */
    String getIdentity(String token);
}
