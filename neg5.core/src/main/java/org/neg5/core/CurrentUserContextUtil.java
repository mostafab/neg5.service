package org.neg5.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.neg5.jwt.JwtManager;
import org.neg5.jwt.JwtData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;

@Singleton
public class CurrentUserContextUtil {

    @Inject private JwtManager jwtManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserContextUtil.class);

    public UserData getUserData(String token) {
        try {
            JwtData jwtData = jwtManager.readJwt(token);
            return new UserData(jwtData.getClaim("username", String.class));
        } catch (Exception e) {
            LOGGER.error("Encountered exception attempting to get user data", e);
            throw e;
        }
    }
}
