package com.atl.client_product_service.client.infrastructure.http.service.implementation;

import com.atl.client_product_service.client.application.usecases.contract.*;
import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.infrastructure.http.service.contract.IClientReactiveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Log4j2
@Service
public class ClientReactiveServiceImpl implements IClientReactiveService {
    private final ICreateClientUseCase createUseCase;
    private final IUpdateByIdClientUseCase updateByIdUseCase;
    private final IDeleteByIdClientUseCase deleteByIdUseCase;
    private final IFindByIdClientUseCase findByIdUseCase;
    private final IFindAllClientUseCase findAllUseCase;

    public ClientReactiveServiceImpl(ICreateClientUseCase createUseCase,
                                     IUpdateByIdClientUseCase updateByIdUseCase,
                                     IDeleteByIdClientUseCase deleteByIdUseCase,
                                     IFindByIdClientUseCase findByIdUseCase,
                                     IFindAllClientUseCase findAllUseCase) {
        this.createUseCase = createUseCase;
        this.updateByIdUseCase = updateByIdUseCase;
        this.deleteByIdUseCase = deleteByIdUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findAllUseCase = findAllUseCase;
    }

    @Override
    public Mono<ClientDTO> create(ClientDTO dto) {
        return createUseCase.create(dto)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<ClientDTO> update(UUID uuid, ClientDTO dto) {
        return updateByIdUseCase.updateById(uuid, dto)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return deleteByIdUseCase.deleteById(uuid)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<ClientDTO> findById(UUID uuid) {
        return findByIdUseCase.findById(uuid)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Flux<ClientDTO> findAll() {
        return findAllUseCase.findAll()
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }
}
