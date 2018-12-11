package org.neg5.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.jwt.JwtManager;
import org.neg5.jwt.JwtData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CurrentUserContextUtil {

    @Inject private JwtManager jwtManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserContextUtil.class);

    public UserData getUserData(String token) {
        if (token == null) {
            return null;
        }
        try {
            JwtData jwtData = jwtManager.readJwt(token);
            return new UserData(jwtData.getClaim("username", String.class));
        } catch (Exception e) {
            LOGGER.error("Encountered exception attempting to get user data", e);
            return null;
        }
    }
}
