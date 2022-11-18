package com.example.L4springmvc.controller;

import com.example.L4springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebController {

    private ProductService productService;

    public WebController(ProductService service){
        this.productService = service;
    }

    @GetMapping("/staticMenu")
    public String getStaticMenu(){
        return "productPage.html";
    }

    @GetMapping("/dynamic-menu")
    public ModelAndView getMenu(){
        ModelAndView modelAndView = new ModelAndView("dynamic-menu.html");
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY hh:mm");
        modelAndView.getModelMap().put("serverTime", dateFormat.format(new Date()));
        modelAndView.getModelMap().put("products", productService.getAllProducts());
        return modelAndView;
    }




}
