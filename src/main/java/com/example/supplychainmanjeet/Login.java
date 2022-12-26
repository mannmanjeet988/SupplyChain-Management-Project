package com.example.supplychainmanjeet;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login{

    public static byte[] getSHA(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
               return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            }

        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    String getEncryptedPassword(String password){
        String encryptedPassword="";
        try{
            BigInteger number = new BigInteger(1, getSHA(password));
            StringBuilder hexstring = new StringBuilder(number.toString(16));
            return hexstring.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public boolean customerLogin(String email, String password) {
        String hashValue= getEncryptedPassword( password);
        String query = String.format("SELECT * FROM CUSTOMERINFO  WHERE email = '%s' AND password = '%s'", email, hashValue);

        try {
            DatabaseConnection dbCon = new DatabaseConnection();
            ResultSet rs = dbCon.getQueryTable(query);
            if (rs != null && rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

        public  boolean customerSignUp(String name, String mobile, String email, String password) {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            String hashValue1= getEncryptedPassword( password);
            String query1 = String.format("select customer_id from customerinfo order by customer_id desc limit 1");
             ResultSet rs1= databaseConnection.getQueryTable(query1);
//            int mobileNum= 9999999;
//            String first_name= "Anjali";
          int rowCount1 = 11;
            try{
            while(rs1.next()){
                rowCount1 = (rs1.getInt("customer_id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

            String query2 = String.format(("INSERT INTO Customerinfo (customer_id, email, password,first_name,mobile) VALUES ('%d', '%s','%s','%s','%s')"),
                    (rowCount1+1) , email, hashValue1,name,mobile);

            int rowCount = 0;
            //int rowCount=0;
            try{
                rowCount= databaseConnection.executeUpdateQuery(query2);
            } catch(Exception e){

            }
            return rowCount!=0;
        }

//    public static void main(String[] args){
//        Login login= new Login();
//        System.out.println(login.customerLogin("mannmanjeet988@gmail.com","manjeet123"));
//    }
//    public static void main(String[] args){
//        Login login = new Login();
//        System.out.println(login.getEncryptedPassword("123"));
//    }
}
