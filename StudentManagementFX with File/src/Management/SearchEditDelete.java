/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rakib
 */
public class SearchEditDelete {

    static String sname, sid, sdept, ok;
    static boolean flg = false;
    static TextField showName, showId, showDept;
    static Button update;
    static Button deleteButton;
    static ImageView imageView;
    static Image image;

    public static void search(String cmd) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Search");
        stage.setResizable(false);
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.setHgap(2);
        layout.setVgap(2);
        TextField idSearch = new TextField();
        idSearch.setPromptText("Enter ID ex. CE-16037");
        //TextField.bindAutoCompletion(idSearch,"text to suggest", "another text to suggest");
        GridPane.setConstraints(idSearch, 0, 0);
        Button searchButton = new Button("Search");
        searchButton.setMinWidth(10);
        GridPane.setConstraints(searchButton, 3, 0);
        update = new Button("Update");
        GridPane.setConstraints(update, 3, 15);
        update.setVisible(false);
        deleteButton = new Button("Delete");
        GridPane.setConstraints(deleteButton, 3, 15);
        deleteButton.setVisible(false);
        searchButton.setOnAction(e -> {

            flg = false;
            File file = new File("info.txt");
            //File photo = new File("Store/");
            try {

                Scanner reader = new Scanner(file);
                ok = (String) idSearch.getText();
                ok = "ID :" + ok;

                while (reader.hasNextLine()) {
                    sid = reader.nextLine();
                    sname = reader.nextLine();
                    sdept = reader.nextLine();
                    if (sid.equals(ok)) {
                        flg = true;

                        sname = sname.replace("name :", "");
                        sid = sid.replace("ID :", "");
                        sdept = sdept.replace("Department : ", "");
                        //System.out.print(sid);
                        break;
                    }

                }
                reader.close();
            } catch (FileNotFoundException ex) {
                AlertMessage.display("Error", "File Not Found Exception", "Ok");
            }

            try {
                image = new Image(new FileInputStream("Store/" + sid + ".JPG"));
                imageView = new ImageView(image);
                GridPane.setConstraints(imageView, 20, 5);
            } catch (Exception ex) {
            }

            Label lName = new Label("Name :");
            GridPane.setConstraints(lName, 0, 5);
            showName = new TextField(sname);
            GridPane.setConstraints(showName, 4, 5);

            Label lId = new Label("ID :");
            GridPane.setConstraints(lId, 0, 8);
            showId = new TextField(sid);
            GridPane.setConstraints(showId, 4, 8);

            Label lDept = new Label("Department :");
            GridPane.setConstraints(lDept, 0, 10);
            showDept = new TextField(sdept);
            GridPane.setConstraints(showDept, 4, 10);

            if (flg) {
                {
                    if (cmd.equals("Update")) {
                        update.setVisible(true);
                        layout.getChildren().addAll(lName, showName, lId, showId, lDept, showDept, update);
                    } else if (cmd.equals("Delete")) {
                        deleteButton.setVisible(true);
                        layout.getChildren().addAll(lName, showName, lId, showId, lDept, showDept, deleteButton);
                    } else if (cmd.equals("Show")) {
                        imageView.setFitHeight(150);
                        imageView.setFitWidth(150);
                        layout.getChildren().addAll(lName, showName, lId, showId, lDept, showDept, imageView);
                        /*try{
                            //layout.setHgap(image.getHeight());
                           // layout.setVgap(image.getWidth());
                            imageView.setFitHeight(150);
                            imageView.setFitWidth(150);
                            layout.getChildren().add(imageView);
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }*/
                        //Group root = new Group(lName, showName, lId, showId, lDept, showDept,imageView);
                    }
                }
            } else {
                AlertMessage.display("Error", "Id not Matched", "Ok");

            }
        });
        //try {
        update.setOnAction(e -> {
            if (ConfirmLoginWindow.confirm()) {
                File temp = new File("temp.txt");
                File info = new File("info.txt");

                try {

                    temp.createNewFile();
                    PrintStream writer = new PrintStream(new FileOutputStream(temp, true));
                    Scanner reader = new Scanner(info);
                    while (reader.hasNextLine()) {
                        sid = reader.nextLine();
                        sname = reader.nextLine();
                        sdept = reader.nextLine();
                        if (sid.equals(ok)) {
                            try {
                                writer.append("ID :" + showId.getText());
                                writer.println();
                                writer.append("name :" + showName.getText());
                                writer.println();
                                writer.append("Department : " + showDept.getText());
                                writer.println();
                            } catch (Exception ex) {
                                AlertMessage.display("Message", "Empty block", "Ok");
                            }
                        } else {
                            writer.append(sid);
                            writer.println();
                            writer.append(sname);
                            writer.println();
                            writer.append(sdept);
                            writer.println();
                        }
                    }
                    reader.close();
                    writer.close();
                    info.delete();
                    temp.renameTo(info);

                    AlertMessage.display("Message", "Successfully Update", "Ok");
                    stage.close();

                } catch (IOException ex) {
                    AlertMessage.display("Error", "File not Created", "Ok");
                }
            }
        });
        deleteButton.setOnAction(e -> {
            if (ConfirmLoginWindow.confirm()) {
                File temp = new File("temp.txt");
                File info = new File("info.txt");

                try {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Do you want to delete?");
                    //alert.setContentText("Do you want to delete?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        temp.createNewFile();
                        PrintStream writer = new PrintStream(new FileOutputStream(temp, true));
                        Scanner reader = new Scanner(info);
                        while (reader.hasNextLine()) {
                            sid = reader.nextLine();
                            sname = reader.nextLine();
                            sdept = reader.nextLine();
                            if (sid.equals(ok)) {
                                //System.out.print("paisi");
                                continue;
                            } else {
                                writer.append(sid);
                                writer.println();
                                writer.append(sname);
                                writer.println();
                                writer.append(sdept);
                                writer.println();
                            }
                        }
                        reader.close();
                        writer.close();
                        info.delete();
                        info.delete();
                        temp.renameTo(info);

                        AlertMessage.display("Message", "Successfully Delete", "Ok");
                        stage.close();
                    } else {

                    }

                } catch (IOException ex) {
                    AlertMessage.display("Error", "File not Created", "Ok");
                }
            }
        });
        layout.getChildren().addAll(idSearch, searchButton);
        Scene scene = new Scene(layout, 450, 450);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
