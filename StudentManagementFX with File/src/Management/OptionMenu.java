/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class OptionMenu extends Application{

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Option");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(5);
        grid.setVgap(4);
        
        Button add = new Button("ADD");
        GridPane.setConstraints(add, 7, 4);
        Button edit = new Button("Edit");
        GridPane.setConstraints(edit, 7, 8);
        Button delete = new Button("Delete");
        GridPane.setConstraints(delete, 7, 12);
        Button showInfo = new Button("Show");
        GridPane.setConstraints(showInfo,7, 16);
        
        add.setOnAction(e->{
            WhenAddPressed obj = new WhenAddPressed();
            try {
                obj.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        showInfo.setOnAction(E->{
            try {
                SearchEditDelete.search("Show");
            } catch (Exception ex) {
                Logger.getLogger(OptionMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        edit.setOnAction(e->{
            try {
                SearchEditDelete.search("Update");
            } catch (Exception ex) {
                Logger.getLogger(OptionMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delete.setOnAction(e->{
            try {
                SearchEditDelete.search("Delete");
            } catch (Exception ex) {
                Logger.getLogger(OptionMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        grid.getChildren().addAll(add,edit,delete,showInfo);
        Scene scene = new Scene(grid,300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
