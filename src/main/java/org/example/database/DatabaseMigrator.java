package org.example.database;

public class DatabaseMigrator {

    Configuration configuration = new Configuration();

    public void migrate() {
        org.flywaydb.core.Flyway flyway = org.flywaydb.core.Flyway.configure()
                .dataSource(configuration.getUrl(),
                        configuration.getUser(),
                        configuration.getPassword()).load();
        flyway.migrate();
    }
}
