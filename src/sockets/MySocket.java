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
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket {

	final public static String defaultHostName = "localhost";
	//final public static String defaultProtocol = "http://";
	final public static int defaultPortNumber = 8080;
	final public static boolean log = true; // T: activates logging on console (Sytsem.err)

    private Socket echoSocket;
    private PrintWriter out=null;
    private BufferedReader in=null;

    //private String myProtocol;
    private String myHostName;
    private int myPortNumber;

    public String name = "client";
    
    public MySocket () { 
    	setConnection (defaultHostName, defaultPortNumber);
    }

    public MySocket (String newHost, int newPort) {
    	setConnection (newHost, newPort);
    }
    
    private void setConnection (String newHost, int newPort) {
    	if (newHost != null) {
    		//myProtocol = defaultProtocol; // TODO as Option
    		myHostName = newHost;
    		myPortNumber = newPort;
    	}
    }
    
    public void myLog (String outText) {
    	if (log) {
    		// TODO add TimeStamps
            System.err.println(myHostName+":"+myPortNumber+"> "+outText);
    	}
    }
    
    public void sendHostRequest () {
	    try {
    		// Send a request to server
	    	//String r = "GET /dashboard/index.html HTTP/1.1\r\nHost: localhost\r\n";
    		out.println("Hallo,Hallo"); // TODO with myPath
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

    public boolean sendLine (String serverOutput) {
	    try {
	    	out.println(serverOutput);
    	    myLog("sending: "+serverOutput);
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("--- stream send println() error");
    	    return false;
    	}
	    return true;
    }

    public void openServerConnection () {
    	try {
            myLog("opening connection with "+name+":"+myHostName+":"+myPortNumber);
    	    echoSocket = new Socket(myHostName, myPortNumber);
            myLog("opening output stream to the other");
    	    out = new PrintWriter(echoSocket.getOutputStream(), true);
            myLog("opening input stream from the other");
    	    in =  new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("### open failed");
    	}
    }
 
    ServerSocket serverSocket;
    
    public void openServer () {
    	try {
            myLog("opening connection with "+name+":"+myHostName+":"+myPortNumber);
    	    serverSocket = new ServerSocket(myPortNumber);
    	    echoSocket = serverSocket.accept(); // TODO wartet auf client (nötig?)
            myLog("opening output stream to the other");
    	    out = new PrintWriter(echoSocket.getOutputStream(), true);
            myLog("opening input stream from the other");
    	    in =  new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	    myLog("### open server failed");
    	}
    }
 
    public void closeSocketConnection () {
    	// nothing to do
        myLog("closing connection with client");
    }


}


