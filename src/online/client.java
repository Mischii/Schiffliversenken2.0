package online;

import sockets.MySocket;

public class client extends MySocket{
	
	public boolean serverConnected = false;
	public client(String ip,int port){
    	setConnection (ip, port);
		openServerConnection();
		sendHostRequest();
		serverConnected= true;
	}
	
	public void tick() {
    	if(serverConnected) {
    		sendLine("Action");
			String txt = getLine();
			if(txt!=null) {
				System.out.println(txt);
			}
    	}
	}
}
