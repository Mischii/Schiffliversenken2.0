package schiffeversenken;
import processing.core.PApplet;

public class PlayField {
	PApplet parent;
	Variables myVar;
	
	int anzahlSchiffTeili = 0; // actual ship component
	int schiffSize = 0; // actual ship component
	boolean isFirstPart=false;
		  
	int platzierteSchiffli = 0;
	boolean setSchiffli = false;
	boolean itsTurn = false;
	int line = 10;
	final int fWidth = 10;
	final int fHeight = 10;
	final int zustand = 4;
	final Feld[][] felderArray = new Feld[fWidth][fHeight];
	int destroyed4Er = 0;
	int destroyed3Er = 0;
	int destroyed2Er = 0;
		  
	//Felder-Array erstellen
	PlayField(PApplet p) {
		parent = p;
		myVar = new Variables(parent);
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				felderArray[i][j] = new Feld (parent);
			}
		}
	}
		  
	void drawFields(float pos) {      //Felder zeichnen
		float iPos = (float) myVar.tBorder;
		for (int r = 0; iPos<=myVar.tBorder+parent.width*0.36; iPos+=myVar.fSize, r++){
			float jPos = pos;
			for (int c = 0;  jPos<=pos+parent.width*0.36;jPos+=myVar.fSize, c++){
				felderArray[c][r].setColor();
				parent.rect(jPos,iPos,(float)(myVar.fSize),(float)(myVar.fSize));
			}
		}
	}
		  
	void startSettingNextShip (int maxSize) {
		anzahlSchiffTeili = maxSize; // actual ship component
		isFirstPart = true;
		schiffSize = maxSize; 
	}
	  
	void resetSettings () {
		anzahlSchiffTeili = 0; // actual ship component
		schiffSize = 0; 
	}
		  
	boolean isPlacingAShip () {
		if (anzahlSchiffTeili > 0) return true; // actual ship component
			return false; 
	}
		  
	void tryToSetetShip(int column, int row) {
		if (anzahlSchiffTeili > 0) {
			if (isFirstPart) {
				if (felderArray[column][row].myZustand == 0) {
					felderArray[column][row].myZustand = 3;
		            felderArray[column][row].setShipSize(schiffSize);// gesetzt
		            isFirstPart = false;
		            updateWaterCross(column,row);
		            lockWaterAngles(column,row);
		            System.out.println("First("+column+","+row+")");
		            anzahlSchiffTeili--;
				}
			}else{
				if (felderArray[column][row].myZustand == 1) {
					felderArray[column][row].myZustand = 3;
		            felderArray[column][row].setShipSize(schiffSize); // gesetzt
		            System.out.println("Next("+column+","+row+")");
		            updateWaterCross(column,row);
		            lockWaterCross(column,row);
		            lockWaterAngles(column,row);
		            anzahlSchiffTeili--;
		            if (anzahlSchiffTeili < 1) lockLastWaterCrossPart(column,row);
		          }
			}
		}
	}
		  
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
	void setetShip() {
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				if(felderArray[i][j].myZustand == 1) {
					felderArray[i][j].setZustand(2);
				}
			}
		}
	}
		  

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
		  
	void schiessen(int column, int row){
		felderArray[column][row].checkShooted();
		if(felderArray[column][row].myShipSize == 4) {
			destroyed4Er ++;
			if(destroyed4Er == 4) {
				toDestroy(4);
			}
		}
		if(felderArray[column][row].myShipSize == 3) {
			destroyed3Er ++;
			if(destroyed3Er == 3) {
				toDestroy(3);
			}
		}
		if(felderArray[column][row].myShipSize == 2) {
			destroyed2Er ++;
			if(destroyed2Er == 2) {
				toDestroy(2);
			}
		}
	}
	void toDestroy(int size) {
		for (int i = 0; i < fWidth; i++) {
			for (int j = 0; j < fHeight; j++) {
				if(felderArray[i][j].myShipSize == size) {
					felderArray[i][j].setZustand(6);
				}
			}
		}
	}
}
