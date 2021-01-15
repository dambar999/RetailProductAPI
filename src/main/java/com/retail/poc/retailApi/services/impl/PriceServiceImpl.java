package com.retail.poc.retailApi.services.impl;

import com.retail.poc.retailApi.entity.PriceDetail;
import com.retail.poc.retailApi.repository.PriceRepository;
import com.retail.poc.retailApi.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;

public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public void updatePrice(String id,PriceDetail priceDetail) {
        priceDetail.setProductId(id);
        //replace the document with new price details
        priceRepository.save(priceDetail) ;

    }
}
