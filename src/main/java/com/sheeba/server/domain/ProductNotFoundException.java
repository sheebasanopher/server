package com.sheeba.server.domain;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String massage) {
        super(massage);
    }

}
