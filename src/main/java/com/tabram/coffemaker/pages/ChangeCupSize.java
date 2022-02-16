package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;

import java.util.Scanner;

public class ChangeCupSize {
    public ChangeCupSize(CoffeeMaker coffeeMaker, Coffee coffee) {

        System.out.println("Change the size of the cup in ml.");
        System.out.println("Enter the size of the cup...");

        Scanner sc = new Scanner(System.in);
        int cupSize = sc.nextInt();
        coffee.setCupSize(cupSize);

        new DoYouHaveTheSameCupSizePage(coffeeMaker, coffee);
    }


}
