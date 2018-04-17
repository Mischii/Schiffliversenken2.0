package view;

import basics.Processing;
import processing.core.PApplet;

public class ColorSetter extends Processing {
	
	
	public ColorSetter(PApplet p) {
		super(p);
	}

	public void updateColor (int zustand) {
		switch(zustand){
			case(0): selectColor(30,125,230);	//Wasser das noch nicht besetzt ist
			break;
			case(1): selectColor(0,0,255);  	//Moeglichkeiten fuer das naechste Feld des Schiffes
			break;
			case(2): selectColor(255,0,0);  	//Wasser das neben einem Schiff liegt
			break;
			case(3): selectColor(112,112,112);  //Ein Teil eines Schiffes
			break;
			case(4): selectColor(30,125,230);  //Wasser das  besetzt ist, damit der Gegner die SChiffe nicht sieht
			break;
			case(5): selectColor(243, 187, 42); //Getroffen,nicht versenkt
			break;
			case(6): selectColor(255,0,0); 		//Versenkt
			break;
			case(7): selectColor(0,0,0); 		//nicht getroffen
			break;
		}
	}
	
}
