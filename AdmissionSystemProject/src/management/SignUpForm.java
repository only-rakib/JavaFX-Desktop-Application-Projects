/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author Rakib
 */
public class SignUpForm extends Application {

    private Label labelName, labelEmail, labelPassword;
    private TextField textFieldName, textFieldEmail;
    private Button buttonSignUp, buttonCancel;
    private PasswordField passwordFieldPassword;

    @Override
    public void start(Stage primaryStage) throws Exception {
        EmailValidator emailValidator = EmailValidator.getInstance();
        primaryStage.setTitle("Sign Up");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        labelName = new Label("Enter Name");
        GridPane.setConstraints(labelName, 1, 2);
        textFieldName = new TextField();
        GridPane.setConstraints(textFieldName, 4, 2);

        labelEmail = new Label("Enter Email");
        GridPane.setConstraints(labelEmail, 1, 4);
        textFieldEmail = new TextField();
        GridPane.setConstraints(textFieldEmail, 4, 4);

        labelPassword = new Label("Password");
        GridPane.setConstraints(labelPassword, 1, 6);
        passwordFieldPassword = new PasswordField();
        GridPane.setConstraints(passwordFieldPassword, 4, 6);

        StringBinding validEmailExpr = Bindings.createStringBinding(
                () -> emailValidator.isValid(textFieldEmail.getText()) ? textFieldEmail.getText() : "",
                textFieldEmail.textProperty()
        );

        buttonSignUp = new Button("Sign Up");
        GridPane.setConstraints(buttonSignUp, 8, 8);
        buttonSignUp.disableProperty().bind(
            textFieldEmail.textProperty().isEqualTo(validEmailExpr).not()
            .or(
                     textFieldEmail.textProperty().isEmpty()
                )
        );
        buttonSignUp.setOnAction(e->{
            
            try{
                AlertMessage.display("Successful", "Successfully registered", "Ok");
                LoginPageForAdmin login = new LoginPageForAdmin();
                login.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });

        buttonCancel = new Button("Cancel");
        GridPane.setConstraints(buttonCancel, 1, 8);
        buttonCancel.setOnAction(e -> {
            LoginPageForAdmin login = new LoginPageForAdmin();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {

            }
        });
        grid.getChildren().addAll(labelName, labelEmail, labelPassword, textFieldName, textFieldEmail, buttonSignUp, passwordFieldPassword, buttonCancel);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
