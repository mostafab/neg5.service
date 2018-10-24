package org.neg5.module;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.sun.jnlp.PersistenceServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.neg5.core.ReadOnly;
import org.neg5.core.ReadWrite;
import org.neg5.daos.TournamentDAO;
import org.neg5.daos.TournamentMatchDAO;
import org.neg5.daos.TournamentPhaseDAO;
import org.neg5.daos.TournamentPlayerDAO;
import org.neg5.daos.TournamentTeamDAO;
import org.neg5.daos.TournamentTossupValueDAO;
import org.neg5.data.TournamentTeam;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteModule extends PrivateModule {

    public static final String READ_WRITE_PERSIST_SERVICE_PROP = "PersistService.readWrite";

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";
    private static final String CONNECTION_POOL_SIZE_PROP = "neg5.maxPoolSize";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String PERSISTENCE_UNIT_NAME = "org.neg5.data.RW";

    @Override
    protected void configure() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DATASOURCE, provideRWDataSource(new EnvironmentBackedSystemVariables()));
        install(new JpaPersistModule(PERSISTENCE_UNIT_NAME).properties(properties));

        bind(TournamentDAO.class).annotatedWith(ReadWrite.class).to(TournamentDAO.class);
        bind(TournamentMatchDAO.class).annotatedWith(ReadWrite.class).to(TournamentMatchDAO.class);
        bind(TournamentTossupValueDAO.class).annotatedWith(ReadWrite.class).to(TournamentTossupValueDAO.class);
        bind(TournamentPhaseDAO.class).annotatedWith(ReadWrite.class).to(TournamentPhaseDAO.class);
        bind(TournamentPlayerDAO.class).annotatedWith(ReadWrite.class).to(TournamentPlayerDAO.class);
        bind(TournamentTeamDAO.class).annotatedWith(ReadWrite.class).to(TournamentTeamDAO.class);

        expose(TournamentDAO.class).annotatedWith(ReadWrite.class);
        expose(TournamentMatchDAO.class).annotatedWith(ReadWrite.class);
        expose(TournamentTossupValueDAO.class).annotatedWith(ReadWrite.class);
        expose(TournamentPhaseDAO.class).annotatedWith(ReadWrite.class);
        expose(TournamentPlayerDAO.class).annotatedWith(ReadWrite.class);
        expose(TournamentTeamDAO.class).annotatedWith(ReadWrite.class);
    }

    @Named(READ_WRITE_PERSIST_SERVICE_PROP)
    @Provides
    @Exposed
    protected PersistService providePersistService(PersistService persistService) {
        return persistService;
    }

    private DataSource provideRWDataSource(SystemProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setUsername(properties.getString(USERNAME_PROP));
        config.setPassword(properties.getString(PASSWORD_PROP));
        config.setJdbcUrl(properties.getString(JDBC_URL_PROP));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(properties.getInt(CONNECTION_POOL_SIZE_PROP));
        config.setReadOnly(false);

        return new HikariDataSource(config);
    }
}
