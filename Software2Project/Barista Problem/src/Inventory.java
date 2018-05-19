/*******************************************************
 * This class creates an Inventory object, consisting of
 * ingredients and their quantity.
 ******************************************************/

import java.util.*;

public class Inventory {
    private TreeMap<Ingredients, Integer> inventoryList;

    public Inventory(){
        inventoryList = new TreeMap<Ingredients, Integer>();
        stockInventory();
    }

    public void stockInventory(){
        inventoryList.put(Ingredients.COCOA, 10);
        inventoryList.put(Ingredients.COFFEE, 10);
        inventoryList.put(Ingredients.CREAM, 10);
        inventoryList.put(Ingredients.DECAF_COFFEE, 10);
        inventoryList.put(Ingredients.ESPRESSO, 10);
        inventoryList.put(Ingredients.FOAMED_MILK, 10);
        inventoryList.put(Ingredients.STEAMED_MILK, 10);
        inventoryList.put(Ingredients.SUGAR, 10);
        inventoryList.put(Ingredients.WHIPPED_CREAM, 10);
    }

    public Integer getStock(Ingredients ingredient){
        return inventoryList.get(ingredient);
    }

    public void setStock(Ingredients ingredients, Integer integer){
        inventoryList.put(ingredients, integer);
    }

    //removeItems is called when a drink is made. It subtracts the
    //quantity needed for each ingredient to make the drink from the
    //total inventory and updates the inventory object
    public void removeItems(Inventory inventory, Drink drink){
        Recipe recipe = drink.getDrinkRecipe();
        for (Map.Entry<Ingredients, Integer> temp : recipe.getDrinkRecipe(drink).entrySet()){
            inventory.setStock(temp.getKey(), (inventory.getStock(temp.getKey()) - temp.getValue()));
        }

    }

    public void printInventory(){
        System.out.println("Inventory: ");

        for (Ingredients ingredients: inventoryList.keySet()){
            System.out.println(ingredients + ": " + inventoryList.get(ingredients));

        }
    }

}
