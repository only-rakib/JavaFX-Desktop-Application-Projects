package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AboutController implements Initializable {
	@FXML 
	Button buttonBack;
	
	@FXML
	private void buttonBackClick(ActionEvent event) throws IOException
	{
		Main.adminOption();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
