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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class ResultForm extends Application {

    private Label labelRoll;
    private Button buttonSubmit, buttonCancel, buttonSubjectChoice;
    private TextField textFieldRoll;

    private String unit;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Result");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(4);

        labelRoll = new Label("Roll");
        GridPane.setConstraints(labelRoll, 2, 2);
        textFieldRoll = new TextField();
        GridPane.setConstraints(textFieldRoll, 3, 2);

        buttonSubmit = new Button("Submit");
        GridPane.setConstraints(buttonSubmit, 4, 5);

        buttonSubjectChoice = new Button("Subject Choice");
        GridPane.setConstraints(buttonSubjectChoice, 3, 5);
        buttonSubjectChoice.setVisible(false);
        buttonSubmit.setOnAction(e -> {
            if (textFieldRoll.getText().equals("")) {
                AlertMessage.display("Error", "Enter Roll", "OK");
            } else {
                unit = database.getUnit(textFieldRoll.getText());
                int merit = database.getMeritPos(textFieldRoll.getText());
                //String s1=database.get_Sub1(textFieldRoll.getText());
                if(unit != null)
                {
                    AlertMessage.display("Success", "Unit " + unit+"  Merit "+merit, "OK");
                }
                
                if (database.get_Sub1(textFieldRoll.getText()) != null) {
                    buttonSubjectChoice.setVisible(false);
                } else {
                    
                    buttonSubjectChoice.setVisible(true);
                }
                //buttonSubjectChoice.setVisible(true);
            }

        });
        buttonCancel = new Button("Cancel");
        GridPane.setConstraints(buttonCancel, 1, 5);
        buttonCancel.setOnAction(e -> {
            StudentOption student = new StudentOption();

            try {
                student.start(primaryStage);
            } catch (Exception ex) {

            }
        });
        buttonSubjectChoice.setOnAction(e -> {
            LoginPageForStudents login = new LoginPageForStudents();
            login.setRoll(textFieldRoll.getText());
            login.setUnit(unit);
            try {
                login.start(primaryStage);
            } catch (Exception ex) {

            }
        });
        grid.getChildren().addAll(labelRoll, buttonSubmit, buttonCancel, buttonSubjectChoice, textFieldRoll);
        Scene scene = new Scene(grid, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
