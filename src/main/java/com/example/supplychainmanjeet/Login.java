package com.example.supplychainmanjeet;

import java.sql.ResultSet;

public class Login{

    public boolean customerLogin(String email, String password){
        String query = String.format("SELECT * FROM CUSTOMERINFO  WHERE email = '%s' AND password = '%s'",email,password);

    try{
        DatabaseConnection dbCon = new DatabaseConnection();
        ResultSet rs = dbCon.getQueryTable(query);
        if(rs!=null && rs.next()){
            return true;
        }
    } catch(Exception e){
        e.printStackTrace();
    }

    return false;
    }

    public static void main(String[] args){
        Login login= new Login();
        System.out.println(login.customerLogin("mannmanjeet988@gmail.com","manjeet123"));
    }
}
