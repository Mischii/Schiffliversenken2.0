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
import threads.StartingThreads;
import view.GameView;

public class Game extends PApplet {

	GameControler myGameController;
	GameView myGameView;
	Variables myVar;
	StartingThreads myThreads;
	MySocket client;

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
		myThreads = new StartingThreads();
    	client = new MySocket("10.0.0.104", 80);
    	client.openServer();
    	client.openServerConnection();
    }

    public void draw(){
    	myGameView.setBackgroundLight();
     	myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar, myGameController.activePlayer);
    	String txt = client.getLine();
    	if(txt!=null) {
    		System.out.println(txt);
    	}
    }

    public void mousePressed(){
        if (myGameController.winningPlayer() == false) {
        	myGameController.buttonClicked(this.mouseX,this.mouseY,myVar, myGameView);
        	}
        client.sendLine("MousePressed");
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
