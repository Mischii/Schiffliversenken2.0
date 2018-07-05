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
	String toSend;

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
    	server = new MySocket("10.0.0.104", 80);
    	server.openServerConnection();
    	server.sendHostRequest();
    }

    public void draw(){
    	myGameView.setBackgroundLight();
     	myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar, myGameController.activePlayer);
    	if(toSend != null) {
    		server.sendLine(toSend);
    		toSend = null;
    	}
    	try {
        	String txt = server.getLine();
        	System.out.println(txt);
    	}catch(Exception e){
    		
    	}
    }
    

    public void mousePressed(){
        if (myGameController.winningPlayer() == false) {
        	myGameController.buttonClicked(this.mouseX,this.mouseY,myVar, myGameView);
        	}
        toSend = "MousePressed";
    }
    
    public void keyPressed() {
		switch (key) {
		case 'r': 
			if(myGameController.restartGame == true) {
				restartGame();
			}
			break;
		}
    }
    
	public void restartGame() {
		setup();
	}
}
