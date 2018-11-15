package org.neg5.jwt.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.neg5.jwt.JwtManager;
import org.neg5.jwt.SigningKeyBackedJwtManagerImpl;
import org.neg5.module.Configuration;

public class JwtSigningModule extends AbstractModule {

    public static final String JWT_SECRET_PROP_NAME = "JwtSigningModule.SIGNING_SECRET";

    private final String secretPropName;

    public JwtSigningModule(String secretPropName) {
        this.secretPropName = secretPropName;
    }

    @Override
    protected void configure() {
        bind(JwtManager.class).to(SigningKeyBackedJwtManagerImpl.class);
    }

    @Provides
    @Named(JWT_SECRET_PROP_NAME)
    protected String provideSigningSecret(Configuration configuration) {
        return configuration.getString(secretPropName);
    }
}
