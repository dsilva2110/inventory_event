package com.sigeinv.model.ventasordenescreadas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class SalesCreated {

    private String storeCode;
    private String productCode;
    private int quantity;
}
