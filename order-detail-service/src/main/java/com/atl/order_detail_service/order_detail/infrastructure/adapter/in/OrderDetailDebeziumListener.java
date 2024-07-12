package com.atl.order_detail_service.order_detail.infrastructure.adapter.in;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.IReplicateOrderDetailUseCase;
import com.atl.order_detail_service.order_detail.infrastructure.util.DebeziumEventUtil;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class OrderDetailDebeziumListener {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;
    private final IReplicateOrderDetailUseCase replicateUseCase;

    public OrderDetailDebeziumListener(io.debezium.config.Configuration customerConnectorConfiguration,
                                       IReplicateOrderDetailUseCase replicateUseCase) {
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(customerConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
        this.replicateUseCase = replicateUseCase;
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        DebeziumEventUtil util = new DebeziumEventUtil(sourceRecordRecordChangeEvent);
        replicateUseCase.replicateData(util.payload(), util.operation().name());
    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (Objects.nonNull(this.debeziumEngine)) {
            this.debeziumEngine.close();
        }
    }
}
