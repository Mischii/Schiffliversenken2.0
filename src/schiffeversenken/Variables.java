package schiffeversenken;
import processing.core.PApplet;

public class Variables {
	PApplet parent;
	
	double lBorder;
	double rBorder;
	double tBorder;
	double bBorder;
	double pFSize;
	double fSize;

	Variables(PApplet p) {
		parent = p;
		lBorder = parent.width*0.05;
		rBorder = parent.width*0.55;
		tBorder = parent.height-parent.height*0.8;
		pFSize = parent.width*0.4;
		fSize = parent.width*0.04;
	}



}
