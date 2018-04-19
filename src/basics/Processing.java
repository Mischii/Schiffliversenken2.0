/**
* extends Processing  
* 
* @author	Lenny Johner, Michèle Habegger
* @version	1.0
* @since	17.04.2018
*/

package basics;

import processing.core.PApplet;
import processing.core.PConstants;

public class Processing {

	public PApplet parent;

	public Processing (PApplet p) {
		parent = p;
	}
	
	protected void selectColor (int r, int g, int b) {	
		parent.fill(r,g,b);
	}
	
	/**
	 * text output for button or players
	 */
	public void writeText(float x, float y, String textButton,int r,int g,int b, int size){
		parent.fill(r,g,b);
		parent.textAlign(PConstants.CENTER);
		parent.textSize(size);
		parent.text(textButton,x,y);
	}
    
    public void setBackgroundLight() {
		parent.background(200,200,200);
    }
    
    public int getMouseX() {
    	return parent.mouseX;
    }

    public int getMouseY() {
    	return parent.mouseY;
    }
}
