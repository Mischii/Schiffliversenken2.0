package schiffeversenken;
import processing.core.PApplet;

public class Schiffliversenken extends PApplet{

	public static void main(String[] args) {
		PApplet.main("schiffeversenken.Schiffliversenken");
	}

	GameControler myGame;

    Variables myVar;
    int placeShips = 12;

    public void settings(){
		size(1600,900);
		if (surface != null)
			surface.setResizable(true);
    }

    public void setup(){
        myGame = new GameControler(this);
        myGame.player2Fields.startSettingNextShip(4);
    }

    public void draw(){

    	if (!myGame.player2Fields.isPlacingAShip()) {
    		if (placeShips > 8) {
    			System.out.println(placeShips);
               myGame.player2Fields.startSettingNextShip(3);
               placeShips--;
            }
    		else if(placeShips > 6) {
    			myGame.player2Fields.startSettingNextShip(2);
                placeShips--;
    		}
          } 
    	if (!myGame.player1Fields.isPlacingAShip()) {
    		if (placeShips > 6) {            
               myGame.player1Fields.startSettingNextShip(4);
               placeShips--;
            }
    		else if(placeShips > 3) {
    			myGame.player1Fields.startSettingNextShip(3);
    			myGame.player1Fields.startSettingNextShip(3);
                myGame.player1Fields.startSettingNextShip(3);
                placeShips--;
    		}
    		else if(placeShips > 1) {
    			myGame.player1Fields.startSettingNextShip(2);
                myGame.player1Fields.startSettingNextShip(2);
                placeShips--;
    		}
          }
          myVar = new Variables(this);
          myGame.show();
    }

    public void mousePressed(){
        myGame.buttonClicked();
      }
    
}
