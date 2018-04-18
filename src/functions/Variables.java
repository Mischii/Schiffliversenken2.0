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
	double bBorder;
	public double pFSize;
	public double fSize;

	public Variables(int width, int height) {
		lBorder = width*0.05;
		rBorder = width*0.55;
		tBorder = height-height*0.8;
		pFSize = width*0.4;
		fSize = width*0.04;
	}



}
