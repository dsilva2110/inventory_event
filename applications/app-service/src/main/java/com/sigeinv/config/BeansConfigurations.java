package com.sigeinv.config;

import com.sigeinv.handlers.SalesCreatedHandler;
import com.sigeinv.storeinventory.StoreInventoryUseCase;
import com.sigeinv.storeinventory.gateway.StoreInventoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigurations {

    @Bean
    public StoreInventoryUseCase storeInventoryUseCase(StoreInventoryGateway storeInventoryGateway) {
        return new StoreInventoryUseCase(storeInventoryGateway);
    }

    @Bean
    public SalesCreatedHandler salesCreatedHandler(StoreInventoryUseCase storeInventoryUseCase) {
        return new SalesCreatedHandler(storeInventoryUseCase);
    }
}
