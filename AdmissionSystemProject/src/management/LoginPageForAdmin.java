/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;


import javafx.application.Application;
import static javafx.application.Application.launch;
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
 * 
 */
public class LoginPageForAdmin extends Application {
    
    private GridPane grid;
    private Button login,cancel,buttonClear,buttonCreateAccount;
    private Label name,pass;
    
    private TextField textFieldName;
    private PasswordField passwordFieldPass;
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
        textFieldName = new TextField();
        GridPane.setConstraints(textFieldName, 3, 4);
        textFieldName.setPromptText("User Name");
        
        pass = new Label("Password");
        GridPane.setConstraints(pass, 2, 6);
        passwordFieldPass = new PasswordField();
        GridPane.setConstraints(passwordFieldPass,3,6);
        passwordFieldPass.setPromptText("Password");
        
        login = new Button("Login");
        GridPane.setConstraints(login, 2, 8);
        login.setOnAction(e->{
            /*//boolean result = AllContainer.login(sname.getText(),spass.getText());
            if(result)
            {
                AlertMessage.display("Welcome", "Successfully Login", "Ok");
                //OptionMenu op = new OptionMenu();
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
            }*/
            AdminOption adminOption = new AdminOption();
            try{
                adminOption.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        
        buttonClear = new Button("Clear");
        GridPane.setConstraints(buttonClear, 3, 8);
        buttonClear.setOnAction(e->{
            textFieldName.setText("");
            passwordFieldPass.setText("");
        });
        
        cancel = new Button("Cancel");
        GridPane.setConstraints(cancel, 4, 8);
        cancel.setOnAction(e->{
            
            MainMenu mm = new MainMenu();
            try {
                mm.start(primaryStage);
            } catch (Exception ex) {
                management.AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        buttonCreateAccount = new Button("Create New Account");
        GridPane.setConstraints(buttonCreateAccount, 4, 10);
        buttonCreateAccount.setOnAction(e->{
            SignUpForm signUpForm = new SignUpForm();
            try{
                 signUpForm.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        grid.getChildren().addAll(name,textFieldName,pass,passwordFieldPass,login,buttonClear,cancel,buttonCreateAccount);
        Scene scene = new Scene(grid,400,250);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        }
    
    
}
