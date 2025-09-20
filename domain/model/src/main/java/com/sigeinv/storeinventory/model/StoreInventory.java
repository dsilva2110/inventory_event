package com.sigeinv.storeinventory.model;

import java.util.Date;

public class StoreInventory {

    private String storeCode;
    private String productCode;
    private int quantity;
    private Date lastUpdate;

    public StoreInventory() {
    }

    public StoreInventory(String storeCode, String productCode, int quantity ) {
        this.storeCode = storeCode;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
