/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class WhenAddPressed extends Application {

    String comString;
    TextField chooseDept;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Add Student Information");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(2);
        grid.setVgap(10);

        Label showName = new Label("Name :");
        GridPane.setConstraints(showName, 5, 5);
        TextField enterName = new TextField();
        enterName.setPromptText("ex Rakib");
        GridPane.setConstraints(enterName, 7, 5);

        Label showID = new Label("Enter ID:");
        GridPane.setConstraints(showID, 5, 7);
        TextField enterID = new TextField();
        enterID.setPromptText("ex CE-16037");
        GridPane.setConstraints(enterID, 7, 7);

        Label showDept = new Label("Department ");
        GridPane.setConstraints(showDept, 5, 9);
        ComboBox<String> combobox = new ComboBox<>();
        combobox.getItems().addAll(
                "CSE",
                "ICT",
                "Textile",
                "Others"
        );
        combobox.setPromptText("Select");
        GridPane.setConstraints(combobox, 7, 9);
        combobox.setOnAction((ActionEvent e) -> {
            comString = (String) combobox.getValue();
            if (comString.equals("Others")) {
                chooseDept = new TextField();
                chooseDept.setPromptText("Enter Department");

                GridPane.setConstraints(chooseDept, 7, 10);
                grid.getChildren().add(chooseDept);
            }
        });

        Button showSubmit = new Button("Submit");
        GridPane.setConstraints(showSubmit, 5, 12);
        showSubmit.setOnAction(e -> {

            try {
                if (comString.equals("Others")) {
                    comString = chooseDept.getText();

                }
                if (ConfirmLoginWindow.confirm()) {
                    AllContainer.print((String) enterName.getText(), (String) enterID.getText(), comString);
                    OptionMenu opt = new OptionMenu();
                    try {
                        opt.start(primaryStage);

                    } catch (Exception ex) {
                        AlertMessage.display("Error", "Window Not Found", "Ok");
                    }
                }

            } catch (Exception ex) {
                AlertMessage.display("Error", "Input with no letter", "Ok");
            }

        });

        Button showCancel = new Button("Cancel");
        GridPane.setConstraints(showCancel, 10, 12);
        showCancel.setOnAction(e -> {
            OptionMenu option = new OptionMenu();
            try {
                option.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }

        });

        grid.getChildren().addAll(showName, enterName, showID, enterID, showDept, combobox, showSubmit, showCancel);
        Scene scene = new Scene(grid, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
