package functions;
import view.GameView;

public class GameControler {

	private PlayField player1Fields;
	private PlayField player2Fields;
	int myWidth;
	int myHeight;
	
	int placeShips = 12;

	public GameControler(int width, int height) {
		myWidth = width;
		myHeight = height;
		player1Fields = new PlayField();
		player2Fields = new PlayField();
		player2Fields.setSchiffli = true;
        player2Fields.startSettingNextShip(4);
        player2Fields.setetShip();
	}
	
	public PlayField getPlayerFields(int player) {
		if (player == 1) return player1Fields;
		return player2Fields;
	}

	public void draw(){
    	if (!player2Fields.isPlacingAShip()) {
    		if (placeShips > 8) {
    			System.out.println(placeShips);
    			player2Fields.startSettingNextShip(3);
               placeShips--;
            }
    		else if(placeShips > 6) {
    			player2Fields.startSettingNextShip(2);
                placeShips--;
    		}
    		player2Fields.setetShip();
          } 
    	if (!player1Fields.isPlacingAShip()) {
    		if (placeShips > 6) {            
    			player1Fields.startSettingNextShip(4);
               placeShips--;
            }
    		else if(placeShips > 3) {
    			player1Fields.startSettingNextShip(3);
                placeShips--;
    		}
    		else if(placeShips > 1) {
    			player1Fields.startSettingNextShip(2);
                placeShips--;
    		}
    		player2Fields.setetShip();
          }
    }
	
	//überprüfung des Buttons!!!
	public void buttonClicked(float x, float y, Variables myVar, GameView myGameView){  
		int column = 11;
		int row = 11;
		if(((y-(myVar.tBorder))/(myVar.fSize))>=0) {
			row = (int)((y-(myVar.tBorder))/(myVar.fSize));
		}
		if(player2Fields.setSchiffli == true || player1Fields.itsTurn == true) {
			if(((x-(myVar.rBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.rBorder))/(myVar.fSize));
			}
		}else if(player1Fields.setSchiffli == true || player2Fields.itsTurn == true){
			if(((x-(myVar.lBorder))/(myVar.fSize))>=0) {
				column = (int)((x-(myVar.lBorder))/(myVar.fSize));
			}
		}
		if ( (column < 10  && column+1 > 0) && 
		   (row    < 10 && row+1 > 0)){			//Klicken ist inerhalb des Spielfeldes
		     if (player2Fields.setSchiffli == true) {
			     player2Fields.tryToSetetShip(column, row);
			 }else {
				 player1Fields.tryToSetetShip(column, row);
			 }
		}
		//Button für den Gefächtstart überprüfen
		if( (player2Fields.isPlacingAShip() == false) && 
		    myGameView.getShape().checkHitboxButton(x,y,(float)(myWidth*0.45),(float)(myHeight*0.1))) {
			myGameView.setBackgroundDark();
			for (int r = 0; r <= 9; r++){
				for (int c = 0;  c<=9;c++){
					player2Fields.getFeld(c,r).changeColorSetShip();
					player2Fields.setSchiffli = false;
					player1Fields.setSchiffli = true;
				}
			}
		}
		if( (player1Fields.isPlacingAShip() == false) && 
			myGameView.getShape().checkHitboxButton(x,y,(float)(myWidth*0.45),(float)(myHeight*0.1))) {
			myGameView.setBackgroundLight();
			for (int r = 0; r <= 9; r++){
				for (int c = 0;  c<=9;c++){
					player1Fields.getFeld(c,r).changeColorSetShip();
					player1Fields.setSchiffli = false;
					player2Fields.itsTurn = true;
				}
			}
		}
		//Wechselt zwischen den Spielern bei Jedem Schuss ab
		if(player1Fields.setSchiffli==false && player2Fields.setSchiffli==false) {
			if ((column < 10  && column+1 > 0) && (row    < 10 && row+1 > 0)){			//Klicken ist inerhalb des Spielfeldes
				if(player2Fields.itsTurn == true) {
					if((player1Fields.getFeld(column, row).myZustand == 0)||(player1Fields.getFeld(column, row).myZustand == 4)) {
						player1Fields.schiessen(column, row);
						player2Fields.itsTurn = false;
						player1Fields.itsTurn = true;
					}
				}else {
					if((player2Fields.getFeld(column, row).myZustand == 0)||(player2Fields.getFeld(column, row).myZustand == 4)) {
						player2Fields.schiessen(column, row);
						player1Fields.itsTurn = false;
						player2Fields.itsTurn = true;
					}
				}
			}
		}
	}
}
