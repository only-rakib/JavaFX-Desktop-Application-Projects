/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class AlertMessage {
    public static void display(String title,String mgs,String acButton)
    {
        Stage window =new Stage() ;
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(150);
        window.setResizable(true);
        Label show = new Label(mgs);
        Button button;
        button = new Button(acButton);
        button.setOnAction(e->{
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(show,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,400,150);
        window.setScene(scene);
        window.showAndWait();
    
    }

}
