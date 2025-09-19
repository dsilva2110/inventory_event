package com.sigeinv.storeinventory;

import com.sigeinv.storeinventory.exceptions.InvalidValuesException;
import com.sigeinv.storeinventory.model.StoreInventory;
import lombok.RequiredArgsConstructor;
import com.sigeinv.storeinventory.gateway.StoreInventoryGateway;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class StoreInventoryUseCase {

    private final StoreInventoryGateway storeInventoryGateway;

    public Mono<Boolean> reduceStockStore(StoreInventory storeInventory) {
        return Mono.just(storeInventory)
                .flatMap(this::validateInventoryData)
                .flatMap(si -> storeInventoryGateway.reduceStockByStore(si));
    }

    private Mono<StoreInventory> validateInventoryData(StoreInventory inventory) {

        if (Objects.isNull(inventory.getStoreCode()) || inventory.getStoreCode().trim().isEmpty()) {
            return Mono.error(new InvalidValuesException("El campo 'storeCode' no puede ser nulo o vacio."));
        }

        if (Objects.isNull(inventory.getProductCode()) || inventory.getProductCode().trim().isEmpty()) {
            return Mono.error(new InvalidValuesException("El campo 'productCode' no puede ser nulo o vacio."));
        }

        if (inventory.getQuantity() <= 0) {
            return Mono.error(new InvalidValuesException("El campo 'quantity' debe ser un valor positivo (> 0) para la reduccion de stock."));
        }

        return Mono.just(inventory);
    }
}
