package com.retail.poc.retailApi.services.impl;

import com.retail.poc.retailApi.Common.ServiceClientUtility;
import com.retail.poc.retailApi.Exception.DataNotFoundException;
import com.retail.poc.retailApi.Exception.RetryableHttpStatusCodeException;
import com.retail.poc.retailApi.entity.PriceDetail;
import com.retail.poc.retailApi.entity.ProductDetail;
import com.retail.poc.retailApi.repository.PriceRepository;
import com.retail.poc.retailApi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

import static com.retail.poc.retailApi.Common.ServiceClientUtility.requestProcessingException;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Value("${external.endpoint}")
    private String externalBaseEndpoint;

    @Autowired
    ServiceClientUtility serviceClientUtility;

    @Autowired
    PriceRepository  priceRepository;


    public ProductDetail generateProductDetail(String productId){

        String productName= getProductName(productId);
        Optional<PriceDetail>   priceDetail=priceRepository.findById(productId);
        if (!priceDetail.isPresent()) {
            log.info("price not found for this id:",productId);
            String message = String.format("price detail not found for this : %s", productId);
            throw new DataNotFoundException(message);

        }


        return ProductDetail.builder().current_price(priceDetail.get()).name(productName).productId(productId).build();

    }
    @Override
    @Retryable(
            value = { RetryableHttpStatusCodeException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000))
   public String  getProductName(String productId){

         log.info("getting product name from external API");

            try{
                String externalBaseEndpointUrl = buildGetProductNameUrl(externalBaseEndpoint,productId);
                HttpHeaders headers= new HttpHeaders();

                HttpEntity<String> requestEntity=new HttpEntity<>(headers);
                ResponseEntity<String> responseEntity= serviceClientUtility.getApiData(externalBaseEndpointUrl,requestEntity);
                return responseEntity.getBody();
            }

            catch (Exception e) {
                throw requestProcessingException(log, String.format("exception Calling External  API : %s", e));

            }

        }

    private String buildGetProductNameUrl(String baseEndPoint, String productId) {

        return baseEndPoint + productId;
    }


}
