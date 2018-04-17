package view;
import basics.Processing;
import functions.Variables;
import processing.core.PApplet;

public class Background extends Processing {

	Variables myVar;

	public Background(PApplet p) {
	    super(p);
		myVar = new Variables(p.width,p.height);
	}
	
	public void show(FieldView f1, FieldView f2) {
		
		selectColor(30,125,230);
		
		parent.rect((float)(myVar.lBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));  // Spielfeld
		parent.rect((float)(myVar.rBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));
		
		f1.drawFields(false);                                 //Felder
		f2.drawFields(true);                                 //Felder  

	}
}
