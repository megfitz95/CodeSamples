/*******************************************************
 * This class creates a Menu object, consisting of
 * a drink id (int) and a Drink object
 ******************************************************/

import java.util.TreeMap;

public class Menu {
    public TreeMap<Integer, Drink>  menu;

    public Menu(){
        menu = new TreeMap<Integer, Drink>();
        createMenu();
    }

    public void createMenu(){

        menu.put(1, new CafeAmericano("Cafe Americano"));
        menu.put(2, new CafeLatte("Cafe Latte"));
        menu.put(3, new CafeMocha("Cafe Mocha"));
        menu.put(4, new Cappuccino("Cappuccino"));
        menu.put(5, new Coffee("Coffee"));
        menu.put(6, new DecafCoffee("Decaf Coffee"));
    }

    //checkStock checks to see if a drink is in stock by checking
    //the inventory and prints to the console out of stock or
    //dispensing. All drinks can use this function to print
    //to the console.
    public void checkStock(Inventory inventory, Drink drink){
        if (drink.inStock(inventory, drink)) {
            System.out.println("Dispensing " + drink.getDrinkName());
            System.out.println();
            inventory.removeItems(inventory, drink);
            inventory.printInventory();
        } else {
            System.out.println("Out of Stock " + drink.getDrinkName());
        }
        System.out.println();
        printMenu(inventory);
    }

    public void printMenu(Inventory inventory){
        System.out.println("Menu: ");

        for (Integer integer: menu.keySet()){
            String cost = String.format("%.2f", menu.get(integer).getCost());
            System.out.println(integer + ": " + menu.get(integer).drinkName + ", $" + cost +  ", " + menu.get(integer).inStock(inventory, null));

        }
    }
}
