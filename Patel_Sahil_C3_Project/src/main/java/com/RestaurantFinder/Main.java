package com.RestaurantFinder;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        //these are for testing purpose only...

        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("21:00:00");
        Restaurant restaurant = new Restaurant("Mom's cafe", "New Jersey", openingTime, closingTime);
        restaurant.addToMenu("Sweet & Sour soup", 15);
        restaurant.addToMenu("Chicken Korma", 25);
    }
}
