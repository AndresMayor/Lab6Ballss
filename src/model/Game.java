package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Game {

	public static final String GAME = "Game.txt";
	public static final String Level0 = "Level0.txt";
	public static final String Level1 = "level1.txt";
	public static final String Level2 = "Level2.txt";
	
	private int level;
	private ArrayList<Player>  players0; 
	private ArrayList<Player>  players1; 
	private ArrayList<Player>  players2; 
	private ArrayList<Ball> ball;
	private String fileBall;
	
	
	public Game(String fileBall)  {
		ball = new ArrayList<Ball>();
		players0 = new ArrayList<Player>();
		players1 = new ArrayList<Player>();
		players2 = new ArrayList<Player>();
		this.fileBall=fileBall;
	
		try {
			loadGame(fileBall);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}


	}
	

	public ArrayList<Player> getPlayers0() {
		return players0;
	}


	public void setPlayers0(ArrayList<Player> players0) {
		this.players0 = players0;
	}


	public ArrayList<Player> getPlayers1() {
		return players1;
	}


	public void setPlayers1(ArrayList<Player> players1) {
		this.players1 = players1;
	}


	public ArrayList<Player> getPlayers2() {
		return players2;
	}


	public void setPlayers2(ArrayList<Player> players2) {
		this.players2 = players2;
	}


	public ArrayList<Ball> getBall() {
		return ball;
	}


	public void setBall(ArrayList<Ball> ball) {
		this.ball = ball;
	}
	
	
	public void loadGame(String fileBall) throws IOException {
		if (fileBall.contentEquals("Game.txt")) {
		File f = new File(GAME);
		BufferedReader bw = new BufferedReader(new FileReader(f));
		String line = "";
		while ((line = bw.readLine())!=null) {
			if (line.charAt(0) != '#' ) {
				String[] s = line.split("\t");
				if (s.length == 1) {
					level = Integer.parseInt(s[0]);
					
				}else {
					Ball bal = new Ball(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),s[4],Integer.parseInt(s[5]),Boolean.parseBoolean(s[6]));
					ball.add(bal);
				}
			}
		}
		bw.close();
		
	}
	}

	public void addPlayerToHall(Player p) throws FileNotFoundException, IOException {
		boolean added = false;
		if(level == 0) {
			if(players0.isEmpty()) {
				players0.add(p);
				added = true;
			}
			else {
				for(int i = 0; i < players0.size() && !added; i++) {
					if(players0.get(i).compareTo(p) > 0) {
						players0.add(i, p);
						if(players0.size() == 11) {
							players0.remove(10);
						}
						added = true;
					}
				}
				if(!added) {
					if(players0.size() < 10) {
						players0.add(p);
						added = true;
					}
				}
			}
			writePlayers0();
		}
		if(level == 1) {
			if(players1.isEmpty()) {
				players1.add(p);
				added = true;
			}
			else {
				for(int i = 0; i < players1.size() && !added; i++) {
					if(players1.get(i).compareTo(p) > 0) {
						players1.add(i, p);
						if(players1.size() == 11) {
							players1.remove(10);
						}
						added = true;
					}
				}
				if(!added) {
					if(players1.size() < 10) {
						players1.add(p);
						added = true;
					}
				}
			}
			writePlayers1();
		}
		if(level == 2) {
			if(players2.isEmpty()) {
				players2.add(p);
				added = true;
			}
			else {
				for(int i = 0; i < players2.size() && !added; i++) {
					if(players2.get(i).compareTo(p) > 0) {
						players2.add(i, p);
						if(players2.size() == 11) {
							players2.remove(10);
						}
						added = true;
					}
				}
				if(!added) {
					if(players1.size() < 10) {
						players1.add(p);
						added = true;
					}
				}
			}
			writePlayers2();
		}
	}
	
	
	
	
	
	public boolean canJoinToTheHall(int score) {
		boolean join = false;
		if(level == 0) {
			if(players0.isEmpty()) {
				join = true;
			}
			else {
				for(int i = 0; i < players0.size(); i++) {
					if(players0.get(i).getScore() > score) {
						join = true;
					}
				}
				if(!join) {
					if(players0.size() != 10) {
						join = true;
					}
				}
			}
		}
		if(level == 1) {
			if(players1.isEmpty()) {
				join = true;
			}
			else {
				for(int i = 0; i < players1.size(); i++) {
					if(players1.get(i).getScore() > score) {
						join = true;
					}
				}
				if(!join) {
					if(players1.size() != 10) {
						join = true;
					}
				}
			}
		}
		if(level == 2) {
			if(players2.isEmpty()) {
				join = true;
			}
			else {
				for(int i = 0; i < players2.size(); i++) {
					if(players2.get(i).getScore() > score) {
						join = true;
					}
				}
				if(!join) {
					if(players2.size() != 10) {
						join = true;
					}
				}
			}
		}
		return join;
	}
	
	public void  writePlayers0() throws FileNotFoundException, IOException {
		File f =new File (Level0);
		ObjectOutputStream n = new 	ObjectOutputStream(new FileOutputStream(f));
		n.writeObject(players0);
		n.close();
		
	}

	public void  writePlayers1() throws FileNotFoundException, IOException {
		File f =new File (Level1);
		ObjectOutputStream n = new 	ObjectOutputStream(new FileOutputStream(f));
		n.writeObject(players1);
		n.close();
		
	}

	public void  writePlayers2() throws FileNotFoundException, IOException {
		File f =new File (Level2);
		ObjectOutputStream n = new 	ObjectOutputStream(new FileOutputStream(f));
		n.writeObject(players2);
		n.close();
		
	}
	
	public void load0() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(Level0);
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		players0 = (ArrayList<Player>)obj.readObject();
		obj.close();
	}
	public void load1() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(Level1);
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		players1 = (ArrayList<Player>)obj.readObject();
		obj.close();
	}
	public void load2() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(Level2);
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		players2 = (ArrayList<Player>)obj.readObject();
		obj.close();
	}
	
	
	
	public String theBest() {
		String playe = "";
		if (level == 0) {
		for (int i = 0; i < players0.size(); i++) {
			playe += players0.get(i).toString()+"\n";
		}
		}else if(level == 1) {
			for (int i = 0; i < players1.size(); i++) {
				playe += players1.get(i).toString()+"\n";
			}
		}else if (level ==2) {
			for (int i = 0; i < players2.size(); i++) {
				playe += players2.get(i).toString()+"\n";
			}
			
		}
		return playe;
	}
	
	public void saveGame() throws IOException {
		String s ="#nivel"+"\n";
		s += "radio posX posY espera direccion rebotes"+"\n";
		for(int i =0;i<ball.size();i++) {
			s+=ball.get(i).toString()+"\n";
		}
		File f = new File(GAME);
		BufferedWriter bw = new BufferedWriter (new FileWriter(f));
		bw.write(s);
		bw.close();
		
		
	}
	
}