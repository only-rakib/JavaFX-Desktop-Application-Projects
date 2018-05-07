/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Rakib
 */
public class LoginPage extends Application {
    
    private GridPane grid;
    private Button login,cancel,clear;
    private Label name,pass;
    private TextField sname;
    private PasswordField spass;
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Login Page");
        //primaryStage.setResizable(false);
        grid = new GridPane();
        grid.setPadding(new Insets(8,8,8,8));
        grid.setHgap(10);
        grid.setVgap(10);

        name = new Label("User Name:");
        GridPane.setConstraints(name, 2, 4);
        sname = new TextField();
        GridPane.setConstraints(sname, 3, 4);
        sname.setPromptText("User Name");
        
        pass = new Label("Password");
        GridPane.setConstraints(pass, 2, 6);
        spass = new PasswordField();
        GridPane.setConstraints(spass,3,6);
        spass.setPromptText("Password");
        
        login = new Button("Login");
        GridPane.setConstraints(login, 2, 8);
        login.setOnAction(e->{
            boolean result = AllContainer.login(sname.getText(),spass.getText());
            if(result)
            {
                AlertMessage.display("Welcome", "Successfully Login", "Ok");
                OptionMenu op = new OptionMenu();
                try {
                    op.start(primaryStage);
                    
                } 
                catch (Exception ex) {
                    AlertMessage.display("Error", "Window Not Found", "Ok");
                }
            }
            else
            {
                AlertMessage.display("Error", "Incorrect User/Password", "Ok");
            }
        });
        
        clear = new Button("Clear");
        GridPane.setConstraints(clear, 3, 8);
        clear.setOnAction(e->{
            sname.setText("");
            spass.setText("");
        });
        
        cancel = new Button("Cancel");
        GridPane.setConstraints(cancel, 4, 8);
        cancel.setOnAction(e->primaryStage.close());
        
        grid.getChildren().addAll(name,sname,pass,spass,login,clear,cancel);
        Scene scene = new Scene(grid,350,250);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
}
