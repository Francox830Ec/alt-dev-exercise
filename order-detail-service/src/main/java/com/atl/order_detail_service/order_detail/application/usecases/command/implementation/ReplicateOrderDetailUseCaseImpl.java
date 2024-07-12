package com.atl.order_detail_service.order_detail.application.usecases.command.implementation;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.IReplicateOrderDetailUseCase;
import com.atl.order_detail_service.order_detail.domain.model.ClientETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderDetailETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderETO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderReadingDBReactiveRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

public class ReplicateOrderDetailUseCaseImpl implements IReplicateOrderDetailUseCase {
    private final IOrderReadingDBReactiveRepository readingDBReactiveRepository;

    public ReplicateOrderDetailUseCaseImpl(IOrderReadingDBReactiveRepository readingDBReactiveRepository) {
        this.readingDBReactiveRepository = readingDBReactiveRepository;
    }

    @Override
    public void replicateData(Map<String, Object> payload, String operation) {
        switch (operation){
            case "CREATE", "UPDATE":
                readingDBReactiveRepository.save(buildOrderETO(payload));
                break;
            case "READ": {
//                if (!queryRepository.existsById(UUID.fromString(payload.get("accId").toString()))){
//                    commandRepository.create(payload);
//                }
                break;
            }
            case "DELETE":
//                commandRepository.deleteById(UUID.fromString(payload.get("accId").toString()));
//                break;
            default: throw new RuntimeException("Operation not supported");
        }

    }

    private OrderETO buildOrderETO(Map<String, Object> payload) {
        return new OrderETO(UUID.fromString(payload.get("ordUuid").toString()),
                payload.get("ordCode").toString(),
                LocalDate.parse(payload.get("ordDate").toString()),
                new ClientETO(UUID.fromString(payload.get("ordUuid").toString()),
                        payload.get("cliNames").toString()),
                new OrderDetailETO(UUID.fromString(payload.get("odtUuid").toString()),
                        UUID.fromString(payload.get("proUuid").toString()),
                        payload.get("proName").toString(),
                        payload.get("proCode").toString(),
                        (BigDecimal) payload.get("odtUnitPrice")));
    }
}
