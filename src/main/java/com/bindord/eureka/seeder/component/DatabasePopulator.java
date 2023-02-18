package com.bindord.eureka.seeder.component;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabasePopulator {

    public static final String POPULATE_INSERT_SCRIPTS = "1";

    @Value("${db.tables.populate}")
    private String populateScripts;

    @Value("${var.database.schema}")
    private String PG_DATABASE_SCHEMA;

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        Flyway.configure().dataSource(dataSource)
                .baselineOnMigrate(true)
                .locations("classpath:/db/migration")
                .schemas(PG_DATABASE_SCHEMA).load().migrate();

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        if (POPULATE_INSERT_SCRIPTS.equals(populateScripts)) {
            resourceDatabasePopulator.addScript(new ClassPathResource("db/inserts/insert_country.sql"));
            resourceDatabasePopulator.addScript(new ClassPathResource("db/inserts/insert_district.sql"));
            resourceDatabasePopulator.addScript(new ClassPathResource("db/inserts/insert_profession.sql"));
            resourceDatabasePopulator.addScript(new ClassPathResource("db/inserts/insert_specialization.sql"));
            resourceDatabasePopulator.addScript(new ClassPathResource("db/inserts/insert_bank.sql"));
        }
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
