package com.atl.order_detail_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;

@Configuration
public class BeanDebeziumConfiguration {
    @Bean
    public io.debezium.config.Configuration orderDetailConnector(Environment env) throws IOException {
        return io.debezium.config.Configuration.create()
                .with("name", "order-info")
                .with("topic.prefix", "order-info-topic")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", File.createTempFile("offsets_", ".dat").getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", env.getProperty("spring.r2dbc.properties.hostname"))
                .with("database.port", env.getProperty("spring.r2dbc.properties.port"))
                .with("database.user", env.getProperty("spring.r2dbc.username"))
                .with("database.password", env.getProperty("spring.r2dbc.password"))
                .with("database.dbname", env.getProperty("spring.r2dbc.name"))
                .with("database.server.id", "10181")
                .with("database.server.name", "altexc-postgres-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("table.include.list", "public.order_info_tmp")
                .with("publication.autocreate.mode", "all_tables")
                .with("plugin.name", "pgoutput")
                .with("slot.name", "dbz_altexc_db_listener")
                .build();
    }
}
