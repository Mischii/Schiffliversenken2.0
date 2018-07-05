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
    	server = new MySocket("192.168.0.104", 80);
    	client = new MySocket("192.168.0.102", 80);
    }

    public void draw(){
    	myGameView.setBackgroundLight();
     	myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar, myGameController.activePlayer);
    	if(isRunning) {
		
		server.sendLine("Hallo Client");
		String txtS = server.getLine();
		System.out.println(txtS);
		String txtC = client.getLine();
		if (!(txtC == null || txtC =="") ) {
			System.out.println("receiving: "+txtC);
			client.sendLine("Hallo, Server");
		}
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
		case 'o': 
	    	server.myLog("server is running...");
			isRunning = true;
			server.openServer();
			//mySocket.sendHostRequest();
			server.myLog("server sends chars...");
			client.openServerConnection();
	    	client.sendHostRequest();
	    	client.myLog("waiting for chars...");
				break;
		}
    }
    
	public void restartGame() {
		setup();
	}
}
