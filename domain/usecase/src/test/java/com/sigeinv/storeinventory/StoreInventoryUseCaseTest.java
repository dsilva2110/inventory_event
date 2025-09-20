package com.sigeinv.storeinventory;

import com.sigeinv.storeinventory.exceptions.InvalidValuesException;
import com.sigeinv.storeinventory.gateway.StoreInventoryGateway;
import com.sigeinv.storeinventory.model.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreInventoryUseCaseTest {

    @Mock
    private StoreInventoryGateway storeInventoryGateway;

    @InjectMocks
    private StoreInventoryUseCase storeInventoryUseCase;

    private StoreInventory validInventory;

    @BeforeEach
    void setUp() {
        validInventory = new StoreInventory("BOG-CENTRO", "TVLG60", 5);
    }

    @Test
    void reduceStockStore_shouldCallGatewayAndReturnTrue_whenDataIsValid() {

        when(storeInventoryGateway.reduceStockByStore(any(StoreInventory.class)))
                .thenReturn(Mono.just(true));

        StepVerifier.create(storeInventoryUseCase.reduceStockStore(validInventory))
                .expectNext(true)
                .verifyComplete();

        verify(storeInventoryGateway, times(1)).reduceStockByStore(validInventory);
    }

    @Test
    void reduceStockStore_shouldReturnError_whenStoreCodeIsNull() {

        validInventory.setStoreCode(null);


        StepVerifier.create(storeInventoryUseCase.reduceStockStore(validInventory))
                .expectError(InvalidValuesException.class)
                .verify();

        verify(storeInventoryGateway, never()).reduceStockByStore(any());
    }

    @Test
    void reduceStockStore_shouldReturnError_whenStoreCodeIsEmpty() {

        validInventory.setStoreCode(" ");

        StepVerifier.create(storeInventoryUseCase.reduceStockStore(validInventory))
                .expectErrorMatches(throwable ->
                        throwable instanceof InvalidValuesException &&
                                throwable.getMessage().contains("storeCode"))
                .verify();

        verify(storeInventoryGateway, never()).reduceStockByStore(any());
    }

    @Test
    void reduceStockStore_shouldReturnError_whenProductCodeIsNull() {

        validInventory.setProductCode(null);

        StepVerifier.create(storeInventoryUseCase.reduceStockStore(validInventory))
                .expectError(InvalidValuesException.class)
                .verify();

        verify(storeInventoryGateway, never()).reduceStockByStore(any());
    }

    @Test
    void reduceStockStore_shouldReturnError_whenQuantityIsNegative() {
        validInventory.setQuantity(-5);

        StepVerifier.create(storeInventoryUseCase.reduceStockStore(validInventory))
                .expectError(InvalidValuesException.class)
                .verify();

        verify(storeInventoryGateway, never()).reduceStockByStore(any());
    }
}
