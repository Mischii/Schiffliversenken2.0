package online;

import sockets.MySocket;

public class server extends MySocket{
	public boolean isRunning =false;
	
	public server(){
		myLog("server is running...");
		isRunning = true;
		name = "server";
		openServer();
	}
	
	public void tick() {
			if (isRunning) {
				String txt = getLine();
				System.out.println(txt+" Test 1");
				if(txt.equals("Action")) {
					System.out.println(txt+" Test 2");
					sendLine("Reaction");
			}
    	}
	}
}
