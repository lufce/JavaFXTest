package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	@FXML Button b1;
	@FXML Label lb1;
	@FXML TextField tf1;
	
	@FXML
	protected void b1Click(ActionEvent e){
		lb1.setText("clicked the button");
	}
	
	@FXML
	protected void tf1MouseMoved(MouseEvent e){
		lb1.setText("x1:"+e.getX()+" x2:"+e.getSceneX());
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ui.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
