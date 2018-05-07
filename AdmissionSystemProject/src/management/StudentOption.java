/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
/**
 *
 * 
 */
public class StudentOption extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Option");
        primaryStage.setResizable(false);
        VBox box = new VBox(5);
        box.setPadding(new Insets(10, 50, 50, 50));
        box.setSpacing(10);
        Button buttonRegistration = new Button("Registration");
        Button buttonResult = new Button("Result");
        //Button buttonSubjectChoice = new Button("Subject Choice");
        Button buttonBack = new Button("Back");
        
        
        buttonRegistration.setOnAction(e->{
            StudentRegistrationForm form = new StudentRegistrationForm();
            try {
                form.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        buttonResult.setOnAction(e->{
            ResultForm result = new ResultForm();
            try{
                result.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        /*buttonSubjectChoice.setOnAction(e->{
            LoginPageForStudents login = new LoginPageForStudents();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });*/
        
        buttonBack.setOnAction(e->{
            MainMenu mm = new MainMenu();
            try {
                mm.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        
        box.getChildren().addAll(buttonRegistration,buttonResult,buttonBack);
        Scene scene = new Scene(box, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public static void main(String[] args) {
        launch(args);
    }
}
