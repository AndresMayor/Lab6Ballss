package thread;

import controlador.BolaControlador;
import javafx.scene.shape.Circle;
import model.Ball;

public class Threads extends Thread {

	private Circle circle;
	private Ball ball;
	
	
	
	public Threads(Circle circle, Ball ball) {
		
		this.circle = circle;
		this.ball = ball;
	}

	
	public Circle getCircle() {
		return circle;
	}


	public void setCircle(Circle circle) {
		this.circle = circle;
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public void run() {
		while(!ball.isStop()) {
			
		try {
		
				ball.move();
				circle.setCenterX(ball.getPosX());
				circle.setCenterY(ball.getPosY());
				sleep(ball.getWait());
				
				
			}
		catch(InterruptedException e ) {
			e.printStackTrace();
			
		}
		
		}
	}
	
	
	
	

}