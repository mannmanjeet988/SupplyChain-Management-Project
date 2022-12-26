package com.example.supplychainmanjeet;

import java.sql.ResultSet;
import java.sql.Statement;

public class Order {

    public static boolean placeOrder(String customerEmail, Product product)
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("INSERT INTO orders (customer_id, product_id) VALUES ((SELECT customer_id FROM customerinfo WHERE email = '%s'), %s)", customerEmail, product.getProductID());
        int rowCount = 0;
        //int rowCount=0;
        try{
             rowCount= databaseConnection.executeUpdateQuery(query);
        } catch(Exception e){

    }
        return rowCount!=0;
    }


//    public int executeUpdateQuery(String query){
//        Statement statement = getStatement();
//        try{
//            return statement.executeUpdate(query);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return 0;
//    }

}
