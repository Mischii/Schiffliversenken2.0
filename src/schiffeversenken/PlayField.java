package schiffeversenken;
import processing.core.PApplet;
import java.util.concurrent.ThreadLocalRandom;

public class PlayField {
		PApplet parent;
		Variables myVar;
	
		  int anzahlSchiffTeili = 0; // actual ship component
		  int schiffSize = 0; // actual ship component
		  boolean isFirstPart=false;
		  
		  int platzierteSchiffli = 0;
		  boolean setSchiffli = false;
		  int line = 10;
		  final int fWidth = 10;
		  final int fHeight = 10;
		  final int zustand = 4;
		  final Feld[][] felderArray = new Feld[fWidth][fHeight];                                                //Felder-Array erstellen

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
		  
		  void mouseCheck() {  //Maus abfragen
		    int x = parent.mouseX;
		    int y = parent.mouseY;
		    int row = 0;
		    int column = 0;
		    
		    // compute row and column from mouse position:
		    if (setSchiffli == true){
		      column = (int)((x-(myVar.lBorder))/(myVar.fSize));
		    } else {
		      column = (int)((x-(myVar.rBorder))/(myVar.fSize));
		    }
		    row = (int)((y-(myVar.tBorder))/(myVar.fSize));
		    
		    if ( (column < fWidth  && column+1 > 0) && 
		         (row    < fHeight && row+1 > 0)    &&
		         (x>= myVar.rBorder && y>= myVar.tBorder)) {  //Klicken ist inerhalb des Spielfeldes
		         
		      tryToSetetShip(column, row);
		      
		    } /*else {    
		      // Klicken auserhalb des Spielfeldes -> Fehleranzeige
		    	parent.fill(0000000);
		    	parent.textSize(50);
		    	parent.textAlign(parent.CENTER);
		    	parent.text("Hallllt STOP!!!",parent.width/2,parent.height/12);
		    }*/
		  }
		  
		  /**
		  *  Tries to set a a ship component
		  *   if first: set only on state 0
		  *   else set only on state 1
		  *   update water
		  *   update environement
		  */
		  void tryToSetetShip(int column, int row) {
		    if (anzahlSchiffTeili > 0) {
		      if (isFirstPart) {
		          if (felderArray[column][row].myZustand == 0) {
		             felderArray[column][row].myZustand = 3; // gesetzt
		             isFirstPart = false;
		             updateWaterCross(column,row);
		             lockWaterAngles(column,row);
		             System.out.println("First("+column+","+row+")");
		             anzahlSchiffTeili--;
		          }
		      } else {
		          if (felderArray[column][row].myZustand == 1) {
		             felderArray[column][row].myZustand = 3; // gesetzt
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
		  /**
			  *  Computer tries to set a a ship component
			  *   if first: set only on state 0
			  *   else set only on state 1
			  *   update water
			  *   update environement
			  */
		  void setShipComputer(){
			  while (platzierteSchiffli<6) {
				  int column = ThreadLocalRandom.current().nextInt(0,9);
				  int row = ThreadLocalRandom.current().nextInt(0,9);
				  tryToSetetShip(column, row);
				  System.out.println("Next("+column+","+row+")");
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
		     if (row > 0) 
		       if (felderArray[column][row-1].myZustand <= 1) felderArray[column][row-1].myZustand = 1; // wasser gesperrt, setzen erlaubt
		     if (column > 0) 
		       if (felderArray[column-1][row].myZustand <= 1) felderArray[column-1][row].myZustand = 1;
		     if (row < 9) 
		       if (felderArray[column][row+1].myZustand <= 1) felderArray[column][row+1].myZustand = 1;
		     if (column < 9) 
		       if (felderArray[column+1][row].myZustand <= 1) felderArray[column+1][row].myZustand = 1;
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
}
