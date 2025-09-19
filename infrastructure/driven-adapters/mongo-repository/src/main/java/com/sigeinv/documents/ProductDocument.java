package com.sigeinv.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "product")
public class ProductDocument {

    @Id
    private String id;
    private String code;
    private String name;
    private double price;

    public ProductDocument() {
    }
}
