package com.sigeinv.handlers;

import com.sigeinv.mappers.JsonMapperUtil;
import com.sigeinv.model.ventasordenescreadas.SalesCreated;
import com.sigeinv.storeinventory.StoreInventoryUseCase;
import com.sigeinv.storeinventory.exceptions.InvalidValuesException;
import com.sigeinv.storeinventory.model.StoreInventory;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;

@AllArgsConstructor
public class SalesCreatedHandler {

    private final StoreInventoryUseCase storeInventoryUseCase;

    public Mono<Boolean> reduceStockStore(String eventMessage){
        return storeInventoryUseCase.reduceStockStore(toStoreInventory(eventMessage))
                .onErrorResume(InvalidValuesException.class, e -> {
                    System.err.println("ERROR: Mensaje descartado (Datos inválidos): " + e.getMessage());
                    return Mono.empty();
                });
    }

    private StoreInventory toStoreInventory(String eventMessage){
        SalesCreated salesCreated = JsonMapperUtil.deserialize(eventMessage, SalesCreated.class);
        StoreInventory storeInventory = new StoreInventory();
        storeInventory.setStoreCode(salesCreated.getStoreCode());
        storeInventory.setProductCode(salesCreated.getProductCode());
        storeInventory.setQuantity(salesCreated.getQuantity());
        storeInventory.setLastUpdate(new Date());
        return storeInventory;
    }
}
