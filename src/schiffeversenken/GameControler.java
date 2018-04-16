package schiffeversenken;
import processing.core.PApplet;

public class GameControler {
	PApplet parent;
	Shape myShape;
	Feld myFeld;
	Background bGround;
	Variables myVar;
	PlayField player1Fields;
	PlayField player2Fields;

	GameControler(PApplet p) {
		parent = p;
		myShape = new Shape(parent);
		myFeld = new Feld(parent);
		bGround = new Background(parent);
		myVar = new Variables(parent);
		player1Fields = new PlayField(parent);
		player2Fields = new PlayField(parent);
		player2Fields.setSchiffli = true;
	}

	//Zeigt zwei Spielfelder und zwei Button
	void show() {
		bGround.show(player1Fields, player2Fields);
		myShape.drawButton((float)(parent.width*0.45),(float)(parent.height*0.1),"Spiler wächsle!");
	}
	//überprüfung des Buttons!!!
	void buttonClicked(){  
		float x = parent.mouseX;
		float y = parent.mouseY;
		int column = 11;
		int row = 11;
		if(((y-(myVar.tBorder))/(myVar.fSize))>=0) {
			row = (int)((y-(myVar.tBorder))/(myVar.fSize));
		}
		if(player2Fields.setSchiffli == true || player1Fields.itsTurn == true) {
			if(((x-(myVar.rBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.rBorder))/(myVar.fSize));
			}
		}else if(player1Fields.setSchiffli == true || player2Fields.itsTurn == true){
			if(((x-(myVar.lBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.lBorder))/(myVar.fSize));
			}
		}
		if ( (column < 10  && column+1 > 0) && 
		   (row    < 10 && row+1 > 0)){			//Klicken ist inerhalb des Spielfeldes
		     if (player2Fields.setSchiffli == true) {
			     player2Fields.tryToSetetShip(column, row);
			 }else {
				 player1Fields.tryToSetetShip(column, row);
			 }
		}
		//Button für den Gefächtstart überprüfen
		if((player2Fields.isPlacingAShip() == false)&& myShape.checkHitboxButton(x,y,(float)(parent.width*0.45),(float)(parent.height*0.1))){
			parent.background(0,0,0);
			for (int r = 0; r <= 9; r++){
				for (int c = 0;  c<=9;c++){
					player2Fields.felderArray[c][r].changeColorSetShip();
					player2Fields.setSchiffli = false;
					player1Fields.setSchiffli = true;
				}
			}
		}
		if((player1Fields.isPlacingAShip() == false)&& myShape.checkHitboxButton(x,y,(float)(parent.width*0.45),(float)(parent.height*0.1))){
			parent.background(200,200,200);
			for (int r = 0; r <= 9; r++){
				for (int c = 0;  c<=9;c++){
					player1Fields.felderArray[c][r].changeColorSetShip();
					player1Fields.setSchiffli = false;
					player2Fields.itsTurn = true;
				}
			}
		}
		//Wechselt zwischen den Spielern bei Jedem Schuss ab
		if(player1Fields.setSchiffli==false && player2Fields.setSchiffli==false) {
			if ((column < 10  && column+1 > 0) && 
					(row    < 10 && row+1 > 0)){			//Klicken ist inerhalb des Spielfeldes
				if(player2Fields.itsTurn == true) {
					player1Fields.schiessen(column, row);
					player2Fields.itsTurn = false;
					player1Fields.itsTurn = true;
				}else {
					player2Fields.schiessen(column, row);
					player1Fields.itsTurn = false;
					player2Fields.itsTurn = true;
				}
				
			}
		}
	}
}
