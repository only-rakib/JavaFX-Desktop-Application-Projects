/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

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

    public static void search(String cmd) throws SQLException, ClassNotFoundException {
        //Database connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery("select * from info");

        Stage stage = new Stage();
        stage.setTitle("Search");
        stage.setResizable(false);
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.setHgap(2);
        layout.setVgap(2);
        TextField idSearch = new TextField();
        idSearch.setPromptText("Enter ID ex. CE-16037");
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

            try {
                ok = (String) idSearch.getText();

                while (res.next()) {
                    sid = res.getString(1);
                    sname = res.getString(2);
                    sdept = res.getString(3);
                    if (sid.equals(ok)) {
                        flg = true;
                        break;
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(SearchEditDelete.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label lName = new Label("Name :");
            GridPane.setConstraints(lName, 0, 5);
            showName = new TextField(sname);
            GridPane.setConstraints(showName, 4, 5);

            Label lId = new Label("ID :");
            GridPane.setConstraints(lId, 0, 8);
            showId = new TextField(sid);
            showId.setEditable(false);
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
                        layout.getChildren().addAll(lName, showName, lId, showId, lDept, showDept);
                    }
                }
            } else {
                AlertMessage.display("Error", "Id not Matched", "Ok");

            }
        });
        update.setOnAction(e -> {
            if (ConfirmLoginWindow.confirm()) {

                String query = "update info set Name = ?,Dept = ? where ID = ?";
                PreparedStatement preparedStmt;
                try {
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, showName.getText());
                    preparedStmt.setString(2, showDept.getText());
                    preparedStmt.setString(3, showId.getText());
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                    AlertMessage.display("Message", "Successfully Update", "Ok");

                    stage.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                    AlertMessage.display("Message", "SQL Server Connection Error", "Ok");
                }

                //stage.close();
            }
        });
        deleteButton.setOnAction(e -> {
            if (ConfirmLoginWindow.confirm()) {

                String query = "delete from info where id = ?";
                PreparedStatement preparedStmt;

                try {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Do you want to delete?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setString(1, showId.getText());
                        preparedStmt.execute();
                        AlertMessage.display("Message", "Successfully Delete", "Ok");
                        stage.close();
                    } else {

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                    AlertMessage.display("Message", "SQL Server Connection Error", "Ok");
                }

            }
        });
        layout.getChildren().addAll(idSearch, searchButton);
        Scene scene = new Scene(layout, 450, 450);
        stage.setScene(scene);
        stage.showAndWait();
        conn.close();
    }
}
