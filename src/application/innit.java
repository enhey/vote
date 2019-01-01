package application;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class innit extends Application {
	private Stage mainstage;
	Basic base;
	@Override	
	public void start(Stage primaryStage) throws Exception {
			mainstage=primaryStage;
			mainstage.setResizable(false);//窗口大小可变			
//			mainstage.getIcons().add(new Image("/images/juflogo.jpg"));
			mainstage.setTitle("大学生投票系统");			
			mainstage.setWidth(600);
			mainstage.setHeight(400);
			homeui();			
			primaryStage.show();			

}
	public void homeui() {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("home.fxml"));
			Parent root=loader.load();
			homeController controller=loader.getController();
			controller.setapp(this);
			Scene scene=new Scene(root,600,400);						
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainstage.setScene(scene);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void toStu(Basic base) {		
		try {
			this.base=base;
			stuController controller = new stuController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("StuUi.fxml"));
			loader.setController(controller);
			//readuiController controller=loader.getController();
			//FXMLLoader loader = new FXMLLoader();
			//loader.setLocation(getClass().getResource("readui.fxml"));
			controller.setapp(this);
			Parent root=loader.load();
			
			
			Scene scene=new Scene(root,1200,800);			
			mainstage.setScene(scene);
			mainstage.show();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void toAdmin(Basic base)
	{
		try {			
			this.base=base;
			adminController controller = new adminController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("adminUi.fxml"));
			loader.setController(controller);
			controller.setapp(this);
			Parent root=loader.load();
			Scene scene=new Scene(root,1200,800);			
			mainstage.setScene(scene);
			mainstage.show();	
		} catch (IOException e) {
			System.out.println("add work ui is wrong");
			// TODO: handle exception
		}
	}
	
	public Basic getbase() {
		return base;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
