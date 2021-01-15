package com.retail.poc.retailApi.controller;


import com.retail.poc.retailApi.entity.PriceDetail;
import com.retail.poc.retailApi.entity.ProductDetail;
import com.retail.poc.retailApi.service.PriceService;
import com.retail.poc.retailApi.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.util.logging.resources.logging;

import java.util.Map;

@Slf4j
@RestController
public class ProductResource {

@Autowired
ProductService productService;

@Autowired
PriceService priceService;



    @GetMapping(value = "/v1/products/{id}", produces = "application/json")
    public ResponseEntity<ProductDetail> productDetailsAPI(@RequestHeader Map<String,String> headers,

                                                              @RequestParam("id") String id){

        ProductDetail productDetail=null;
        log.info("fething product deatils ....");
        try{

            productDetail= productService.generateProductDetail(id);

        } catch (Exception e){
            log.error("Error occurred while calling Product Dtails API, error = {0}", e);
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(productDetail,HttpStatus.OK);
    }


    @PutMapping(value="v1/products/{id}",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity  updatePrice(@PathVariable String id, @RequestBody PriceDetail priceDetail)    {
        ProductDetail productDetail=null;
        log.info("updating product deatils ....");
        try{

             priceService.updatePrice(id,priceDetail);

        } catch (Exception e){
            log.error("Error occurred while updating price, error = {0}", e);
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    }