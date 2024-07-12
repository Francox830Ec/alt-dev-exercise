package com.atl.order_detail_service.order_detail.application.usecases.command.contract;

import java.util.Map;

public interface IReplicateOrderDetailUseCase {
    void replicateData(Map<String, Object> payload, String operation);
}
