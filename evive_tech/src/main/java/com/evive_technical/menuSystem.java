package com.evive_technical;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class menuSystem 
{
    public static void main( String[] args )
    {
        /* Menu Ordering System 
         * 1) Take input from user
         *  1.1) take input
         *      1.1.3) Prompt user
         *      1.1.2) Check if input exists
         *      1.1.3) if it exists, take input, otherwise wait for input
         *  1.2) flatten text so that it's uniform
         *  1.3) check for invalid input symbols (i.e. only letters and numbers)
         * 2) Parse via space and commas
         *  2.1) parse via commas
         *  2.2) check for particular meal based on first item
         *  2.3) check that all other arguments are numbers
         * 3) Call meal request based on the numbers given
         *  3.1) call meal request and pass in order numbers
         *  3.2) reject order based on issues with order numbers and special rules (too large of a number, 
         *      too many items, etc)
         *  3.3) assemble meal request and return
         * 4) Output meal request
         * 
         * NOTE: A menu ordering system doesn't make much sense if it has to be run for each and every order
         * rather than running continuously, outputting orders, and waiting for a quit signal. That being 
         * said, that's what is in the spec, so that's what I'm doing.
         * 
         * NOTE: As far as OOP is concerned, I suppose, gun-to-my-head, you could create a class called "meal" with a 
         * function that checks basic rules. You could then have children of that class that extend it for each specific 
         * type of meal with prompts, checks, and output assembly. BUT, this program is so simple, why bother?
        */
        
        // Variable declarations
        final Scanner   scan = new Scanner(System.in);
        String          input;

        // Prompt user and take input
        System.out.println("Breakfast:\nEggs - 1\tToast - 2\tCoffee - 3\nUnlimited coffee orders for breakfast only\n");
        System.out.println("Lunch:\nSandwich - 1\tChips - 2\tSoda - 3\nUnlimted chip orders for lunch only\n");
        System.out.println("Dinner:\nSteak - 1\tPotatoes -2\tWine - 3\tCake - 4\nWater is always provided for dinner\n");
        System.out.println("Any meals without a drink will be provided with water\nDinner must come with a dessert\n");
        System.out.print("Please enter your meal and selection: ");

        do{
            input = scan.nextLine();
        }while(input.equals("") || input.equals("\n") || input == null);
        
        //Check input for special Characters other than commas
        if(specialCharCheck(input)){
            System.out.println("Unable to process: please only enter letters, numbers, commas, and spaces");
            scan.close();
            return;
        }
        
        input = input.toLowerCase();

        scan.close();
    }

    private static boolean specialCharCheck(String input){
        final Pattern   pattern = Pattern.compile("[^a-z0-9, ]", Pattern.CASE_INSENSITIVE);
        final Matcher   match;

        match = pattern.matcher(input);

        return match.find();
    }

    private static String outputBreakfast(){

    }

    private static String outputLunch(){

    }

    private static String outputDinner(){

    }
}