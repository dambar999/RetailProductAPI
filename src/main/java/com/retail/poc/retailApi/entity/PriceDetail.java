package com.retail.poc.retailApi.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Builder
@Document
@Getter
@Setter
public class PriceDetail {
    @Id
    String productId;
    String currency_code;
    long value;
}
