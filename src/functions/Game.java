/**
 * Game creator
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	17.04.2018
 */

package functions;

import online.client;
import online.server;
import processing.core.PApplet;
import sockets.MySocket;
import view.GameView;

public class Game extends PApplet {

	GameControler myGameController;
	GameView myGameView;
	Variables myVar;
	server server;
	client client;
	String toSend;
	String friendsIP = "192.168.0.104";
	boolean isServer = false;
	boolean isClient = false;

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
    	if(isServer) {
        	server.tick();
    	}else if(isClient) {
    		client.tick();	
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
			server = new server();
				break;
		case 'c':
			client = new client(friendsIP, 80);
		}
    }
    
	public void restartGame() {
		setup();
	}
	public void stop() {
			server.closeSocketConnection();
			client.closeSocketConnection();
	}
}
