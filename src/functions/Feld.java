package functions;

public class Feld {

	public int myZustand = 0;
	public int myShipSize = 0;
	public int myShipID = 0;
	
	public int getZustand () {
		return myZustand;
	}
	
	public void setZustand (int newZ) {
		myZustand = newZ;
	}
	
	public void setShipSize (int newS) {
		myShipSize = newS;
	}

	public void changeColorSetShip(){
		if(myZustand == 2 || myZustand == 1) {
			myZustand = 0;
		}
		if(myZustand == 3) {
			myZustand = 4;
		}
		if(myZustand == 0) {
			myShipID = 0;
		}
	}
	
	public void checkShooted() {
		//nicht getroffen
		if(myZustand < 4) {
			myZustand = 7;
		}
		//Getroffen
		if(myZustand == 4) {
			myZustand = 5;
		}
	}
}
