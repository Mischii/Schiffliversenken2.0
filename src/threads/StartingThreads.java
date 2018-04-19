package threads;

import sockets.MySocket;

public class StartingThreads {
	static Server server = new Server("localhost", 80);
	static MySocket client = new MySocket("localhost", 80, "");
	final public static int timeOut = 100; // loops

	
	// For Testing only:
    public static void main (String [] args) {
    	server.start();
    	
	    int timeCounts = timeOut*10;
    	// USER: open XAMP Webserver Apache before start testing this
    	while (!server.isRunning) {
    		System.out.print("*");
        	timeCounts--;
        	if (timeCounts < 0) return;
    	}
    	client.openServerConnection();
    	client.sendHostRequest();
    	client.myLog("waiting for chars...");

	    System.out.println("****************************");

	    timeCounts = timeOut;
    	while (timeCounts > 0) {
    		String txt = client.getLine();
    		if (!(txt == null || txt =="") ) {
    			System.out.println("receiving: "+txt);
    		}
        	timeCounts--;
    	}
    	
    	System.out.println("****************************");
        
    	client.closeSocketConnection();
    }
}
