package neg5.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.neg5.module.Configuration;
import org.neg5.module.EnvironmentBackedConfig;

import javax.sql.DataSource;

public class Neg5DatabaseConfiguration {

    private static final String USERNAME_PROP = "neg5.username";
    private static final String PASSWORD_PROP = "neg5.password";
    private static final String JDBC_URL_PROP = "neg5.jdbc_url";
    private static final String CONNECTION_POOL_SIZE_PROP = "neg5.maxPoolSize";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final Configuration CONFIG = new EnvironmentBackedConfig();

    public static DataSource getDataSource() {
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