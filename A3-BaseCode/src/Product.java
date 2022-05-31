//Base class for all products the store will sell
public class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;
    private boolean available;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        cartQuantity = 0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }


    public int getCart(){
        return cartQuantity;
    }
    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0){ //&& stockQuantity >= amount) {
            //stockQuantity -= amount;
            soldQuantity += amount;
            cartQuantity -= amount;
            return price * amount;
        }
        return 0.0;
    }

    public void IncreaseCart() {
        if (stockQuantity > 0 ) {
            stockQuantity = stockQuantity - 1;
            cartQuantity++;
        }
    }
    public void DecreaseCart() {
        if (stockQuantity >= 0) {
            stockQuantity = stockQuantity + 1;
            cartQuantity--;
        }
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public boolean isAvailable() {
        return available;
    }

}