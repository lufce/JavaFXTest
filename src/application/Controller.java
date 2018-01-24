package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller implements Initializable {
	@FXML Button b1;
	@FXML Label lb1;
	@FXML TextField tf1;
	
	@FXML Canvas cv1;
	double CanvasHeight;
	double CanvasWidth;
	GraphicsContext gc;
	
	boolean[][] dot;
	int dotsize = 4;
	
	double MouseX;
	double MouseY;
	
	
	@FXML
	protected void b1Click(ActionEvent e){
		EraseCanvas();
		RandomMap(dot);
		DrawDotMap(dot);
	}
	
	@FXML
	protected void tf1MouseMoved(MouseEvent e){
		MouseX = e.getX();
		MouseY = e.getY();
		
		lb1.setText("x: "+MouseX+" y: "+MouseY);
	}
	
	@FXML
	protected void cv1MouseMoved(MouseEvent e) {
		String crlf = System.getProperty("line.separator");
		
		MouseX = e.getX();
		MouseY = e.getY();

//		this.DrawMousePoint(MouseX, MouseY);
		lb1.setText("x: "+MouseX+crlf+"y: "+MouseY);
		
	}
	
	void EraseCanvas() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,CanvasWidth,CanvasHeight);
	}
	
	void DrawMousePoint(double x, double y) {
		gc.setFill(Color.BLACK);
		gc.fillRect(x, y, 2, 2);
	}
	
	void RandomMap(boolean[][] map) {
		Random random = new Random();
		
		for(int m = 0; m < map.length; m++) {
			for(int n = 0; n < map[0].length; n++) {
				if(random.nextInt(2) >= 1) {
					map[m][n] = true;
				}else {
					map[m][n] = false;
				}
			}
		}
	}
	
	void DrawDotMap(boolean[][] map) {
		gc.setFill(Color.BLACK);
		
		for(int m = 0; m < map.length; m++) {
			for(int n = 0; n < map[0].length; n++) {
				if(map[m][n]) {
					gc.fillRect(m*dotsize, n*dotsize, dotsize, dotsize);
				}
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle rb) {
		gc = cv1.getGraphicsContext2D();
		
		CanvasHeight = cv1.getHeight();
		CanvasWidth = cv1.getWidth();
		
		EraseCanvas();
		
		dot = new boolean[(int) (cv1.getHeight()/dotsize)][(int) (cv1.getWidth()/dotsize)];
		RandomMap(dot);
		DrawDotMap(dot);
	}
}
