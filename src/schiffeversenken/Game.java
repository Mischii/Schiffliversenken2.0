package schiffeversenken;

import functions.Variables;
import processing.core.PApplet;

public class Game extends PApplet {

	GameControler myGameController;
	GameView myGameView;
	Variables myVar;

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
     	//myVar = new Variables(width,height);
        myGameController.draw();
    	myGameView.show(myVar);
    }

    public void mousePressed(){
        myGameController.buttonClicked(this.mouseX,this.mouseY,myVar, myGameView);
    }
    

}
