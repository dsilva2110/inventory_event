package com.sigeinv.adapters;

import com.sigeinv.storeinventory.gateway.StoreInventoryGateway;
import com.sigeinv.storeinventory.model.StoreInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StoreInventoryDBAdapter implements StoreInventoryGateway {

    private final ReactiveMongoTemplate mongoTemplate;
    @Override
    public Mono<Boolean> reduceStockByStore(StoreInventory storeInventory) {

        System.out.println("storeCode = " + storeInventory.getStoreCode());
        System.out.println("productCode = " + storeInventory.getProductCode());

        Query query = new Query(Criteria.where("storeCode").is(storeInventory.getStoreCode())
                .and("productCode").is(storeInventory.getProductCode()));

        Update update = new Update().inc("quantity", -storeInventory.getQuantity());

        return mongoTemplate.updateFirst(query, update, "store_inventory")
                .map(result -> {
                    System.out.println("MONGO RESULTADO: Matched: " + result.getMatchedCount());
                    System.out.println("MONGO RESULTADO: Modified: " + result.getModifiedCount());

                    return result.wasAcknowledged() && result.getModifiedCount() > 0;
                })
                .defaultIfEmpty(false);
    }
}
