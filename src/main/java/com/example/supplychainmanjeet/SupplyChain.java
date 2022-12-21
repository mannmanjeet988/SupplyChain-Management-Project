package com.example.supplychainmanjeet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class SupplyChain extends Application {

    public static final int width=700, height = 600,headerBar=50;
    Pane bodyPane = new Pane();


    Login login = new Login();
    ProductDetails  productDetails = new ProductDetails();
    Button globalLoginButton;
    Label customerEmailLabel = null;
    String customerEmail = null;


    private  GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {             // on click serach,it will provide particular product list
                String productName=searchText.getText();
                //clear body and put this new pane in body
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
            }
        });

        globalLoginButton = new Button("Log In");
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
//                globalLoginButton.setDisable(true);
//                customerEmailLabel.setText("Welcome " + customerEmail);
            }
        });

        customerEmailLabel = new Label("Welcome User");
        customerEmailLabel.setTextFill(Color.MISTYROSE);
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        //gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setStyle("-fx-background-color:BLUE ");
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(globalLoginButton,2,0);
        gridPane.add(customerEmailLabel,3,0);

        return gridPane;
    }
    //TextField emailTextField;

    private GridPane loginPage(){
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Label messageLabel = new Label("I am Label");
loginButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        String email = emailTextField.getText();
        String password = passwordField.getText();
        //messageLabel.setText(email + " $$ "+ password);
        if (login.customerLogin(email, password)) {
            messageLabel.setText(" Login Successful");
            customerEmail = email;
            globalLoginButton.setDisable(true);
            customerEmailLabel.setText("Welcome " + customerEmail);
            bodyPane.getChildren().clear();
            bodyPane.getChildren().add(productDetails.getAllProducts());

        } else {
            messageLabel.setText(" Login Failed");
        }
    }
});
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setStyle("-fx-background-color: #C0C0C0");
        //gridPane.setStyle("-fx-background-color:DIM-BLUE ");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        // first is x, second is y
        gridPane.add(emailLabel, 0,0);
        gridPane.add(emailTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton,0,2);
        gridPane.add(messageLabel,1,2);
        return gridPane;
    }

    private  GridPane footerBar(){
       // TextField searchText = new TextField();
        Button addToCartButton = new Button("Add to cart");
        Button buyNowButton = new Button("Buy Now");

        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              Product selectedProduct = productDetails.getSelectedProduct();
              if(Order.placeOrder(customerEmail,selectedProduct)){
                  messageLabel.setText("Ordered");
                  messageLabel.setTextFill(Color.DARKBLUE);
              }
              else{
                  messageLabel.setText("Order Failed");
                  messageLabel.setTextFill(Color.MISTYROSE);
              }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridPane.setStyle("-fx-background-color:YELLOWGREEN ");
        gridPane.setVgap(5);
        gridPane.setHgap(50);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar+height+5);
        gridPane.add(addToCartButton,0,0);
        gridPane.add(buyNowButton,1,0);
        gridPane.add(messageLabel,2,0);

        return gridPane;
    }

    private Pane createContent(){
        Pane root= new Pane();

        // to add image
        InputStream stream1 = null;
        InputStream stream2 = null;
        try {
            stream1 = new FileInputStream("I:\\SupplyChainManjeet\\src\\logo.png");
            stream2 = new FileInputStream("I:\\SupplyChainManjeet\\src\\main\\LOGO 2.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = new Image(stream1);
        Image image2 = new Image(stream2);

        ImageView imageView = new ImageView();
        ImageView imageView2 = new ImageView();

        imageView.setImage(image);
        imageView2.setImage(image2);

        //imageView.setX(10);
//        imageView.setY(1);
        imageView.setFitWidth(140);
        imageView.setFitHeight(50);

        imageView2.setX(10);
         imageView2.setY(640);
        imageView2.setFitWidth(140);
        imageView2.setFitHeight(50);


        Group logo= new Group(imageView);
        Group logo2= new Group(imageView2);

        Login login = new Login();
        root.setPrefSize(width, height+2*headerBar+10);

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(loginPage());
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(),bodyPane, footerBar(),logo,logo2);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("ONLINE SHOPPING!");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}