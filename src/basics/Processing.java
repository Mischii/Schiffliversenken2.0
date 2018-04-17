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
	
	protected void showPlayer(float x, float y, String textButton){
		parent.fill(255,255,255);
		parent.textAlign(PConstants.CENTER);
		parent.textSize(15);
		parent.text(textButton,(float)(x+(parent.width*0.1/2)),(float)(parent.height*0.15));
	}

    public void setBackgroundDark() {
		parent.background(0,0,0);
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
