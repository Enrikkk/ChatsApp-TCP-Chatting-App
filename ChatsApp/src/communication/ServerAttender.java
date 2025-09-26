package communication;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore; // To synchronize.
import application.ChatUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerAttender extends Thread {

	// Atributes
	private ServerSocket server; // Server socket to attend all the incoming joining requests from clients.
	
	// List used so that we can implement the "AllUsers" command.
	// Every started thread has a reference to this list, and it is 
	// accessed via mutual exclusion implemented through semaphores.
	private ArrayList<NameSocket> clients;
	
	// Now, this list will be used to have a record of all the 
	// users that joined (their ServerThread, to be more precise). 
	// It will be used to shut down the server.
	private ArrayList<ServerThread> threads;
	
	private ChatUI chatui;
	private Stage originalStage;
	private Scene originalScene;
	
	// Variable to stop running the main thread loop.
	private volatile boolean running;

	// We will do a basic mutual excuslion method to access the clients list.
	// Semaphores.
	private Semaphore mutex = new Semaphore(1); // To access control variables on mutual exclusion.
	private Semaphore screen = new Semaphore(1); // To write on the server's screen with mutual exclusion.

	// Whenever a new thread is created from this one (to attend a new client), he
	// will receive s parameters the list of clients and the synchronization methods to access
	// the shared resources, which could be accessed by every server thread at any time.

	// Constructor
	public ServerAttender(int port, ChatUI chatui, Stage stage, Scene scene)
						                           throws IOException, InterruptedException {
		// First, we create the server using the port given, it should be the same
		// port that the clients will use.
		this.server = new ServerSocket(port);
		this.clients = new ArrayList<NameSocket>();
		this.chatui = chatui;
		this.threads = new ArrayList<ServerThread>();
		this.running = true;
		
		// Data to get back the menu.
		this.originalStage = stage;
		this.originalScene = scene;
	}

	@Override
	public void run() {
		
		// Get screen lock.
		try { screen.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
		this.chatui.appendMessage("This text area will serve as a log of the messages sent to the chat, either by the server or by the users chatting in it.", "server");
		screen.release();
		
		try {
            while (running) { // Main client accepting loop.
                try {
                    Socket socket = server.accept();
                    
	                // We create the thread that will be in charge of this client.
	    			ServerThread thread = null;
	    			try {
	    				thread = new ServerThread(mutex, screen, socket, clients, chatui);
	    			} catch (IOException e) { e.printStackTrace(); }
                    
	    			try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                    threads.add(thread);
                    mutex.release();
                    
                    thread.start();
                } catch (SocketException e) {
                	chatui.appendMessage("Socket Closed", "server");
                    if (!running) break; // stop was called.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { communicateClosing(); }
		
	}
	
	// Method to start the closing of the server.
	public void closeServer() {
		
		chatui.appendMessage("closing server", "server");
		
		// First, we will put the "running" variable to false, so that the server will 
		// get out of the main loop.
		this.running = false;
		
		// After that, we will close the server socket, just in case it is blocked there.
		// This will let us end the chat even is the serverAttender thread is blocked 
		// waiting for a new client to connect.
		try { this.server.close(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	// Method to communicate to all of the possible clients that the chat will be closed.
	public void communicateClosing() {
		for(ServerThread thread : this.threads) {
			try { thread.close(); } catch (Exception ignored) { }
		}
		
		threads.clear();
		
		// Switch back to menu using a javafx thread.
        javafx.application.Platform.runLater(() -> {
            this.originalStage.setScene(originalScene);
            this.originalStage.setTitle("ChatsApp Menu");
            this.originalStage.show();
        });
	}

}
