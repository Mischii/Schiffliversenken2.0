import processing.core.PApplet;

public class Feld {
	PApplet parent;
	Feld(PApplet p) {
		parent = p;}


	int myZustand = 0;

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
		}
	}

	void setZustand (int newZ) {
		myZustand = newZ;
	}

	void changeColorSetShip(){
		if(myZustand == 2) {
			myZustand = 0;
		}
	}

}
