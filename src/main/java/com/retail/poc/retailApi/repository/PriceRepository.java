package com.retail.poc.retailApi.repository;

import com.retail.poc.retailApi.entity.PriceDetail;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceDetail,String> {

}
