package com.example.L4springmvc.controller;

import com.example.L4springmvc.model.Product;
import com.example.L4springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);

    }

    @GetMapping("/addProductGet")
    public void addProductGet(@RequestParam String name, @RequestParam String price){
        //validate params
        Double price2 = Double.parseDouble(price);

        Product product = new Product(name, price2);

        productService.addProduct(product);
    }


    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
