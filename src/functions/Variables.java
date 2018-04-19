/**
 * collection of variables
 * 
 * @author	Lenny Johner, Michèle Habegger
 * @version	1.0
 * @since	13.04.2018
 */

package functions;

public class Variables {
	
	public double lBorder;
	public double rBorder;
	public double tBorder;
	public double bBorder;
	public double pFSize;
	public double fSize;

	public Variables(int gWidth, int gHeight) {
		lBorder = gWidth*0.05;
		rBorder = gWidth*0.55;
		tBorder = gHeight-gHeight*0.8;
		pFSize = gWidth*0.4;
		fSize = gWidth*0.04;
	}



}
