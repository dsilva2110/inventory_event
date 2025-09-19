package com.sigeinv.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "store_inventory")
public class StoreInventoryDocument {

    @Id
    private String id;
    private String storeCode;
    private String productCode;
    private int quantity;
    private Date lastUpdate;

    public StoreInventoryDocument() {
    }
}
