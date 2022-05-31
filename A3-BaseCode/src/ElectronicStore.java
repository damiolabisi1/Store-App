//Class representing an electronic store
//Has an array of products that represent the items the store can sell

public class ElectronicStore {
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  private int cartProducts;
  private int sales;
  private double sale;
  private String name;
  private Product[] stock; //Array to hold all products
  private double revenue;
  private Product[] cart; //Array to hold products added to Cart
  //private ArrayList<Product> cart;
  private double CurrentCart;

  public ElectronicStore(String initName) {
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    cartProducts = 0;
    sales = 0;
    sale = 0.0;
    CurrentCart = 0.0;
    //cart = new ArrayList<Product>();
    cart = new Product[MAX_PRODUCTS];
  }

  public static ElectronicStore createStore() {
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }

  public String getName() {
    return name;
  }

  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct) {
    if (curProducts < MAX_PRODUCTS) {
      stock[curProducts] = newProduct;
      curProducts++;
      return true;
    }
    return false;
  }


  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount) {
    if (index >= 0 && index < curProducts) {
      revenue += stock[index].sellUnits(amount);
    }
  }

  public double getCurrentCart() {
    return CurrentCart;
  }

  public double getRevenue() {
    return revenue ;
  }

  public int getSales() {
    return sales;
  }

  public double getSale(){
    sale = (double) (revenue/sales);
    return sale;
  }

  //Prints the stock of the store
  public void printStock() {
    for (int i = 0; i < curProducts; i++) {
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }

  public Product[] getStock() {
    return stock;
  }

  public Product[] getCart(){
    return cart;
  }

  public void addtoCart(Product item) {
    if (item != null) {
      CurrentCart += item.getPrice();
      if (!isObjFound(item, cart)) {
        cart[cartProducts] = item;
        cartProducts++;
      }

      if (isObjFound(item, cart)) {
        item.IncreaseCart();
      }
    }
  }
  public void removeFromCart(int item) {
    if (cart[item] != null) {
      //CurrentCart -= cart[item].getPrice();
      if (isObjFound(cart[item], cart) && cart[item].getStockQuantity() <= MAX_PRODUCTS) {
        CurrentCart -= cart[item].getPrice();
        cart[item].DecreaseCart();

      }
      if (cart[item].getCart() <= 0) {
        removeCart(item);
      }
    }

  }

  public void CompleteSale(){
    sales += 1;
    revenue += CurrentCart;
    for (Product s: cart) {
      if (s != null) {
        s.sellUnits(s.getCartQuantity());
      }
    }
    CurrentCart = 0.0;
  }


  public Product[] getProductByString(String p){
    for (int i = 0; i < MAX_PRODUCTS; i++){
      if (cart != null && p.contains(cart.toString())){
        return cart;
      }
    }
    return null;
  }

  public boolean isObjFound(Product item, Product[] cart){
    for (Product p : cart)
      if (p != null)
        if (p.equals(item))
          return true;

    return false;
  }

  public boolean isCartFound(Product item, Product[] stock){
    for (Product p : stock) {
      if (p == item) {
        return true;
      }
    }
    return false;
  }

  public Product[] getStockList() {
    int count = 0;
    for (int i = 0; i < curProducts; i++)
      if (stock[i].getStockQuantity() > 0){
        count++;
      }
    Product[] nonNullstock = new Product[count];

    int newcount = 0;
    for (int i = 0; i < stock.length; i++) {
      if (stock[i] != null && stock[i].getStockQuantity() > 0) {
        nonNullstock[newcount++] = stock[i];
      }
    }
    return nonNullstock;
  }

  public int getStockCounter(Product item){
    for (int i = 0; i < MAX_PRODUCTS; i++){
      if(item == stock[i]){
        return i;
      }
    }
    return 0;
  }

  public int getCartCounter(Product item){
    for (int i = 0; i < MAX_PRODUCTS; i++){
      if(item == cart[i]){
        return i;
      }
    }
    return 0;
  }

  public void removeFromStock(int item){
    if(item >= 0 && item < curProducts) {
      stock[item] = null;
      for (int i = item + 1; i < curProducts; i++) {
        Product s = stock[i - 1];
        stock[i - 1] = stock[i];
        stock[i] = s;
      }
      curProducts--;
    }
  }

  public void removeCart(int item){
    if (item >= 0 && item < cartProducts){
      cart[item] = null;
      for(int i = item + 1; i < cartProducts; i++){
        Product p = cart[i-1];
        cart[i-1] = cart[i];
        cart[i] = p;
      }
      cartProducts--;
    }
  }


  public Product[] getPopularList() {
    Product[] popularstock = new Product[3];
    //if (sale == 0) {
    for (int i = 0; i < 3; i++) {
      popularstock[i] = stock[i];
    }
  return popularstock;
  }

  public Product[] getPopularProduct(){
    Product[] popularStock = new Product[3];
    Product[] Pstock = new Product[getCurProducts()];

    for (int i = 0; i < Pstock.length; i++) {
      Pstock[i] = stock[i];
    }
    for (int j = 0; j <= Pstock.length-1; j++) {
      for (int i = 0; i < Pstock.length - j - 1; i++)
        if (Pstock[i + 1] != null && Pstock[i] != null)
          if (Pstock[i].getSoldQuantity() < Pstock[i + 1].getSoldQuantity()) {
            Product stock = Pstock[i];
            Pstock[i] = Pstock[i + 1];
            Pstock[i + 1] = stock;
          }
    }
    for (int i = 0; i < 3; i++) {
      popularStock[i] = Pstock[i];

    }
    return popularStock;
  }

  public String[] getCartStock() {
    int count = 0;
    for (int i = 0; i < cartProducts; i++){
      if (cart[i].getCartQuantity() > 0 )
        count++;
    }
    String[] Cstock = new String[count];
    int newcount = 0;
    for (int i = 0; i < cartProducts; i++){
      if (cart[i] != null){
        if (cart[i].getCartQuantity() > 0) {
          Cstock[newcount++] = cart[i].getCartQuantity() + " x " + cart[i];
      }}
    }
    return Cstock;
  }

  public int getCurProducts(){
    return curProducts;
  }

  public int getCartProducts(){

    return cartProducts;
  }

}