/*******************************************************
 * This is a subclass of Drink called CafeLatte.
 * It instantiates its unique createRecipe function.
 ******************************************************/


public class CafeLatte extends Drink{

    protected CafeLatte(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe cafeLatte = new Recipe();
        cafeLatte.addIngredients(Ingredients.ESPRESSO, 2);
        cafeLatte.addIngredients(Ingredients.STEAMED_MILK, 1);
        cafeLatte.addIngredients(Ingredients.STEAMED_MILK, 1);

        return cafeLatte;
    }


}
