package com.evive_technical;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
         *  2.3) check that all other arguments are positive, integer, non-zero numbers
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
        */
        
        // Variable declarations
        final Scanner   scan = new Scanner(System.in);
        Scanner         inputScan;
        String          input = "";
        String          mealName = "";
        String          stringOrder = "";
        List<Integer>   order = new ArrayList<Integer>();


        // Prompt user and take input
        breakfast.displayBreakfastChoices();
        lunch.displayLunchChoices();
        //dinner.displayDinnerChoices();
        System.out.print("\nPlease enter your meal and selection: ");

        do{
            input = scan.nextLine();
        }while(input.equals("") || input.equals("\n") || input == null);
        

        //Check input for special Characters other than commas
        if(specialCharCheck(input)){
            System.out.println("Unable to process: please only enter letters, numbers, commas, and spaces");
            scan.close();
            return;
        }
        

        //Fix input, parse meal name and order
        input = input.toLowerCase();
        inputScan = new Scanner(input);
        inputScan.useDelimiter(" ");
        if (inputScan.hasNext()) mealName = inputScan.next();   //NOTE: Assuming meal name will always come first
        if (inputScan.hasNext()) stringOrder = inputScan.next();
        inputScan.close();
        inputScan = new Scanner(stringOrder);
        inputScan.useDelimiter(",");
        while(inputScan.hasNext()){
            order.add(Integer.valueOf(inputScan.next()));
        }


        // call meal request
        switch(mealName){
            case "breakfast":   
                breakfast breakfastMeal = new breakfast(order);
                System.out.println(breakfastMeal.processOrder());
                break;
            case "lunch":       
                lunch lunchMeal = new lunch(order);
                System.out.println(lunchMeal.processOrder());
                break;
            /*case "dinner":      
                dinner dinnerMeal = new dinner(order);
                System.out.println(dinnerMeal.processOrder());
                break;*/
            default:
                System.out.println("Unable to process: invalid meal input");
                scan.close();
                inputScan.close();
                return;
        }

        scan.close();
        inputScan.close();
        return;
    }

    private static boolean specialCharCheck(String input){
        final Pattern   pattern = Pattern.compile("[^a-z0-9, ]", Pattern.CASE_INSENSITIVE);
        final Matcher   match;

        match = pattern.matcher(input);

        return match.find();
    }

}