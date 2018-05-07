/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class SubjectChoiceForm extends Application {

    private TableView<TableClass> table;
    private ObservableList<TableClass> data
            = FXCollections.observableArrayList();

    private Button buttonUp, buttonDown, buttonSubmit, buttonCancel;
    private Label labelNotice;
    private static ArrayList<String> subList = new ArrayList<>();
    private String unit, roll1;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRoll1() {
        return roll1;
    }

    public void setRoll1(String roll1) {
        this.roll1 = roll1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        try{
        primaryStage.setTitle("Subject Choice");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(4);

        labelNotice = new Label();
        labelNotice.setText("*Sort this table with the priority of your subject");
        labelNotice.setTextFill(Color.RED);
        GridPane.setConstraints(labelNotice, 3, 4);
        
        table = new TableView<>();
        GridPane.setConstraints(table, 3, 5);
        TableColumn firstNameCol = new TableColumn("Subject");
        firstNameCol.setMinWidth(300);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        
        
        if (unit.equals("A")) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            data.addAll(new TableClass("CSE"), new TableClass("ICT"), new TableClass("Textile"));

        } else if (unit.equals("B")) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            data.addAll(new TableClass("CPS"), new TableClass("FTNS"), new TableClass("BGE"), new TableClass("Pharmecy"), new TableClass("BMB"));

        } else if (unit.equals("C")) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            data.addAll(new TableClass("Chemistry"), new TableClass("Mathematics"), new TableClass("Physics"), new TableClass("Statistics"));

        } else if (unit.equals("D")) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            data.addAll(new TableClass("BBA"), new TableClass("Economics"));

        }
        table.setItems(data);
        buttonUp = new Button("^");
        GridPane.setConstraints(buttonUp, 4, 5);
        buttonDown = new Button("v");
        GridPane.setConstraints(buttonDown, 4, 6);

        ReadOnlyIntegerProperty selectedIndex = table.getSelectionModel().selectedIndexProperty();
        ArrayList<String> columnData = new ArrayList<>();
        buttonUp.disableProperty().bind(selectedIndex.lessThanOrEqualTo(0));
        buttonDown.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            int index = selectedIndex.get();

            return index < 0 || index + 1 >= table.getItems().size();

        }, selectedIndex, table.getItems()));

        buttonUp.setOnAction(evt -> {
            int index = table.getSelectionModel().getSelectedIndex();
            // swap items
            table.getItems().add(index - 1, table.getItems().remove(index));
            // select item at new position
            table.getSelectionModel().clearAndSelect(index - 1);
            columnData.clear();
            for (TableClass item : table.getItems()) {
                columnData.add(firstNameCol.getCellObservableValue(item).getValue().toString());
            }
        });

        buttonDown.setOnAction(evt -> {
            int index = table.getSelectionModel().getSelectedIndex();
            // swap items
            table.getItems().add(index + 1, table.getItems().remove(index));
            // select item at new position
            table.getSelectionModel().clearAndSelect(index + 1);
            columnData.clear();
            for (TableClass item : table.getItems()) {
                columnData.add(firstNameCol.getCellObservableValue(item).getValue().toString());
            }
        });

        buttonSubmit = new Button("Submit");
        GridPane.setConstraints(buttonSubmit, 8, 8);
        buttonSubmit.setOnAction(e -> {
            subList.clear();
            for (int i = 0; i < columnData.size(); i++) {
                subList.add(columnData.get(i));
                //System.out.println(columnData.get(i));
            }

            //AlertMessage.display("Success", "Successfuly Submitted", "OK");
            database.store_SubChoice(subList, roll1);
            
            buttonSubmit.setVisible(false);

        });
        buttonCancel = new Button("Cancel");
        GridPane.setConstraints(buttonCancel, 2, 8);
        buttonCancel.setOnAction(e -> {
            StudentOption student = new StudentOption();
            try {
                student.start(primaryStage);
            } catch (Exception ex) {

            }
        });
        table.getColumns().addAll(firstNameCol);
        grid.getChildren().addAll(buttonUp, buttonDown, buttonSubmit, buttonCancel, labelNotice, table);
        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
