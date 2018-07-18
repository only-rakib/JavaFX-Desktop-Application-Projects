package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.stage = primaryStage;
			Main.stage.setTitle("Login");
			Main.loginPage();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void printPage() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/PrintDemo.fxml"));
		Main.stage.setTitle("Print and Preview");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void loginPage() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/LoginScene.fxml"));
		Main.stage.setTitle("Login");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void adminOption() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/AdminOption.fxml"));
        Main.stage.setTitle("Admin Option");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void salesmanOption() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/SalesmanOption.fxml"));
        Main.stage.setTitle("Salesman Option");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void createNewAccount() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CreateNewACC.fxml"));
        Main.stage.setTitle("New Account");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void storeButtonClick() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/StorePage.fxml"));
        Main.stage.setTitle("Equipments");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void changePasswordClick() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ChangePasswordAdmin.fxml"));
        Main.stage.setTitle("Change Password");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void SalesManSellAndDetailsHistory() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ShowSalesManHistory.fxml"));
        Main.stage.setTitle("Record");
		Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public static void labelAbout()
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/About.fxml"));
        Main.stage.setTitle("About");
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
