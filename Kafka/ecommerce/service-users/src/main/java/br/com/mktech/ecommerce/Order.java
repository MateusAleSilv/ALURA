package br.com.mktech.ecommerce;

import java.math.BigDecimal;

public class Order {

    private final String orderId;
    private final BigDecimal amount;
    private final String email;

    public Order(String orderId, BigDecimal amount, String email, String email1) {

        this.orderId = orderId;
        this.amount = amount;
        this.email = email1;
    }

    public String getEmail() {
        return "email";
    }
}
