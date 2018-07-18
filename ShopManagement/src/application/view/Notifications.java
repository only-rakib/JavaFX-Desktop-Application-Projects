package application.view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notifications extends Application{
	ArrayList<StoreForNotificationFile> arrayList = new ArrayList<>();
	private boolean flag = false;
	public void dbNotify()
	{
		DatabaseStore dataBase = new DatabaseStore();
		arrayList = dataBase.storeForNotification();
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.dbNotify();
		primaryStage.setTitle("Notification");
        primaryStage.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(10);
        grid.setVgap(5);
        
        String name;
        int col=2,row=2;
        Button buttonView[]=new Button[arrayList.size()+1];
        Label labelNofitication[]=new Label[arrayList.size()+1];
        for(int i=0;i<arrayList.size();i++)
        {
        	
        	name = arrayList.get(i).getName();
        	labelNofitication[i]=new Label(name);
        	buttonView[i] = new Button("View");
        	GridPane.setConstraints(labelNofitication[i], col, row);
        	GridPane.setConstraints(buttonView[i], col+1, row);
        	grid.getChildren().addAll(labelNofitication[i]);
            grid.getChildren().addAll(buttonView[i]);
            
        	row++;
        }
        for(int i=0;i<arrayList.size();i++)
        {
        	final int ii = i;
        	buttonView[i].setOnAction(e->{
        		this.display(ii);
        		if(flag)
        		{
        			buttonView[ii].setDisable(true);
        		}
        		
        	});
        }
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	public void display(int indx) {
		this.dbNotify();
		Stage window =new Stage() ;
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");
        /*window.setWidth(400);
        window.setHeight(150);*/
        window.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(5);
        grid.setVgap(5);
        
        Label labelName,labelCareOf,labelAddress,labelPhoneNo,labelUserID,
        textName,textCareOf,textAddress,textPhoneNo,textUserID;
        
        labelName = new Label("Name");
        GridPane.setConstraints(labelName, 2, 2);
        textName = new Label(arrayList.get(indx).getName());
        GridPane.setConstraints(textName, 3, 2);
        
        labelCareOf = new Label("Care Of");
        GridPane.setConstraints(labelCareOf, 2, 3);
        textCareOf = new Label(arrayList.get(indx).getCareOf());
        GridPane.setConstraints(textCareOf, 3, 3);
        
        labelAddress = new Label("Address");
        GridPane.setConstraints(labelAddress, 2, 4);
        textAddress = new Label(arrayList.get(indx).getAddress());
        GridPane.setConstraints(textAddress, 3, 4);
        
        labelPhoneNo = new Label("Phone No");
        GridPane.setConstraints(labelPhoneNo, 2, 5);
        textPhoneNo = new Label(arrayList.get(indx).getPhoneNo());
        GridPane.setConstraints(textPhoneNo, 3, 5);
        
        labelUserID = new Label("User ID");
        GridPane.setConstraints(labelUserID, 2, 6);
        textUserID = new Label(arrayList.get(indx).getUserID());
        GridPane.setConstraints(textUserID, 3, 6);
        
        Button buttonAccept,buttonDelete,buttonBack;
        buttonBack = new Button("Back");
        GridPane.setConstraints(buttonBack, 1, 8);
        buttonBack.setOnAction(e->{
        	flag= false;
            window.close();
        });
        buttonAccept = new Button("Accept");
        GridPane.setConstraints(buttonAccept, 2, 8);
        buttonAccept.setOnAction(e->{
        	flag= true;
        	DatabaseStore db = new DatabaseStore();
        	db.insertSalesMan(arrayList.get(indx).getName(),arrayList.get(indx).getCareOf(), arrayList.get(indx).getAddress(), arrayList.get(indx).getDateOfBirth(), arrayList.get(indx).getNIDNo(),arrayList.get(indx).getPhoneNo(),arrayList.get(indx).getUserID(),arrayList.get(indx).getPassword(),arrayList.get(indx).getPhotoPath() );
        	db.delete(arrayList.get(indx).getUserID());
        	//db.createNewTable(arrayList.get(indx).getUserID());
        	AlertMessage.display("Success", "Successfully Add", "OK");
        	window.close();
        });
        buttonDelete = new Button("Delete");
        GridPane.setConstraints(buttonDelete, 3, 8);
        buttonDelete.setOnAction(e->{
        	DatabaseStore db = new DatabaseStore();
        	flag= true;
        	db.delete(arrayList.get(indx).getUserID());
        	AlertMessage.display("Success", "Successfully Delete", "OK");
        	window.close();
        });
        grid.getChildren().addAll(labelName,labelCareOf,labelAddress,labelPhoneNo,labelUserID,textName,textCareOf,textAddress,textPhoneNo,textUserID);
        grid.getChildren().addAll(buttonAccept,buttonBack,buttonDelete);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid,400,400);
        window.setScene(scene);
        window.showAndWait();
    
    }
	
	/*public static void main(String[] args) {
        launch(args);
    }*/
}
