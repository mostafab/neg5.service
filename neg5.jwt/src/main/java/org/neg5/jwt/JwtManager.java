package org.neg5.jwt;

public interface JwtManager {

    String buildJwt(JwtData data);

    JwtData readJwt(String token);

}
