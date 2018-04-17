package schiffeversenken;

import basics.Processing;
import functions.PlayField;
import functions.Variables;
import processing.core.PApplet;
import view.Background;
import view.FieldView;
import view.Shape;

public class GameView extends Processing {
	
	Shape myShape;
	Background bGround;

	public FieldView myFieldViewPlayer1;
	public FieldView myFieldViewPlayer2;
	

	public GameView(PApplet p, PlayField pf1, PlayField pf2) {
		super(p);
		myShape = new Shape(p);
		bGround = new Background(p);
		myFieldViewPlayer1 = new FieldView(p, pf1);
		myFieldViewPlayer2 = new FieldView(p, pf2);
	}

	public void show(Variables myVar) {
		bGround.show(myFieldViewPlayer1, myFieldViewPlayer2, myVar);
		myShape.drawButton((float)(parent.width*0.45),(float)(parent.height*0.1),"Spiler w�chsle!");
	}
	
	public Shape getShape() {
		return myShape;
	}
}