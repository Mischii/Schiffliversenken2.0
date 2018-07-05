/**
 * Game creator
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	17.04.2018
 */

package functions;

import processing.core.PApplet;
import sockets.MySocket;
import threads.Server;
import threads.StartingThreads;
import view.GameView;

public class Game extends PApplet {

	GameControler myGameController;
	GameView myGameView;
	Variables myVar;
	MySocket server;
	MySocket client;
	String toSend;
	boolean serverConnected = false;
	boolean isRunning =false;
	String friendsIP = "192.168.0.104";

    // Processing
    public void settings() {
    	size(1600,900);
		if (surface != null)
			surface.setResizable(true);
    }
    
    public void setup() {
    	myVar = new Variables(width,height);
        myGameController = new GameControler(width,height);
		myGameView = new GameView(this, myGameController.getPlayerFields(1), myGameController.getPlayerFields(2));
    }

    public void draw(){
    	myGameView.setBackgroundLight();
     	myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar, myGameController.activePlayer);
    	if (isRunning) {
			String txt = server.getLine();
			System.out.println(txt+" Test 1");
			if(txt.equals("Action")) {
				System.out.println(txt+" Test 2");
				server.sendLine("Reaction");
			}
    	}
    	if(serverConnected) {
    		client.sendLine("Action");
			String txt = client.getLine();
			if(txt!=null) {
				System.out.println(txt);
			}
    	}
    }
    

    public void mousePressed(){
        if (myGameController.winningPlayer() == false) {
        	myGameController.buttonClicked(this.mouseX,this.mouseY,myVar, myGameView);
        	}
    }
    
    public void keyPressed() {
		switch (key) {
		case 'r': 
			if(myGameController.restartGame == true) {
				restartGame();
			}
			break;
		case 's': 
			server = new MySocket();
			server.myLog("server is running...");
			isRunning = true;
			server.name = "server";
			server.openServer();
				break;
		case 'c':
			client = new MySocket(friendsIP,80);
			client.openServerConnection();
			client.sendHostRequest();
			serverConnected= true;
		}
    }
    
	public void restartGame() {
		setup();
	}
	public void stop() {
		if(isRunning) {
			server.closeSocketConnection();
		}
		if(serverConnected) {
			client.closeSocketConnection();
		}
	}
}
