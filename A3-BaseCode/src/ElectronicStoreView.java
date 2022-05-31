import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ElectronicStoreView extends Pane{

    private ListView<Product> PopularList;
    private Label label3;
    private Label label4;
    private Label label7;
    private TextField StockField;
    private TextField PopularField;
    private TextField SaleField;
    private TextField RevenueField;
    //private TextField CartField;
    private TextField SalesField;
    private Button RemoveCart;
    private Button AddCart;
    private Button ResetStore;
    private Button CompleteSale;
    ElectronicStore model;
    private ListView<Product> StockList;
    private ListView<String> CartList;


    public ElectronicStoreView(ElectronicStore initModel){
        model = initModel;

        Pane aPane = new Pane();
        aPane.setStyle("-fx-background-color: white; " +
                "-fx-border-color: gray; " +
                "-fx-padding: 4 4;");

        //Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(50,10);

        Label label2 = new Label("# Sales:");
        label2.relocate(40,35);

        label3 = new Label("Revenue:");
        label3.relocate(32,70);

        label4 = new Label("$ / Sale:");
        label4.relocate(38,105);

        Label label5 = new Label("Most Popular Items:");
        label5.relocate(38,140);

        Label label6 = new Label("Store Stock:");
        label6.relocate(300,10);

        //double Cost = model.getRevenue();
        label7 = new Label("Current Cart: ($" + String.format("%.2f",model.getCurrentCart()) + "):");
        label7.relocate(600,10);

        //Create the TextFields
        SalesField = new TextField("0");
        SalesField.relocate(82,30);
        SalesField.setPrefSize(100,30);

        RevenueField = new TextField("0.00");
        RevenueField.relocate(82,65);
        RevenueField.setPrefSize(100,30);

        SaleField = new TextField("N/A");
        SaleField.relocate(82,100);
        SaleField.setPrefSize(100,30);

        PopularField = new TextField();
        PopularField.relocate(10,170);
        PopularField.setPrefSize(170,162);

         /**StockField = new TextField();
        StockField.relocate(190,30);
        StockField.setPrefSize(285,302);**/



        //Create Button
        ResetStore = new Button("Reset Store");
        ResetStore.relocate(25,336);
        ResetStore.setPrefSize(142,48);

        AddCart = new Button("Add to Cart");
        AddCart.relocate(258,336);
        AddCart.setPrefSize(142,48);

        RemoveCart = new Button("Remove from Cart");
        RemoveCart.relocate(485,336);
        RemoveCart.setPrefSize(142,48);

        CompleteSale = new Button("Complete Sale");
        CompleteSale.relocate(627,336);
        CompleteSale.setPrefSize(142,48);


        //ListView
        StockList = new ListView<Product>();
        Product[] stock = model.getStockList();
        ObservableList<Product> list = FXCollections.observableArrayList(stock);
        StockList.setItems(list);
        StockList.relocate(190,30);
        StockList.setPrefSize(285,302);

        PopularList = new ListView<Product>();
        //PopularList.getItems().add(model.getStock()[0]);
        //PopularList.getItems().add(model.getStock()[1]);
        //PopularList.getItems().add(model.getStock()[2]);
        Product[] popular = model.getPopularList();
        ObservableList<Product> list1 = FXCollections.observableArrayList(popular);
        PopularList.setItems(list1);
        PopularList.relocate(10,170);
        PopularList.setPrefSize(170,162);

        CartList = new ListView<String>();
        CartList.relocate(485,30);
        CartList.setPrefSize(285,302);



        getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,
                SalesField,RevenueField,SaleField,CartList,PopularField,
                ResetStore,AddCart,RemoveCart,CompleteSale,StockList,PopularList);

        /**
        primaryStage.setTitle("Electronic Store Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane,800,400));
        primaryStage.show();
         **/

    }

    public Button getResetStore(){return ResetStore;}
    public Button getAddButton(){return AddCart;}
    public Button getRemoveCart(){return RemoveCart;}
    public Button getCompleteSale(){return CompleteSale;}

    public TextField getSalesField(){return SalesField;}
    public TextField getRevenueField(){return RevenueField;}
    public TextField getSaleField(){return SaleField;}
    public TextField getPopularField(){return PopularField;}
    public TextField getStockField(){return StockField;}
    //public TextField getCartField(){return CartField;}

    public ListView<Product> getStockList(){return StockList;}
    public ListView<String>getCartList(){return CartList;}
    public ListView<Product> getPopularList(){return PopularList;}

    public Label getLabel7(){return label7;}



    public void update() {
        CartList.setItems(FXCollections.observableArrayList(model.getCartStock()));
        StockList.setItems(FXCollections.observableArrayList(model.getStockList()));

        PopularList.setItems(FXCollections.observableArrayList(model.getPopularProduct()));


        if(CartList != null){
            CompleteSale.setDisable(false);
        }
        int selection = CartList.getSelectionModel().getSelectedIndex();
        if(selection >= 0) {
            RemoveCart.setDisable(false);
        }else
            RemoveCart.setDisable(true);

        //label3.setText("Revenue:" + model.getRevenue());
        RevenueField.setText(String.format("%.2f", model.getRevenue()));
        //label4.setText("$ / Sale:" + model.getSales());
        SalesField.setText("" + model.getSales());
        label7.setText("Current Cart: ($" + String.format("%.2f",model.getCurrentCart()) + "):");

        if (model.getSales() == 0){
            SaleField.setText("N/A");
        }
        else
            SaleField.setText(String.format("%.2f", model.getSale()));


    }

}
