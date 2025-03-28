package com.exampleapi1;

import java.util.Scanner;

public class TollGate {
    public static void main(String[] args) {
        int balance = 100;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your amount");
        int amount = scan.nextInt();
        if (amount > balance) {
            // throw a custom Exception
            try {

                throw new LowBalance("black listed");
            } catch (LowBalance e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Done...");
        }
    }

}

