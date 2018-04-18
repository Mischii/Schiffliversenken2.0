/**
 * show the game view
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	19.03.2018
*/

package view;
import basics.Processing;
import functions.Variables;
import processing.core.PApplet;

public class Background extends Processing {

	public Background(PApplet p) {
	    super(p);
	}
	
	/**
	 * show both playfilds 
	 * @param f1
	 * @param f2
	 * @param myVar
	 */
	public void show(FieldView f1, FieldView f2, Variables myVar) {
		
		selectColor(30,125,230);
		
		parent.rect((float)(myVar.lBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));  // Spielfeld
		parent.rect((float)(myVar.rBorder),(float)(myVar.tBorder),(float)(myVar.pFSize),(float)(myVar.pFSize));
		
		f1.drawFields(false, myVar);                                 //Felder
		f2.drawFields(true, myVar);                                 //Felder  

	}
}

