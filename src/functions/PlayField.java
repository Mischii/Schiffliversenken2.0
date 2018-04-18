/**
 * create the array and check the part of ships
 * controlling the playfields
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	19.03.2018
 */

package functions;

public class PlayField {
	
	int anzahlSchiffTeili = 0; // actual ship component
	int schiffSize = 0; // actual ship component
	boolean isFirstPart=false;
		 
	int platzierteSchiffli = 0;
	public boolean setSchiffli = false;
	public boolean itsTurn = false;
	int line = 10;
	final int fWidth = 10;
	final int fHeight = 10;
	final int zustand = 4;
	private final Feld[][] felderArray = new Feld[fWidth][fHeight];
	int shipID = 1;
		
	/**
	 * create the field array
	 */
	public PlayField() {
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				felderArray[i][j] = new Feld ();
			}
		}
	}
		
	public Feld getFeld(int i, int j) {
	   return felderArray[i][j];
	}
	
	/**
	 * create new ships
	 * initialize the length of the ships
	 * @param maxSize
	 */
	public void startSettingNextShip (int maxSize) {
		anzahlSchiffTeili = maxSize; // actual ship component
		isFirstPart = true;
		schiffSize = maxSize; 
	}
	/**
	 * after placing ship prepare for next ship
	 */
	void resetSettings () {
		anzahlSchiffTeili = 0; // actual ship component
		schiffSize = 0; 
	}
	/**
	 * control player placing ships
	 * @return
	 */
	public boolean isPlacingAShip () {
		if (anzahlSchiffTeili > 0) return true; // actual ship component
			return false; 
	}
	
	/**
	 * control if player can set the first or next part of the ship
	 * @param column
	 * @param row
	 */
	public void tryToSetetShip(int column, int row) {
		Feld f = felderArray[column][row];
		if (anzahlSchiffTeili > 0) {
			if (isFirstPart) {
				if (f.myZustand == 0) {
					f.myZustand = 3;
		            f.setShipSize(schiffSize);
		            f.myShipID = shipID; //set
		            isFirstPart = false;
		            updateWaterCross(column,row);
		            lockWaterAngles(column,row);
		            System.out.println("First("+column+","+row+")");
		            anzahlSchiffTeili--;
				}
			}else{
				if (f.myZustand == 1) {
					f.myZustand = 3;
		            f.setShipSize(schiffSize);
		            f.myShipID = shipID; //set
		            System.out.println("Next("+column+","+row+")");
		            updateWaterCross(column,row);
		            lockWaterCross(column,row);
		            lockWaterAngles(column,row);
		            anzahlSchiffTeili--;
		            if (anzahlSchiffTeili < 1) {
		            	lockLastWaterCrossPart(column,row);
		            	shipID++;
					}
		        }
			}
		}
	}
	
	/**
	 * create water around the hole ship when it's the last part of the ship
	 * @param column
	 * @param row
	 */
	void lockLastWaterCrossPart (int column, int row) {
		if (row > 0) 
			if (felderArray[column][row-1].myZustand <= 1) {
				felderArray[column][row-1].myZustand = 2; // wasser und setzen gesperrt
		        if (row+schiffSize <= 9)
		        	felderArray[column][row+schiffSize].myZustand = 2; // wasser gegenüber setzen gesperrt
		    }
		    if (column > 0) 
		    	if (felderArray[column-1][row].myZustand <= 1) {
		    		felderArray[column-1][row].myZustand = 2;
		    		if (column+schiffSize <= 9)
		    			felderArray[column+schiffSize][row].myZustand = 2; // wasser gegenüber setzen gesperrt
		    	}
		    if (row < 9) 
		    	if (felderArray[column][row+1].myZustand <= 1) {
		    		felderArray[column][row+1].myZustand = 2;
		    		if (row-schiffSize >= 0 )
		    			felderArray[column][row-schiffSize].myZustand = 2; // wasser gegenüber setzen gesperrt
		    	}
		    if (column < 9) 
		    	if (felderArray[column+1][row].myZustand <= 1) {
		    		felderArray[column+1][row].myZustand = 2;
		    		if (column-schiffSize >= 0 )
		    			felderArray[column-schiffSize][row].myZustand = 2; // wasser gegenüber setzen gesperrt
		    	}
		    
		    resetSettings();
		  }
	
	/**
	 * show the player which field can be placed with a ship
	 * @param column
	 * @param row
	 */
	void updateWaterCross(int column, int row) {
		if (row > 0) {
			if (felderArray[column][row-1].myZustand <= 1) {
				felderArray[column][row-1].myZustand = 1; // wasser gesperrt, setzen erlaubt 
			}
		}
		if (column > 0) {
			if (felderArray[column-1][row].myZustand <= 1) {
				felderArray[column-1][row].myZustand = 1;
			}
		}
		if (row < 9) {
			if (felderArray[column][row+1].myZustand <= 1) {
				felderArray[column][row+1].myZustand = 1;
			}
		}
		if (column < 9) {
			if (felderArray[column+1][row].myZustand <= 1) {
				felderArray[column+1][row].myZustand = 1;
			}
		}
	}
	/**
	 * show the player which field can't be placed with a ship
	 * @param column
	 * @param row
	 */
	void lockWaterCross(int column, int row) {
		// locate adjacent part
		int aRow = -1;
		int aCol = -1;
		if (row > 0) 
			if (felderArray[column][row-1].myZustand  == 3) {aCol=column; aRow=row-1;} // found part
		if (column > 0) 
			if (felderArray[column-1][row].myZustand  == 3) {aCol=column-1; aRow=row;} // found part
		if (row < 9) 
			if (felderArray[column][row+1].myZustand  == 3) {aCol=column; aRow=row+1;} // found part
		if (column < 9) 
			if (felderArray[column+1][row].myZustand  == 3) {aCol=column+1; aRow=row;} // found part
		System.out.println("Found("+aCol+","+aRow+")");
		       
		// try locking water on cross:  
		if (row > 0) 
			if (felderArray[column][row-1].myZustand <= 1) if (row-1 != aRow && column != aCol) felderArray[column][row-1].myZustand = 2; // wasser gesperrt, setzen gesperrt
		if (column > 0) 
			if (felderArray[column-1][row].myZustand <= 1) if (row != aRow && column-1 != aCol) felderArray[column-1][row].myZustand = 2;
		if (row < 9) 
			if (felderArray[column][row+1].myZustand <= 1) if (row+1 != aRow && column != aCol) felderArray[column][row+1].myZustand = 2;
		if (column < 9) 
			if (felderArray[column+1][row].myZustand <= 1) if (row != aRow && column+1 != aCol) felderArray[column+1][row].myZustand = 2;
		}
	public void setetShip() {
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				if(felderArray[i][j].myZustand == 1) {
					felderArray[i][j].setZustand(2);
				}
			}
		}
	}
		  
	
	/**
	 * show the player that he can't place a ship at the angle of the previous part
	 * @param column
	 * @param row
	 */
	void lockWaterAngles (int column, int row) {
		if (row > 0 && column >0) 
			if (felderArray[column-1][row-1].myZustand <= 1) felderArray[column-1][row-1].myZustand = 2; // wasser und setzen gesperrt
		if (row < 9 && column < 9) 
			if (felderArray[column+1][row+1].myZustand <= 1) felderArray[column+1][row+1].myZustand = 2; // wasser und setzen gesperrt
		if (row > 0 && column < 9) 
			if (felderArray[column+1][row-1].myZustand <= 1) felderArray[column+1][row-1].myZustand = 2; // wasser und setzen gesperrt
		if (row < 9 && column >0) 
			if (felderArray[column-1][row+1].myZustand <= 1) felderArray[column-1][row+1].myZustand = 2; // wasser und setzen gesperrt
	}
	
	/**
	 * change the state of the field if destroyed
	 * count the number of destroyed ship parts
	 * @param column
	 * @param row
	 */
	public void schiessen(int column, int row){
		int destroyed = 0;
		felderArray[column][row].checkShooted();
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				if((felderArray[i][j].myShipID == felderArray[column][row].myShipID) && (felderArray[i][j].myZustand == 5)) {
					destroyed ++;
				}
			}
		}
		if(destroyed == felderArray[column][row].myShipSize) {
			if(felderArray[column][row].myShipID != 0) {
				toDestroy(felderArray[column][row].myShipID);
			}
		}
	}
	/**
	 * if ship destroyed change state to drowned
	 * @param id
	 */
	void toDestroy(int id) {
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				if(felderArray[i][j].myShipID == id) {
					felderArray[i][j].setZustand(6);
				}
			}
		}
	}
}
