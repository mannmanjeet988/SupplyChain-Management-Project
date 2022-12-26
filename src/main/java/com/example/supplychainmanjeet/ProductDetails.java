package com.example.supplychainmanjeet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

public TableView<Product> productTable;

public Pane getAllProducts(){
    TableColumn id =  new TableColumn("Id");
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    TableColumn name =  new TableColumn("Name");
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    TableColumn price =  new TableColumn("Price");
    price.setCellValueFactory(new PropertyValueFactory<>("price"));

//    ObservableList<Product> data = FXCollections.observableArrayList();
//    data.add(new Product(1,"Lenovo", 8500));
//    data.add(new Product(1,"HP", 9000));

ObservableList<Product> products=  Product.getAllProducts();

    productTable = new TableView<>();
    productTable.setItems(products);
    productTable.getColumns().addAll(id,name,price);
    productTable.setMinSize(SupplyChain.width,SupplyChain.height);
    productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the extra column appeared

    Pane tablepane = new Pane();
   // tablepane.setStyle("-fx-background-color:#C0C0C0");      //for background color of table
    tablepane.getChildren().add(productTable);
    tablepane.setMinSize(SupplyChain.width,SupplyChain.height);
    return tablepane;
}

    public Pane getProductsByName(String productName){
        TableColumn id =  new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("productID"));
        TableColumn name =  new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price =  new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//    ObservableList<Product> data = FXCollections.observableArrayList();
//    data.add(new Product(1,"Lenovo", 8500));
//    data.add(new Product(1,"HP", 9000));

        ObservableList<Product> products=  Product.getProductsByName(productName);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the extra column appeared

        Pane tablepane = new Pane();
        // tablepane.setStyle("-fx-background-color:#C0C0C0");      //for background color of table
        tablepane.getChildren().add(productTable);
        tablepane.setMinSize(SupplyChain.width,SupplyChain.height);
        return tablepane;

    }

    public Product getSelectedProduct()
    {
        try{
            Product selectProduct = productTable.getSelectionModel().getSelectedItem();
            return selectProduct;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
