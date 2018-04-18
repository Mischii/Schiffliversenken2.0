/**
 * Game creator
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	17.04.2018
 */

package functions;

import processing.core.PApplet;
import view.GameView;

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
