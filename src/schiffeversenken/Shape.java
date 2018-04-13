package schiffeversenken;
import processing.core.PApplet;
import processing.core.PConstants;

public class Shape {
	PApplet parent;
	Shape(PApplet p) {
		parent = p;}


	void drawButton(float x, float y, String textButton){
		parent.fill(80,5,5);
		parent.rect(x,y,parent.width*0.1f,parent.height*0.1f);
		parent.fill(255,255,255);
		parent.textAlign(PConstants.CENTER);
		parent.textSize(15);
		parent.text(textButton,(float)(x+(parent.width*0.1/2)),(float)(parent.height*0.15));
	}
	boolean checkHitboxButton(float xMouse,float yMouse,float xButton,float yButton){
		if ((xMouse<= xButton+parent.width*0.1 && xMouse >= xButton) && (yMouse >= yButton && yMouse <= yButton+parent.width*0.1)){
			return true;
		}else{
			return false;
		}
	}



}
