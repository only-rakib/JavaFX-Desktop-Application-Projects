
package management;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainMenu extends Application {
    private Button buttonStudentForm,buttonAdminForm; 
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(5);
        grid.setVgap(4);
        
        buttonStudentForm = new Button("Student Page");
        GridPane.setConstraints(buttonStudentForm,5, 8);
        buttonAdminForm = new Button("Admin Page");
        GridPane.setConstraints(buttonAdminForm, 5, 10);
        
        buttonStudentForm.setOnAction(e->{
            StudentOption st = new StudentOption();
            try {
                st.start(primaryStage);
            } catch (Exception ex) {
                AlertMessage.display("Error", "Window Not Found", "Ok");
            }
        });
        
        buttonAdminForm.setOnAction(e->{
            LoginPageForAdmin login =new LoginPageForAdmin();
            try{
                login.start(primaryStage);
            }
            catch(Exception ex)
            {
                
            }
        });
        
        grid.getChildren().addAll(buttonAdminForm,buttonStudentForm);
        
        Scene scene = new Scene(grid,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
