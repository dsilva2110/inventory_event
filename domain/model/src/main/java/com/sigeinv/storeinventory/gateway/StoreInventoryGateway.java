package com.sigeinv.storeinventory.gateway;

import com.sigeinv.storeinventory.model.StoreInventory;
import reactor.core.publisher.Mono;

public interface StoreInventoryGateway {

    Mono<Boolean> reduceStockByStore(StoreInventory storeInventory);
}
