package bhardwaj.keshav.bonappetite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by keshav on 10/1/18.
 */

public class MenuItemCustomerSideView {

    public String ItemName, Charges, Temp, ItemId, Ingredients1;
    public Vector<String> Ingredients;
    public static int Cold, LowBP;

    public static int getCold() {
        return Cold;
    }

    public static void setCold(int cold) {
        Cold = cold;
    }

    public static int getLowBP() {
        return LowBP;
    }

    public static void setLowBP(int lowBP) {
        LowBP = lowBP;
    }

    public MenuItemCustomerSideView(String itemName, String charges, String temp, String itemId, String ingredients) {
        ItemName = itemName;
        Charges = charges;
        Temp = temp;
        ItemId = itemId;
        Ingredients1 = ingredients;
        Ingredients = new Vector<String>(Arrays.asList(ingredients.split("-")));

    }

    public MenuItemCustomerSideView(String itemName, String charges, String temp, String itemId) {
        ItemName = itemName;
        Charges = charges;
        Temp = temp;
        ItemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }


    public String getCharges() {
        return Charges;

    }

    public String getTemp() {
        return Temp;
    }

    public String getItemId() {
        return ItemId;
    }

    public Vector<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(Vector<String> ingredients) {
        Ingredients = ingredients;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setCharges(String charges) {
        Charges = charges;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public void setIngredients(String ingredients) {
        //Ingredients = (Vector < String > (ingredients.split("-"));

        Ingredients = new Vector<String>(Arrays.asList(ingredients.split("-")));

        //Arrays.asList(s1.split(",")))
    }
}
