/**
* checks his own state
* 
* @author	Lenny Johner, Michèle Habegger
* @version	1.0
* @since	19.03.2018
*/

package functions;

public class Feld {

	public int myZustand = 0;
	public int myShipSize = 0;
	public int myShipID = 0;
	
	public int getZustand () {
		return myZustand;
	}
	
	/**
	 * change his own state 
	 * @param newZ
	 */
	public void setZustand (int newZ) {
		myZustand = newZ;
	}
	
	/**
	 * change his own length
	 * @param newS
	 */
	public void setShipSize (int newS) {
		myShipSize = newS;
	}
	
	/**
	 * checks if ship or empty
	 */
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
	
	/**
	 * check if destroyed
	 */
	public void checkShooted() {
		//not shoot
		if(myZustand < 4) {
			myZustand = 7;
		}
		//shoot
		if(myZustand == 4) {
			myZustand = 5;
		}
	}
}
