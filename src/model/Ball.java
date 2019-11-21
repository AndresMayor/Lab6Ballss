package model;


public class Ball {

	private int radius;
	private int posX;
	private int posY;
	private int wait;
	private String direction;
	private int numberOfBounces;
	private boolean stop;


	public Ball(int radius, int posX, int posY, int wait, String direction, int numberOfBounces, boolean stop) {
		
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.direction = direction;
		this.numberOfBounces = numberOfBounces;
		this.stop = stop;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	public int getWait() {
		return wait;
	}


	public void setWait(int wait) {
		this.wait = wait;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public int getNumberOfBounces() {
		return numberOfBounces;
	}


	public void setNumberOfBounces(int numberOfBounces) {
		this.numberOfBounces = numberOfBounces;
	}


	public boolean isStop() {
		return stop;
	}


	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
	public void move() {
		
		if (direction.equals("DERECHA")) {
			posX++;
			if (posX+radius==400) {
				direction ="IZQUIERDA";	
				numberOfBounces++;
			}		
		}else if (direction.equals("IZQUIERDA")) {
			posX--;
			if (posX-radius==0) {
				direction ="DERECHA";
				numberOfBounces++;
				
			}
		}else if (direction.equals("ABAJO")) {
			posY++;
			if (posY+radius==500) {
				direction ="ARRIBA";
				numberOfBounces++;
			}	
		}else {
			posY--;
			if (posY-radius==30) {
				direction ="ABAJO";
				numberOfBounces++;
			}
		}

	}
	

	

}
