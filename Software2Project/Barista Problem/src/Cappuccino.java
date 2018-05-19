/*******************************************************
 * This is a subclass of Drink called Cappuccino.
 * It instantiates its unique createRecipe function.
 ******************************************************/


public class Cappuccino extends Drink {

    protected Cappuccino(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe cappuccino = new Recipe();
        cappuccino.addIngredients(Ingredients.ESPRESSO, 2);
        cappuccino.addIngredients(Ingredients.STEAMED_MILK, 1);
        cappuccino.addIngredients(Ingredients.FOAMED_MILK, 1);

        return cappuccino;
    }
}