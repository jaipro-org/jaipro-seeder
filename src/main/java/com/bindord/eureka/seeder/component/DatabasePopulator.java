package com.bindord.eureka.seeder.component;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class DatabasePopulator {

    public static final String POPULATE_INSERT_SCRIPTS = "1";

    @Value("${db.tables.populate}")
    private String populateScripts;

    @Value("${var.database.schema}")
    private String PG_DATABASE_SCHEMA;

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) throws IOException {
        Flyway.configure().dataSource(dataSource)
                .baselineOnMigrate(true)
                .locations("classpath:/db/migration")
                .schemas(PG_DATABASE_SCHEMA).load().migrate();

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        if (POPULATE_INSERT_SCRIPTS.equals(populateScripts)) {

            File insertFiles = new ClassPathResource("db/inserts").getFile();
            File[] pgFunctions = insertFiles.listFiles();
            List<String> filePaths = new ArrayList<>(pgFunctions.length);
            for(File file: pgFunctions) {
                filePaths.add(file.getAbsoluteFile().getPath());
            }
            Collections.sort(filePaths);
            for (int i = 0; i < Objects.requireNonNull(pgFunctions).length; i++) {
                resourceDatabasePopulator
                        .addScript(
                                new PathResource(filePaths.get(i)));
            }
        }
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
