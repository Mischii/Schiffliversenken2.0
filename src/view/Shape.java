package view;
import basics.Processing;
import processing.core.PApplet;

public class Shape extends Processing {

	public Shape(PApplet p) {
		super(p);
	}
	
	public void drawButton(float x, float y, String textButton){
		parent.fill(80,5,5);
		parent.rect(x,y,parent.width*0.1f,parent.height*0.1f);
		showPlayer (x, y, textButton);
	}
	
	public boolean checkHitboxButton(float xMouse,float yMouse,float xButton,float yButton){
		if ((xMouse<= xButton+parent.width*0.1 && xMouse >= xButton) && (yMouse >= yButton && yMouse <= yButton+parent.width*0.1)){
			return true;
		}else{
			return false;
		}
	}

}
