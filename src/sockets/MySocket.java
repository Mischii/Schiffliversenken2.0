package sockets;

/******************************************************************************
 *
 *  Java Socket Connector Functionality
 *
 *
 *
 *
 *
 ******************************************************************************/

import java.io.*;

import java.net.Socket;

public class MySocket {

	final public static String defaultHostName = "localhost";
	final public static String defaultProtocol = "https://";
	final public static int defaultPortNumber = 80;
	final public static int timeOut = 100; // loops
	final public static boolean log = true; // T: activates logging on console (Sytsem.err)

    private Socket echoSocket;
    private PrintWriter out=null;
    private BufferedReader in=null;

    private String myProtocol;
    private String myHostName;
    private String myPath = "";
    private String myFile = "";
    private int myPortNumber;

    public MySocket () { 
    	setConnection (defaultHostName, defaultPortNumber, "");
    }

    public MySocket (String newHost, int newPort, String pathAndFile) {
    	setConnection (newHost, newPort, pathAndFile);
    }
    
    private void setConnection (String newHost, int newPort, String pathAndFile) {
    	if (newHost != null && pathAndFile != null) {
    		myProtocol = defaultProtocol; // TODO as Option
    		myHostName = newHost;
    		myPortNumber = newPort;
    		// TODO separate Path and File from pathAndFile
    		myFile = pathAndFile; // TODO add a first "/" if not there
    		myPath = "";
    	}
    }
    
    private void myLog (String outText) {
    	if (log) {
    		// TODO add TimeStamps
            System.err.println(myHostName+":"+myPortNumber+"> "+outText);
    	}
    }
    
    public void sendHostRequest () {
	    try {
    		// Send a request to server
    		out.println(myProtocol+myHostName); // TODO with myPath
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("!!! request error");
    	}
    }

    public String getLine () {
    	String serverInput = null;
	    try {
	    	serverInput = in.readLine();
	    	if (serverInput != null) {
	    		return serverInput;
	    	}
	    	// TODO throw exception
    	    myLog("--- streaming null line error");
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("--- streaming readLine() error");
    	}
	    return "";
    }

    void openServerConnection () {
    	try {
            myLog("opening connection with client");
    	    echoSocket = new Socket(myHostName, myPortNumber);
            myLog("opening output stream to server");
    	    out = new PrintWriter(echoSocket.getOutputStream(), true);
            myLog("opening input stream from server");
    	    in =  new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("### open failed");
    	}
    }
 
    void closeSocketConnection () {
    	// nothing to do
        myLog("closing connection with client");
    }


    // For Testing only:
    public static void main (String [] args) {
    	// USER: open XAMP Webserver Apache before start testing this
    	MySocket s = new MySocket("localhost", 80, "");
    	s.openServerConnection();
    	s.sendHostRequest();
        s.myLog("waiting for chars...");

	    System.out.println("****************************");

	    int timeCounts = timeOut;
    	while (timeCounts > 0) {
    		String txt = s.getLine();
    		if (txt == null || txt =="") break;
    		System.out.println(txt);
        	timeCounts--;
    	}
    	
    	System.out.println("****************************");
        
    	s.closeSocketConnection();
    }
}


