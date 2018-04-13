import processing.core.PApplet;

public class GameControler {
	PApplet parent;
	Shape myShape;
	Feld myFeld;
	Background bGround;
	PlayField player1Fields;
	PlayField player2Fields;

	GameControler(PApplet p) {
		parent = p;
		myShape = new Shape(parent);
		myFeld = new Feld(parent);
		bGround = new Background(parent);
		player1Fields = new PlayField(parent);
		player2Fields = new PlayField(parent);
	}

	//Zeigt zwei Spielfelder und zwei Button
	void show() {
		bGround.show(player1Fields, player2Fields);
		myShape.drawButton((float)(parent.width*0.45),(float)(parent.height*0.1),"Gfächt startä");
	}
	//überprüfung des Buttons
	void buttonClicked(){       
		player2Fields.mouseCheck();
		float x = parent.mouseX;
		float y = parent.mouseY;
		//Button für den Gefächtstart überprüfen
		if((player2Fields.isPlacingAShip() == false)&& myShape.checkHitboxButton(x,y,(float)(parent.width*0.45),(float)(parent.height*0.1))){
			parent.background(0,0,0);
			for (int r = 0; r <= 9; r++){
				for (int c = 0;  c<=9;c++){
			        player2Fields.felderArray[c][r].changeColorSetShip();
				}
			}
		}
	}



}
