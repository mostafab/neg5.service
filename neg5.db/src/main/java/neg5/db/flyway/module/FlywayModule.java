package neg5.db.flyway.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import neg5.db.Neg5DatabaseConfiguration;
import org.flywaydb.core.Flyway;

public class FlywayModule extends AbstractModule {

    @Provides
    protected Flyway provideFlyway() {
        return Flyway.configure()
                .dataSource(Neg5DatabaseConfiguration.getDataSource())
                .baselineOnMigrate(true)
                .locations("classpath:migrations")
                .load();
    }
}
