package com.evive_technical;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class breakfast extends meal{
    private static final List<String> MAINOPTIONS = Arrays.asList("Eggs");
    private static final List<String> SIDEOPTIONS = Arrays.asList("Toast");
    private static final List<String> DRINKOPTIONS = Arrays.asList("Coffee");

    private static final Integer numMainOptions = MAINOPTIONS.size();
    private static final Integer numSideOptions = SIDEOPTIONS.size();
    private static final Integer numDrinkOptions = DRINKOPTIONS.size();

    private static HashMap<String, Integer> mains = new HashMap<>(numMainOptions);
    private static HashMap<String, Integer> sides = new HashMap<>(numSideOptions);
    private static HashMap<String, Integer> drinks = new HashMap<>(numDrinkOptions);

    private static List<List<String>> menu = Arrays.asList(MAINOPTIONS, SIDEOPTIONS, DRINKOPTIONS);
    private static List<HashMap<String, Integer>> orderedItems = Arrays.asList(mains, sides, drinks);

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

        if (super.order == null) return "Unable to process: empty order";

        // Counts all the instances of an item ordered
        for(final Integer i : super.order){
            if (i <= numMainOptions){
                if (mains.containsKey(MAINOPTIONS.get(i-1))){
                    mains.put(MAINOPTIONS.get(i-1), mains.get(MAINOPTIONS.get(i-1))+1);
                } else {
                    mains.put(MAINOPTIONS.get(i-1), 1);
                }
            } else if (i <= (numMainOptions + numSideOptions)) {
                if (sides.containsKey(SIDEOPTIONS.get(i-1-numMainOptions))){
                    sides.put(SIDEOPTIONS.get(i-1-numMainOptions), sides.get(SIDEOPTIONS.get(i-1-numMainOptions))+1);
                } else {
                    sides.put(SIDEOPTIONS.get(i-1-numMainOptions), 1);
                }
            } else if (i <= (numMainOptions + numSideOptions + numDrinkOptions)){
                if (drinks.containsKey(DRINKOPTIONS.get(i-1-numMainOptions-numSideOptions))){
                    drinks.put(DRINKOPTIONS.get(i-1-numMainOptions-numSideOptions), drinks.get(DRINKOPTIONS.get(i-1-numMainOptions-numSideOptions))+1);
                } else {
                    drinks.put(DRINKOPTIONS.get(i-1-numMainOptions-numSideOptions), 1);
                }
            } else {
                return "Unable to process: invalid item number";
            }
        }

        // Apply rules and build output string
        processedOrder = isCompliantWithRules();
        if(processedOrder.equals("Unable to process")) processedOrder = listOrderedItems();
        else return processedOrder;

        return processedOrder;
    }

    private static String isCompliantWithRules(){
        // Each order must contain a main and a side, but only 1 each for breakfast, this does not apply to coffee
        int mainCount = 0;
        int sideCount = 0;
        String returnMsg = "Unable to process: ";

        for(HashMap.Entry<String, Integer> entry : mains.entrySet()){
            mainCount += entry.getValue();
            if(entry.getValue() > 1){
                returnMsg = returnMsg + entry.getKey() + " cannot be ordered more than once, ";
            }
        }

        if(mainCount < 1) returnMsg = returnMsg + "Main is missing, ";

        for(HashMap.Entry<String, Integer> entry : sides.entrySet()){
            sideCount += entry.getValue();
            if(entry.getValue() > 1){
                returnMsg = returnMsg + entry.getKey() + " cannot be ordered more than once, ";
            }
        }

        if(sideCount < 1) returnMsg = returnMsg + "Side is missing, ";
        
        return returnMsg.substring(0, returnMsg.length()-2);
    }

    // NOTE: For the sake of simplicity, I'm assuming drinks are *always* the third listed set of items
    private static String listOrderedItems(){
        String returnOrderedItems = "";

        for(int i = 0; i < orderedItems.size(); i++){
            if (i != 2){
                for(HashMap.Entry<String, Integer> entry : orderedItems.get(i).entrySet()){
                    if(entry.getValue() > 1) returnOrderedItems = returnOrderedItems + entry.getKey() + "(" + entry.getValue() + "), ";
                    else returnOrderedItems = returnOrderedItems + entry.getKey() + ", ";
                }
            } else {
                int drinkSum = 0;
                for(HashMap.Entry<String, Integer> entry : orderedItems.get(i).entrySet()){
                    if(entry.getValue() > 1){
                        returnOrderedItems = returnOrderedItems + entry.getKey() + "(" + entry.getValue() + "), ";
                        drinkSum++;
                    } else if(entry.getValue() == 1){
                        returnOrderedItems = returnOrderedItems + entry.getKey() + ", ";
                        drinkSum++;
                    }
                }
                if(drinkSum == 0){
                    returnOrderedItems = returnOrderedItems + "Water, ";
                }
            }
        }
        
        return returnOrderedItems.substring(0, returnOrderedItems.length()-2);
    }
}
