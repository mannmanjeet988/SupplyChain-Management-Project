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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
            public void handle(ActionEvent actionEvent) {        // on click search,it will provide particular product list
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

        Button signUpButton = new Button("Sign UP");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
//                globalLoginButton.setDisable(true);
//                customerEmailLabel.setText("Welcome " + customerEmail);
            }
        });
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
        gridPane.add(signUpButton,5,0);

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

    private GridPane signUpPage(){
        Label signUPemailLabel = new Label("Email");
        Label signUPpasswordLabel = new Label("Password");
        Label nameLabel = new Label("Name");
        Label mobileLabel = new Label("Mobile");

        TextField signUPemailTextField = new TextField();
        PasswordField signUPpasswordField = new PasswordField();
        TextField nameTextField = new TextField();
        TextField mobileTextField = new TextField();

        Button continueButton = new Button("Continue");
        Label createAccountLabel = new Label("Create Account");
        Label messageLabel = new Label("");
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = signUPemailTextField.getText();
                String password = signUPpasswordField.getText();
                String name = nameTextField.getText();
                String mobile = mobileTextField.getText();
                //messageLabel.setText(email + " $$ "+ password);
                if (login.customerSignUp(name,mobile,email, password)) {
                    messageLabel.setText(" Account created");
                    messageLabel.setTextFill(Color.DARKBLUE);
//                    customerEmail = email;
//                    globalLoginButton.setDisable(true);
//                    customerEmailLabel.setText("Welcome " + customerEmail);
//                    bodyPane.getChildren().clear();
//                    bodyPane.getChildren().add(productDetails.getAllProducts());

                } else {
                    messageLabel.setText(" OOPS! SOMETHING WENT WRONG. TRY AGAIN...");
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
        gridPane.add(nameLabel,10,12);
        gridPane.add(nameTextField,11,12);
        gridPane.add(mobileLabel,10,14);
        gridPane.add(mobileTextField,11,14);
        gridPane.add(signUPemailLabel, 10,16);
        //signUPemailLabel.setFont(new Font("Arial",30));
        gridPane.add(signUPemailTextField, 11, 16);
        gridPane.add(signUPpasswordLabel, 10, 18);
        gridPane.add(signUPpasswordField, 11, 18);
        gridPane.add(continueButton,11,20);
        gridPane.add(createAccountLabel,11,10);
        gridPane.add(messageLabel,11,22);
        createAccountLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 20));
        createAccountLabel.setTextFill(Color.BLUE);
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

        ImageView logo1 = new ImageView();
        ImageView logo2 = new ImageView();

        logo1.setImage(image);
        logo2.setImage(image2);

        logo1.setX(1);
        logo1.setY(1);
        logo1.setFitWidth(140);
        logo1.setFitHeight(50);

        logo2.setX(10);
         logo2.setY(640);
        logo2.setFitWidth(140);
        logo2.setFitHeight(50);
        
        Login login = new Login();
        root.setPrefSize(width, height+2*headerBar+10);

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(loginPage());
        bodyPane.getChildren().addAll(signUpPage());
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(),bodyPane, footerBar(),logo1,logo2);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        InputStream stream1 = null;

        try {
            stream1 = new FileInputStream("I:\\SupplyChainManjeet\\src\\main\\resources\\icon.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = new Image(stream1);
        stage.getIcons().add(image);
        stage.setTitle("  ONLINE SHOPPING!");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}