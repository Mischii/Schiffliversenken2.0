package view;
import basics.Processing;
import functions.Variables;
import processing.core.PApplet;

public class Background extends Processing {

	public Background(PApplet p) {
	    super(p);
	}
	
	public void show(FieldView f1, FieldView f2, Variables myVar) {
		
		selectColor(30,125,230);
		
		parent.rect((float)(myVar.lBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));  // Spielfeld
		parent.rect((float)(myVar.rBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));
		
		f1.drawFields(false, myVar);                                 //Felder
		f2.drawFields(true, myVar);                                 //Felder  

	}
}
