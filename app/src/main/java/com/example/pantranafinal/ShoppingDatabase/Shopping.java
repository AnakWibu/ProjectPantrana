package com.example.pantranafinal.ShoppingDatabase;

public class Shopping {

    public String ShoppingName, ShoppingExpired;
    public int ShoppingQuantity;

    public Shopping(String shoppingName, String shoppingExpired, int shoppingQuantity) {
        ShoppingName = shoppingName;
        ShoppingExpired = shoppingExpired;
        ShoppingQuantity = shoppingQuantity;
    }

    public String getShoppingName() {
        return ShoppingName;
    }

    public void setShoppingName(String shoppingName) {
        ShoppingName = shoppingName;
    }

    public String getShoppingExpired() {
        return ShoppingExpired;
    }

    public void setShoppingExpired(String shoppingExpired) {
        ShoppingExpired = shoppingExpired;
    }

    public int getShoppingQuantity() {
        return ShoppingQuantity;
    }

    public void setShoppingQuantity(int shoppingQuantity) {
        ShoppingQuantity = shoppingQuantity;
    }
}
