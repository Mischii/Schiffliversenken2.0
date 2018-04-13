package schiffeversenken;
import processing.core.PApplet;

public class Background {
	PApplet parent;
	Variables myVar;

	Background(PApplet p) {
	    parent = p;
		myVar = new Variables(parent);
	}
	
	void show(PlayField f1, PlayField f2) {
		parent.fill(30,125,230);
		parent.rect((float)(myVar.lBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));  // Spielfeld
		parent.rect((float)(myVar.rBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));
		f1.drawFields((float)(myVar.lBorder));                                 //Felder
		f2.drawFields((float)(myVar.rBorder));                                 //Felder  

	}
}
