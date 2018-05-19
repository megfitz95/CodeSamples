/*******************************************************
 * This is a subclass of Drink called CafeAmericano.
 * It instantiates its unique createRecipe function.
 ******************************************************/

public class CafeAmericano extends Drink {

    protected CafeAmericano(String drinkName){
        super(drinkName);
        setDrinkRecipe(createRecipe());
    }

    @Override
    Recipe createRecipe(){
        Recipe cafeAmericano = new Recipe();
        cafeAmericano.addIngredients(Ingredients.ESPRESSO, 3);

        return cafeAmericano;
    }
}
