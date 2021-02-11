package com.techTesters;

import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) {
        try {
            float item = chooseProduct();
            int cost = retrievePrice(item);
            float change = moneyInserted(cost);
            changeOut(change);
        }catch(Exception e){
            System.out.println("Sorry. Please try again.");
            System.exit(0);
        }
    }

    public static float chooseProduct() {
        Scanner keyboard = new Scanner(System.in);
        float product = 0;
        System.out.println("Select Product:" + "\n1: Soda (Cost-45)" + "\n2: Coke (Cost-25)" + "\n3: Pepsi (Cost-35)" + "\n4: Exit "+ "\n5: Reset");
        product = keyboard.nextFloat();
        return product;
    }

    public static int retrievePrice(float product) {
        if (product == 1)
            return 45;
        if (product == 2)
            return 25;
        if (product == 3)
            return 35;
        if (product == 4) {
            System.out.println("Exiting the process.");
            System.exit(0);
            return 0;
        }if (product == 5) {
            System.out.println("Please enter the reset code: ");
            Scanner keyboard = new Scanner(System.in);
            int code = keyboard.nextInt();
            if(code==1234){
                System.out.println("Resetting the machine. \nReset Completed!");
                System.exit(0);
                return 0;
            }else {
                System.out.println("Reset code failed. Please try again.");
                System.exit(0);
                return 0;
            }
        }
        else {
            System.out.println("Sorry this is an Invalid Entry.");
            System.exit(0);
            return 0;
        }
    }

    public static float moneyInserted(int Price) {
        Scanner keyboard = new Scanner(System.in);
        float money = 0;
        System.out.println("Your item cost is: " + Price + ", Please enter the amount of money:"+"\nEnter 0 to cancel");
        money = keyboard.nextFloat();
        if(money==0){
            System.out.println("Cancelling the process.");
            System.exit(0);
            return 0;
        }
        while (money < Price) {
            System.out.println("Not sufficient funds. Please insert more money.");
            money = money + keyboard.nextFloat();
        }
        return money - Price;
    }

    public static void changeOut(float refund) {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int penny = 0;
        if(refund<1){
            System.out.println("Enjoy your drink. Thanks!");
            System.exit(0);
        }
        while (refund >= 25) {
            quarters = quarters + 1;
            refund = refund - 25;
        }
        while (refund >= 10) {
            dimes = dimes + 1;
            refund = refund - 10;
        }
        while (refund >= 5) {
            nickels = nickels + 1;
            refund = refund - 5;
        }
        while (refund >= 1) {
            penny = penny + 1;
            refund = refund - 1;
        }
        System.out.printf("\nHere's your change:\n%d quarters, %d dimes, %d nickels and %d pennies! \nEnjoy your drink. Thanks!",
                quarters, dimes, nickels, penny);
    }
}

