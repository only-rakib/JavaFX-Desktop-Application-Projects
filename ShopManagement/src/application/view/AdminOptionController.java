package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminOptionController implements Initializable{

	private Stage primaryStage = new Stage() ;
	@FXML
	private Button buttonStore,buttonEmployee,buttonDaily,buttonBack,buttonChangePassword;
	@FXML
	private Label labelNotifications,labelAbout;
	
	@FXML
	public void labelNotificationClick()
	{
		Notifications notifications = new Notifications();
		try
		{
			
			//Stage primaryStage = null;
			notifications.start(primaryStage);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	@FXML
	public void buttonStoreClick(ActionEvent event) throws IOException
	{
		Main.storeButtonClick();
	}
	@FXML
	public void buttonEmployeeClick(ActionEvent event)
	{
		try {
			Main.SalesManSellAndDetailsHistory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void buttonDailyClick(ActionEvent event)
	{
		
	}
	@FXML
	public void buttonBackClick(ActionEvent event) throws IOException
	{
		Main.loginPage();
	}
	@FXML
	public void buttonChangePasswordClick(ActionEvent event)
	{
		try {
			Main.changePasswordClick();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void labelAboutClick()
	{
		Main.labelAbout();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		labelNotifications.setText("Notification");
		labelNotifications.setCursor(Cursor.HAND);
		
		labelAbout.setText("About");
		labelAbout.setCursor(Cursor.HAND);
		DatabaseStore db = new DatabaseStore();
		if(db.fileEmpty())
		{
			labelNotifications.setText("Notification(new)");
		}
		else
		{
			labelNotifications.setDisable(true);
		}
	}

}
