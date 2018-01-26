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
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

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
	double scale = 1.0;
	double scale_step = 1.1;
	
	double MouseX;
	double MouseY;
	double MousePreX;
	double MousePreY;
	double MouseDeltaX;
	double MouseDeltaY;
	double Max=0;
	
	Affine aff = new Affine();
	private static final Affine IDENTITY_TRANSFORM = new Affine(1f,0f,0f,0f,1f,0);
	
	@FXML
	protected void b1Click(ActionEvent e){
		EraseCanvas();
//		RandomMap(dot);
//		DrawDotMap(dot);
		
		aff.appendTranslation(10, 10);
		gc.setTransform(aff);
		this.DrawDotMap(dot);
	}
	
	@FXML
	protected void tf1MouseMoved(MouseEvent e){
		MouseX = e.getX();
		MouseY = e.getY();
		
		lb1.setText("x: "+MouseX+" y: "+MouseY);
	}
	
	@FXML
	protected void cv1MouseEntered(MouseEvent e) {
//		MousePreX = e.getX();
//		MousePreY = e.getY();
	}
	
	@FXML
	protected void cv1MouseMoved(MouseEvent e) {
//		String crlf = System.getProperty("line.separator");
	}
	
	@FXML
	protected void cv1MousePressed(MouseEvent e) {
		tf1.setText("click");
		MousePreX = e.getX();
		MousePreY = e.getY();
	}
	
	@FXML
	protected void cv1MouseDragged(MouseEvent e) {
		tf1.setText("drag");
		
		MouseX = e.getX();
		MouseY = e.getY();
		
		MouseDeltaX = MouseX-MousePreX;
		MouseDeltaY = MouseY-MousePreY;
		
		MousePreX = MouseX;
		MousePreY = MouseY;
		
		this.EraseCanvas();
		aff.appendTranslation(MouseDeltaX,MouseDeltaY);
		gc.setTransform(aff);
		this.DrawDotMap(dot);
	}
	
	void EraseCanvas() {
		gc.setFill(Color.WHITE);
		gc.setTransform(IDENTITY_TRANSFORM);
		gc.fillRect(0, 0, CanvasWidth, CanvasHeight);
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
		double scaled_dotsize = scale*dotsize;
		
		gc.setFill(Color.BLACK);
		
		for(int m = 0; m < map.length; m++) {
			for(int n = 0; n < map[0].length; n++) {
				if(map[m][n]) {
					gc.fillRect(m*scaled_dotsize, n*scaled_dotsize, scaled_dotsize, scaled_dotsize);
				}
			}
		}
	}
	
	@FXML
	protected void cv1Scroll(ScrollEvent e) {
		
		this.EraseCanvas();
		gc.setTransform(aff);
		scale = e.getDeltaY() >=0 ? scale * scale_step : scale / scale_step;
		
		this.DrawDotMap(dot);
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
