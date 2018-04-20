/**
 * control button
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	19.03.2018
 */

package view;
import basics.Processing;
import processing.core.PApplet;

public class Shape extends Processing {

	public Shape(PApplet p) {
		super(p);
	}
	
	/**
	 * button template 
	 */
	public void drawButton(float x, float y, String textButton){
		parent.fill(173,107,117);
		parent.rect(x,y,parent.width*0.1f,parent.height*0.1f);
		writeText ((float)(x+(parent.width*0.1/2)), (float)(parent.height*0.15), textButton, 0,0,0, 15);
	}
	
	public boolean checkHitboxButton(float xMouse,float yMouse,float xButton,float yButton){
		if ((xMouse<= xButton+parent.width*0.1 && xMouse >= xButton) && (yMouse >= yButton && yMouse <= yButton+parent.width*0.1)){
			return true;
		}else{
			return false;
		}
	}
	

}
