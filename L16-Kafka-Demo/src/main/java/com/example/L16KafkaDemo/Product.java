package com.example.L16KafkaDemo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class Product implements Serializable {

    String name;
    Double price;

}
