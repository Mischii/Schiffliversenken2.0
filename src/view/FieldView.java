package view;

import basics.Processing;
import functions.PlayField;
import functions.Variables;
import processing.core.PApplet;

public class FieldView extends Processing {
	
	ColorSetter myColorSetter;
	Variables myVar;
	PlayField myPlayField;

	public FieldView(PApplet p, PlayField pf) {
		super(p);
		myColorSetter = new ColorSetter(p);
		myVar = new Variables(p.width,p.height);
		myPlayField = pf;
	}


	public void drawFields(boolean isRight) {      // Felder zeichnen
		float pos = (float)myVar.lBorder;
		if (isRight == true) pos = (float)myVar.rBorder;
		float iPos = (float) myVar.tBorder;
		for (int r = 0; iPos<=myVar.tBorder + parent.width*0.36; iPos += myVar.fSize, r++){
			float jPos = pos;
			for (int c = 0;  jPos <= pos+parent.width*0.36; jPos += myVar.fSize, c++){
				int z = myPlayField.getFeld(c,r).getZustand();
				myColorSetter.updateColor(z);
				parent.rect(jPos,iPos,(float)(myVar.fSize),(float)(myVar.fSize));
			}
		}
	}
}
