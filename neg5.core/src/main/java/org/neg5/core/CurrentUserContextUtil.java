package org.neg5.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.jwt.JwtManager;
import org.neg5.jwt.JwtData;

@Singleton
public class CurrentUserContextUtil {

    @Inject private JwtManager jwtManager;

    public UserData getUserData(String token) {
        if (token == null) {
            return null;
        }
        JwtData jwtData = jwtManager.readJwt(token);
        return new UserData(jwtData.getClaim("username", String.class));
    }
}
