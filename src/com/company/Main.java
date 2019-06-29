package com.company;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Theatre t = new Theatre("Sapphire", 16, 8);
        t.printAllSeat();
        System.out.println("Welcome to " + t.getTheatreName() + " Theatre!");
        t.reserveSeat("A01");

        t.reserveSeat("A02");

        t.reserveSeat("A05");

        t.reserveSeat("B04");

        t.reserveSeat("C07");

        t.reserveSeat("C08");

        t.checkAvailableSeatByRow('A');
    }
}
