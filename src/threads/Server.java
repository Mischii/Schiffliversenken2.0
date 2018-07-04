package threads;

import sockets.MySocket;

public class Server extends Thread {
	String serverName = "server";
	MySocket mySocket;	
	public boolean isRunning = false;
	
	public Server(String newHost, int newPort) {
		mySocket = new MySocket (newHost, newPort);
		mySocket.name = "server";
	}
	
	public void run() {
		mySocket.myLog("server is running...");
		isRunning = true;
		mySocket.openServer();
		//mySocket.sendHostRequest();
		mySocket.myLog("server sends chars...");
		int maxLoops = 200;
		while (true) {
			isRunning = true;
			mySocket.sendLine("Hallo");
			maxLoops--;
			//if (maxLoops < 0) break;
		}
		
	}
}