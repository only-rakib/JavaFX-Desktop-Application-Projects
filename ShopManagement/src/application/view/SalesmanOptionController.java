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

public class SalesmanOptionController implements Initializable {
	
	@FXML
	private Button buttonStore,buttonSellHistory,buttonBack;
	@FXML
	private Label labelChangePassword,labelAbout;
	
	@FXML
	private void buttonStoreClick(ActionEvent event) throws IOException{
		Main.storeButtonClick();
	}
	@FXML
	private void changePasswordClick()
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
	
	@FXML
	private void buttonSellHistoryClick(ActionEvent event)
	{
		try {
			Main.SalesManSellAndDetailsHistory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void buttonBackClick(ActionEvent event)
	{
		try {
			Main.loginPage();
		}
		catch (Exception e) {
			AlertMessage.display("Error","Window not found","OK");
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		labelChangePassword.setText("Change Password");
		labelChangePassword.setCursor(Cursor.HAND);
		
		labelAbout.setText("About");
		labelAbout.setCursor(Cursor.HAND);
	}

}
