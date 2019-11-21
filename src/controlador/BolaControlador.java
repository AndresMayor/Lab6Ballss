package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import thread.Threads;

public class BolaControlador {
	
	
	@FXML private AnchorPane anchor;
	private Game game;
	private int score;

	public void mover() {
		
		for (int i = 0;i<game.getBall().size();i++) {
			
			Circle circle = new Circle(game.getBall().get(i).getRadius());
			circle.setCenterX(game.getBall().get(i).getPosX());
			circle.setFill(Color.AQUAMARINE);
			anchor.getChildren().add(circle);
			Threads hilito = new Threads(circle,game.getBall().get(i));
			hilito.start();
			
			
		}

	}
	
	public void clicBall(MouseEvent e ) {
	
		for (int i = 0;i<game.getBall().size();i++) {
			double x = e.getSceneX()-(game.getBall().get(i).getPosX());
				x*=x;
			double y = e.getSceneY()-(game.getBall().get(i).getPosY());
				y*=y;
			double suma = x+y;
			
			
			double radio = game.getBall().get(i).getRadius();
			  radio *=radio;
			  
			  if (suma <= radio) {
			
				game.getBall().get(i).setStop(true);
				
			}
		}
		boolean n = true;
		for (int i = 0; i < game.getBall().size(); i++) {
		if	(game.getBall().get(i).isStop()==false) {
			n =false;
		}
		
		
		}
	}
	

	
	
	public void verMejores() {
		
		Stage n = new Stage();
		AnchorPane ap = new AnchorPane();
		Label label = new Label (game.theBest());
		ap.getChildren().add(label);
		Scene scene = new Scene(ap , 300,300);
		n.setScene(scene);
		n.show();
		
	}
	
	
	public void loadGame(ActionEvent ee) {
		Stage n = new Stage();
		BorderPane ap = new BorderPane();
	
		TextField texF = new TextField();
		
		HBox hb = new HBox();
		Label lb =new Label();
		Button bt = new Button("Load");
		
		hb.getChildren().add(bt);
		ap.setTop(texF);
		ap.setCenter(hb);
		
		Scene scene = new Scene(ap , 300,300);
		n.setScene(scene);
		n.show();
		bt.setOnAction(e-> {
			
			 game = new Game(texF.getText());
			 mover();
			 if (texF.getText().contentEquals("Game.txt")) {
				 lb.setText("The File has loaded successfully");
				 ap.setBottom(lb);
				 Stage s = (Stage)ap.getScene().getWindow();
				 s.close();
			 }else {
				 lb.setText("The Archive: "+texF.getText()+".does not exist.  (Game.txt)");
				 ap.setBottom(lb);
			 }
		
			 
		});
		
	}
	
	public void exit(ActionEvent e) {
		Stage s = (Stage)anchor.getScene().getWindow();
		s.close();
	}
	
	public void aboutGame(ActionEvent e) {
		
		Stage s = new Stage();
		AnchorPane anchor = new AnchorPane();
		String msg = "El juego consta de unas esferas Moviendose Vertical y Horizontalmente "+"\n"+"El Ganador sera el que para todas las esferas"+"\n"+"Haciendole clic sobre la esfera y tengas menos cantidad de rebotes";
		Label label = new Label(msg);
		anchor.getChildren().add(label);
		Scene scene = new Scene(anchor, 300,300);
		s.setScene(scene);
		s.setTitle("About The Game");
		s.show();
	}
	
	/*public void guardarJugador() {
		Stage s = new Stage();
		TextField texF = new TextField();
		BorderPane bo = new BorderPane();
		bo.setCenter(bo);
		Player player = new Player(texF.getText(),score);
		
		try {
			game.addPlayerToHall(player);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scene scene = new Scene(bo, 300,300);
		s.setScene(scene);
		s.show();
	}*/
	
	
	
	
	
	
	/*public void initialize() {
		game = new Game();
		mover();
	}*/
}
