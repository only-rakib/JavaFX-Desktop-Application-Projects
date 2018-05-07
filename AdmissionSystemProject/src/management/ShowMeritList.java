/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rakib
 */
public class ShowMeritList extends Application {
    private TableView<BindingsWithMeritTable> tableMeritList;
    private ObservableList<BindingsWithMeritTable> tableMeritListData= FXCollections.observableArrayList();
    private Label labelUnit;
    private Button buttonBack;
    private ComboBox comboBoxUnit;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Subject Choice");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(4);
        
        labelUnit = new Label("Select Unit");
        GridPane.setConstraints(labelUnit, 2, 2);
        comboBoxUnit = new ComboBox<>();
        comboBoxUnit.setValue("Unit");
        comboBoxUnit.getItems().addAll("A","B","C","D");
        GridPane.setConstraints(comboBoxUnit, 3, 2);
        
        tableMeritList = new TableView<>();
        GridPane.setConstraints(tableMeritList, 5, 5);
        TableColumn firstNameCol = new TableColumn("Merit Position");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("MeritPos"));
        
        TableColumn secondNameCol = new TableColumn("Roll");
        secondNameCol.setMinWidth(150);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("roll"));
        
        TableColumn thirdNameCol = new TableColumn("Name");
        thirdNameCol.setMinWidth(150);
        thirdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn fourthNameCol = new TableColumn("Total Number");
        fourthNameCol.setMinWidth(150);
        fourthNameCol.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));
        
        comboBoxUnit.setOnAction(e->{
            
            if(comboBoxUnit.getValue().equals("Unit"))
            {
                AlertMessage.display("Error","Select Unit" , "Ok");
                
            }
            else
            {
                //System.out.println("Paisi");
                ArrayList<BindingsWithMeritTable>arL =  new ArrayList<>();
                arL = database.merit_table(comboBoxUnit.getValue().toString());
                tableMeritList.getItems().clear();
                for(int i =0;i<arL.size();i++)
                {
                    String m= arL.get(i).getTotalMarks();
                    int x= arL.get(i).getMeritPos();
                    tableMeritListData.addAll(new BindingsWithMeritTable(arL.get(i).getName(), arL.get(i).getRoll(),m,x));
                }
            }
        });
        
        
        tableMeritList.setItems(tableMeritListData);
        tableMeritList.getColumns().addAll(firstNameCol,secondNameCol,thirdNameCol,fourthNameCol);
        
        buttonBack = new Button("Back");
        GridPane.setConstraints(buttonBack, 2, 6);
        buttonBack.setOnAction(e->{
            AdminOption admin = new AdminOption();
            try{
                admin.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        
        grid.getChildren().addAll(tableMeritList,labelUnit,comboBoxUnit,buttonBack);
        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
