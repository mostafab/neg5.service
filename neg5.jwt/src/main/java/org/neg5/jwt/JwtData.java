package org.neg5.jwt;

import java.util.Map;

public class JwtData {

    private final Map<String, Object> claimsProperties;

    JwtData(Map<String, Object> claimsProperties) {
        this.claimsProperties = claimsProperties;
    }

    public Map<String, Object> getClaims() {
        return claimsProperties;
    }

    public <T> T getClaim(String key, Class<T> claimClazz) {
        return (T) claimsProperties.get(key);
    }
}
