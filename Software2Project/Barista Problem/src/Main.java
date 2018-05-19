/*******************************************************
 * Main class, handles user input and program logic
 ******************************************************/

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        //Creates inventory object
        Inventory inventory = new Inventory();

        inventory.printInventory();

        //Creates menu object
        Menu menu = new Menu();

        System.out.println();
        menu.printMenu(inventory);

        System.out.println("Enter selection: ");
        String selection;
        boolean selectDrink = true;

        //While the user does not wish to quit, continue
        //providing them the option to order drinks
        while(selectDrink){
            selection = in.next();
            switch (selection) {
                case "1":
                    Coffee coffee = new Coffee("Coffee");
                    menu.checkStock(inventory, coffee);
                    break;
                case "2":
                    DecafCoffee decafCoffee = new DecafCoffee("Decaf Coffee");
                    menu.checkStock(inventory, decafCoffee);
                    break;
                case "3":
                    CafeAmericano cafeAmericano = new CafeAmericano("Cafe Americano");
                    menu.checkStock(inventory, cafeAmericano);
                    break;
                case "4":
                    CafeLatte cafeLatte = new CafeLatte("Cafe Latte");
                    menu.checkStock(inventory, cafeLatte);
                    break;
                case "5":
                    CafeMocha cafeMocha = new CafeMocha("Cafe Mocha");
                    menu.checkStock(inventory, cafeMocha);
                    break;
                case "6":
                    Cappuccino cappuccino = new Cappuccino("Cappuccino");
                    menu.checkStock(inventory, cappuccino);
                    break;
                case "R":
                case "r":
                    inventory.stockInventory();
                    inventory.printInventory();
                    System.out.println();
                    menu.printMenu(inventory);
                    break;
                case "Q":
                case "q":
                    selectDrink = false;
                    break;
                default:
                    System.out.println("Invalid Selection " + selection);
                    System.out.println();
                    inventory.printInventory();
                    System.out.println();
                    menu.printMenu(inventory);
                    break;
            }
        }
    }
}
