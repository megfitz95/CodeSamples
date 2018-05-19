/*******************************************************
 * This is a subclass of Drink called CafeMocha.
 * It instantiates its unique createRecipe function.
 ******************************************************/


public class CafeMocha extends Drink{

    protected CafeMocha(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe cafeMocha = new Recipe();
        cafeMocha.addIngredients(Ingredients.ESPRESSO, 1);
        cafeMocha.addIngredients(Ingredients.COCOA, 1);
        cafeMocha.addIngredients(Ingredients.STEAMED_MILK, 1);
        cafeMocha.addIngredients(Ingredients.WHIPPED_CREAM, 1);

        return cafeMocha;
    }
}
