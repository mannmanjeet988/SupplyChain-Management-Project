package com.example.supplychainmanjeet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;

public class Product extends Pane {
    private SimpleIntegerProperty productID;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getProductID() {
        return productID.get();
    }

    public double  getPrice() {
        return price.get();
    }

    public String  getName() {
        return name.get();
    }

    public Product(int productID, String name, double price){
        this.productID= new SimpleIntegerProperty(productID);
        this.name= new SimpleStringProperty(name);
        this.price= new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectproducts="SELECT * FROM product";
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectproducts);
            while(rs.next()){
                productList.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                        )
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public static ObservableList<Product> getProductsByName(String productName){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
       // String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        String selectproducts=String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectproducts);
            while(rs.next()){
                productList.add(new Product(rs.getInt("product_id"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}