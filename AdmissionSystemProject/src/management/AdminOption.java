/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class AdminOption extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Option");
        primaryStage.setResizable(false);
        VBox box = new VBox(5);
        box.setPadding(new Insets(10, 50, 50, 50));
        box.setSpacing(10);
        Button buttonUnitInput = new Button("Unit Modify");
        Button buttonResultInput = new Button("Result Input");
        Button buttonPassMark = new Button("Enter Pass Mark");
        Button unitMerit = new Button("Merit List");
        Button buttonBack = new Button("Back");
        
        buttonUnitInput.setOnAction(e->{
            UnitInput unitInput = new UnitInput();
            try {
                
                unitInput.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        buttonResultInput.setOnAction(e->{
            
            try {
               
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        buttonPassMark.setOnAction(e->{
            
            try {
               
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        unitMerit.setOnAction(e->{
            ShowMeritList meritList = new ShowMeritList();
            try{
                meritList.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        
        });
        
        
        buttonBack.setOnAction(e->{
            MainMenu mm = new MainMenu();
            try {
                mm.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        
        box.getChildren().addAll(buttonUnitInput,buttonResultInput,buttonPassMark,unitMerit,buttonBack);
        Scene scene = new Scene(box, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
