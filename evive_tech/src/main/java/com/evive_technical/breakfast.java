package com.evive_technical;

import java.util.List;
import java.util.Arrays;
//import java.util.Map;
import java.util.HashMap;

public class breakfast extends meal{
    private static final List<String> mainOptions = Arrays.asList("Eggs");
    private static final List<String> sideOptions = Arrays.asList("Toast");
    private static final List<String> drinkOptions = Arrays.asList("Coffee");

    private static final Integer numMainOptions = mainOptions.size();
    private static final Integer numSideOptions = sideOptions.size();
    private static final Integer numDrinkOptions = drinkOptions.size();

    private static HashMap<String, Integer> mains = new HashMap<>(numMainOptions);
    private static HashMap<String, Integer> sides = new HashMap<>(numSideOptions);
    private static HashMap<String, Integer> drinks = new HashMap<>(numDrinkOptions);

    private static List<List<String>> menu = Arrays.asList(mainOptions, sideOptions, drinkOptions);

    public breakfast(List<Integer> order){
        super("Breakfast", order);
    }

    public static void displayBreakfastChoices(){
        System.out.println("Breakfast:\n");
        for(int i = 0; i < menu.size(); i++){
            for(int j = 0; j < menu.get(i).size(); j++){
                System.out.print(menu.get(i).get(j) + "\t- " + (i+1));
            }
            System.out.println();
        }
    }


    public String processOrder(){
        String processedOrder = "";

        for(final Integer i : order){
            if (i <= numMainOptions){
                
            } else if (i <= (numMainOptions + numSideOptions)) {

            } else if (i <= (numMainOptions + numSideOptions + numDrinkOptions)){

            } else {
                return "Unable to process: invalid item number";
            }
        }

        return processedOrder;
    }
}
