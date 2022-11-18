package com.example.L4springmvc.service;

import com.example.L4springmvc.model.Product;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    static int nextId=1;

    static List<Product> productList = new ArrayList<>();

    @PostConstruct
    public void initProducts(){

        addProduct(new Product("airpods", 20000));
        addProduct(new Product("smartband", 2000));
        addProduct(new Product("laptop", 200000));

    }



    public void addProduct(Product product) {
        logger.debug("Adding product {}", product);
        product.setId(nextId++);
        productList.add(product);
        logger.info("New Product added with id {}", nextId);
    }

    public List<Product> getAllProducts(){
        if(productList.isEmpty()){
            logger.warn("No Products");
        }
        logger.info("List of products is {}", productList);
        return productList;
    }
}
