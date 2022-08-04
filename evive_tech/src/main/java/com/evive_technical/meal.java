package com.evive_technical;

import java.util.List;

public class meal {
    protected String mealName;
    protected List<Integer> order;

    protected meal(){
        this.mealName = "No meal selected";
        this.order = null;
    }

    protected meal(String mealName, List<Integer> order){
        this.mealName = mealName;
        this.order = order;
    }

    protected void setMealName(String mealName){
        this.mealName = mealName;
    }
    
    protected String getMealName(){
        return this.mealName;
    }

    protected void setOrder(List<Integer> order){
        this.order = order;
    }

    protected List<Integer> getOrder(){
        return this.order;
    }
}
