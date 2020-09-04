package neg5.db.flyway;

import com.google.inject.Inject;
import org.flywaydb.core.Flyway;

public class Neg5DatabaseMigrator {

    private final Flyway flyway;

    @Inject
    protected Neg5DatabaseMigrator(Flyway flyway) {
        this.flyway = flyway;
    }

    public void migrateDatabase() {
        flyway.migrate();
    }
}
