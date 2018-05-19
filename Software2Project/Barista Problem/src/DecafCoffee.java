/*******************************************************
 * This is a subclass of Drink called DecafCoffee.
 * It instantiates its unique createRecipe function.
 ******************************************************/


public class DecafCoffee extends Drink{

    protected DecafCoffee(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe decafCoffee = new Recipe();
        decafCoffee.addIngredients(Ingredients.DECAF_COFFEE, 3);
        decafCoffee.addIngredients(Ingredients.SUGAR, 1);
        decafCoffee.addIngredients(Ingredients.CREAM, 1);

        return decafCoffee;
    }
}
