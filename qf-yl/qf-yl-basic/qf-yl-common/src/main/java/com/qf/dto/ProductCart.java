package com.qf.dto;

import com.qf.entity.ShoppingCart;

import java.io.Serializable;
import java.util.Date;

public class ProductCart implements Serializable {

    ShoppingCart shoppingCart;
    Date date;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
