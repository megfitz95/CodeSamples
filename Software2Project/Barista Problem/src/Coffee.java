/*******************************************************
 * This is a subclass of Drink called Coffee.
 * It instantiates its unique createRecipe function.
 ******************************************************/


public class Coffee extends Drink{

    protected Coffee(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe coffee = new Recipe();
        coffee.addIngredients(Ingredients.COFFEE, 3);
        coffee.addIngredients(Ingredients.SUGAR, 1);
        coffee.addIngredients(Ingredients.CREAM, 1);

        return coffee;
    }

}
