package application;
	
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;



public class Main extends Application {
	@FXML Button b1;
	@FXML Label lb1;
	@FXML TextField tf1;
	@FXML Canvas cv1;
	GraphicsContext gc;
	
	@FXML
	protected void b1Click(ActionEvent e){
		lb1.setText("clicked the button");
	}
	
	@FXML
	protected void tf1MouseMoved(MouseEvent e){
		lb1.setText("x1:"+e.getX()+" x2:"+e.getSceneX());
	}
	
	@FXML
	protected void cv1MouseMoved(MouseEvent e) {
		String crlf = System.getProperty("line.separator");
		lb1.setText("x: "+e.getX()+crlf+"y: "+e.getY());
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ui.fxml"));
			AnchorPane root = loader.load();
			
//			gc = cv1.getGraphicsContext2D();
//			gc.setFill(Color.WHEAT);
//			gc.fill();
			
//			root.getChildren().add(cv1);
			
			Scene scene = new Scene(root);
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
