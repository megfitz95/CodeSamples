/*******************************************************
 * This class creates a recipe object, consisting of
 * ingredients and their quantity in each drink. It also
 * accounts for the recipe's total cost based on the
 * price of each ingredient.
 ******************************************************/

import java.util.HashMap;


public class Recipe{

    private HashMap<Ingredients, Integer> recipe = new HashMap<Ingredients, Integer>();
    private double totalCost;

    public Recipe(){

    }

    //Add ingredients sets the ingredients and quantity, total cost
    //of ingredients by their quantity
    public void addIngredients(Ingredients ingredient, int quantity){
        recipe.put(ingredient, quantity);
        Double temp = ingredient.getCost() * quantity;
        this.totalCost += temp;

    }

    public HashMap<Ingredients, Integer> getDrinkRecipe(Drink drink) {
        return recipe;
    }

    public double getCost(){
        return this.totalCost;
    }


}
