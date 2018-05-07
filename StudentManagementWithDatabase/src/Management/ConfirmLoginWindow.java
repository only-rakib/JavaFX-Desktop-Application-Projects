/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 *
 * @author Rakib
 */
public class ConfirmLoginWindow {

    static boolean flag;
    public static PasswordField enterPass;
    public static boolean confirm() {
        flag = false;
        Stage win = new Stage();
        win.initModality(Modality.APPLICATION_MODAL);
        win.setTitle("Confirm");
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setHgap(2);
        layout.setVgap(2);
        Label name = new Label("User Name:");
        GridPane.setConstraints(name, 1, 1);
        TextField enterName = new TextField();
        GridPane.setConstraints(enterName, 3, 1);

        Label pass = new Label("Password:");
        GridPane.setConstraints(pass, 1, 5);
        enterPass = new PasswordField();
        GridPane.setConstraints(enterPass, 3, 5);

        Button bconfirm = new Button("Confirm");
        GridPane.setConstraints(bconfirm, 3, 10);
        Button cancel = new Button("Cancel");
        GridPane.setConstraints(cancel, 7, 10);

        bconfirm.setOnAction(e -> {
             boolean value = false;
            try {
                value = AllContainer.login(enterName.getText(),enterPass.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ConfirmLoginWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (value) {
                flag = true;
                win.close();
            }
            else
            {  
                AlertMessage.display("Error", "User Name/Password Incorrect", "Ok");
                flag = false;
            }
        });
        cancel.setOnAction(e ->win.close());
        layout.getChildren().addAll(name, enterName, pass, enterPass, bconfirm, cancel);
        Scene scene = new Scene(layout, 400, 150);
        win.setScene(scene);
        win.showAndWait();
        return flag;
    }

}
