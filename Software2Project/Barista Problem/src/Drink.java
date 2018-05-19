/*******************************************************
 * This super class creates a Drink object, consisting
 * of a drink name and a Recipe for the drink. There are
 * sub classes that extend this abstract class.
 ******************************************************/
import java.util.Map;

public abstract class Drink {
    protected String drinkName;
    public Recipe drinkRecipe;


    public Drink(String drinkName){
        super();
        this.drinkName = drinkName;
    }

    public String getDrinkName(){
        return this.drinkName;
    }


    public void setDrinkRecipe(Recipe drinkRecipe){
        this.drinkRecipe = drinkRecipe;
    }

    public Recipe getDrinkRecipe(){
        return this.drinkRecipe;
    }

    public Double getCost(){
        return drinkRecipe.getCost();
    }

    //Checks if a drink is in stock based on the current inventory
    //There must be enough ingredients in the inventory for the drink's
    //specific recipe to be created
    public boolean inStock(Inventory inventory, Drink drink){
        for (Map.Entry<Ingredients, Integer> temp : drinkRecipe.getDrinkRecipe(drink).entrySet()){
            //If the inventory quantity is less than the recipe ingredient quantity
            if (inventory.getStock(temp.getKey()) < temp.getValue()) {
                return false;
            }
        }
        return true;
    }

    //Abstract create recipe class that subclasses override
    abstract Recipe createRecipe();


}
