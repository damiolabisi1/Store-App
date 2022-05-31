import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;

    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        view.getAddButton().setDisable(true);
        view.getCompleteSale().setDisable(true);
        view.getRemoveCart().setDisable(true);

        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });

        /**view.getRemoveCart().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleRemove();
            }
        });**/
        view.getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });

        view.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleSale();
            }
        });

        view.getStockList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.getAddButton().setDisable(true);
                Product item = view.getStockList().getSelectionModel().getSelectedItem();
                if (item != null){
                    view.getAddButton().setDisable(false);
                }
                else {
                    view.getAddButton().setDisable(true);
                }
            }
        });

        view.getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
               handleReset(primaryStage);
            }
        });


        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view,800,400));
        primaryStage.show();

    }


    public void handleAdd() {
        Product item = view.getStockList().getSelectionModel().getSelectedItem();
        if (item != null) {
            model.addtoCart(item);
            view.update();
        }

        }

    public void handleRemove(){
        int index = view.getCartList().getSelectionModel().getSelectedIndex();
        System.out.println(model.getCartStock()[index]); //It is selecting the right thing
        model.removeFromCart(index);
        view.update();
    }

    public void handleSale(){
        model.CompleteSale();
        //view.getPopularList().getItems().clear();
        view.update();
    }

    public void handleReset(Stage s){
        start(s);
    }

        public static void main(String[] args) {
        launch(args);
    }
}













