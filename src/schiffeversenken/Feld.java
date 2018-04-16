package schiffeversenken;
import processing.core.PApplet;

public class Feld {
	PApplet parent;
	Feld(PApplet p) {
		parent = p;}


	int myZustand = 0;
	int myShipSize = 0;

	void setColor () {
		switch(myZustand){
		case(0): parent.fill(30,125,230);  //Wasser das noch nicht besetzt ist
		break;
		case(1): parent.fill(0,0,255);  //Moeglichkeiten fuer das naechste Feld des Schiffes
		break;
		case(2): parent.fill(255,0,0);  //Wasser das neben einem Schiff liegt
		break;
		case(3): parent.fill(112,112,112);  //Ein Teil eines Schiffes
		break;
		case(4): parent.fill(255,255,255);  //Wasser das  besetzt ist, damit der Gegner die SChiffe nicht sieht
		break;
		case(5): parent.fill(243, 187, 42); //Getroffen,nicht versenkt
		break;
		case(6): parent.fill(255,0,0); //Versenkt
		break;
		case(7): parent.fill(0,0,0); 	//nicht getroffen
		break;
		}
	}

	void setZustand (int newZ) {
		myZustand = newZ;
	}
	
	void setShipSize (int newS) {
		myShipSize = newS;
	}

	void changeColorSetShip(){
		if(myZustand == 2 || myZustand == 1) {
			myZustand = 0;
		}
		if(myZustand == 3) {
			myZustand = 4;
		}
		
	}
	void checkShooted() {
		if(myZustand < 4) {
			myZustand = 7;
		}
		if(myZustand == 4) {
			myZustand = 5;
			
		}
	}

}
