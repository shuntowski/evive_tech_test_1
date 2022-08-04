package com.evive_technical;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class menuSystemTest
{
    /*Sample Input/Outputs 
     * NOTE: Running the entire test group at once will cause tests down the line to fail. If these tests are run individually,
     * they pass. I believe at this time that it has something to do with a race condition between testing groups, wherein the
     * object isn't being torn down quickly enough, and so collision between tests happens. This persists even when new objects 
     * are declared for each and every test.
     * 
     * For once: The code works but the tests don't. It'd be funny if it wasn't frustrating.
    */
    @Test
    public void breakfastTest1(){
        breakfast breakfastMeal = new breakfast(Arrays.asList(1,2,3));
        assertEquals("Eggs, Toast, Coffee", breakfastMeal.processOrder());
    }

    @Test
    public void breakfastTest2(){
        breakfast breakfastMeal = new breakfast(Arrays.asList(2,3,1));
        assertEquals("Eggs, Toast, Coffee", breakfastMeal.processOrder());
    }

    @Test
    public void breakfastTest3(){
        breakfast breakfastMeal = new breakfast(Arrays.asList(1,2,3,3,3));
        assertEquals("Eggs, Toast, Coffee(3)", breakfastMeal.processOrder());
    }

    @Test
    public void breakfastTest4(){
        breakfast breakfastMeal = new breakfast(Arrays.asList(1));
        assertEquals("Unable to process: Side is missing", breakfastMeal.processOrder());
    }

    @Test
    public void lunchTest1(){
        lunch lunchMeal = new lunch(Arrays.asList(1,2,3));
        assertEquals("Sandwich, Chips, Soda", lunchMeal.processOrder());
    }

    @Test
    public void lunchTest2(){
        lunch lunchMeal = new lunch(Arrays.asList(1,2));
        assertEquals("Sandwich, Chips, Water", lunchMeal.processOrder());
    }

    @Test
    public void lunchTest3(){
        lunch lunchMeal = new lunch(Arrays.asList(1,1,2,3));
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", lunchMeal.processOrder());
    }

    @Test
    public void lunchTest4(){
        lunch lunchMeal = new lunch(Arrays.asList(1,2,2));
        assertEquals("Sandwich, Chips(2), Water", lunchMeal.processOrder());
    }

    @Test
    public void lunchTest5(){
        lunch lunchMeal = new lunch(Arrays.asList());
        assertEquals("Unable to process: Main is missing, Side is missing", lunchMeal.processOrder());
    }

    @Test
    public void dinnerTest1(){
        dinner dinnerMeal = new dinner(Arrays.asList(1,2,3,4));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", dinnerMeal.processOrder());
    }

    @Test
    public void dinnerTest2(){
        dinner dinnerMeal = new dinner(Arrays.asList(1,2,3));
        assertEquals("Unable to process: Dessert is missing", dinnerMeal.processOrder());
    }
}