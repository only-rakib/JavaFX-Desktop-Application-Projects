/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author Rakib
 */
public class WhenAddPressed extends Application {

    String s;
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

        // JFileChooser saveFile = new JFileChooser();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
               
        );
        Button openButton = new Button("Open Image");
        GridPane.setConstraints(openButton, 40, 12);

        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            //File file1 = fileChooser.showSaveDialog(primaryStage);
            s = file.getAbsolutePath();
            

            //System.out.print(s);
        });

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
                File source = new File(s);
                File des = new File("Store/");
                File rName = new File("Store/"+enterID.getText()+".JPG");
                File tName  = new File("Store/"+source.getName());
                if (des.exists()) {
                    FileUtils.copyFileToDirectory(source, des);
                    tName.renameTo(rName);
                    System.out.println("paisi");
                }

            } catch (Exception ex) {
                AlertMessage.display("Error", "Input with no letter", "Ok");
            }

        });

        Button showCancel = new Button("Cancel");
        GridPane.setConstraints(showCancel, 8, 12);
        showCancel.setOnAction(e -> {
            OptionMenu option = new OptionMenu();
            try {
                option.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }

        });

        grid.getChildren().addAll(showName, enterName, showID, enterID, showDept, combobox, showSubmit, showCancel, openButton);
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
