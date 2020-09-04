package neg5.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.neg5.module.Configuration;
import org.neg5.module.EnvironmentBackedConfig;

import javax.sql.DataSource;

public class Neg5DatabaseConfiguration {

    private static final DataSource dataSource;
    private static final Configuration CONFIG;

    static {
        CONFIG = new EnvironmentBackedConfig();
        dataSource = initializeDataSource();
    }

    private static final String USERNAME_PROP = "NEG5_USERNAME";
    private static final String PASSWORD_PROP = "NEG5_PASSWORD";
    private static final String JDBC_URL_PROP = "NEG5_JDBC_URL";
    private static final String CONNECTION_POOL_SIZE_PROP = "NEG5_MAX_POOL_SIZE";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    public static DataSource getDataSource() {
        return dataSource;
    }

    private static DataSource initializeDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(CONFIG.getString(USERNAME_PROP));
        config.setPassword(CONFIG.getString(PASSWORD_PROP));
        config.setJdbcUrl(CONFIG.getString(JDBC_URL_PROP));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(CONFIG.getInt(CONNECTION_POOL_SIZE_PROP));
        config.setReadOnly(false);

        return new HikariDataSource(config);
    }
}