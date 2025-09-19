package com.sigeinv.repository;

import reactor.core.publisher.Mono;

public interface StoreInventoryRepository {

    Mono<Boolean> reduceStockByStore(String productCode, String storeId, int quantityToReduce);
}
