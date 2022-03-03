package com.tabram.coffemaker.coffee;

import com.tabram.coffemaker.Coffee;

import javax.persistence.Entity;

@Entity
public class Cappuccino extends Coffee {
    public Cappuccino() {
        super("Cappuccino", 95, 5, 17.5, 30, 100, 200);
    }
}