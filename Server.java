import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	
	public static void main(String [] args){
		GenreList genreList = new GenreList();
		int port = 1024;
		int clientCount = 0;
		
		try{
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server Up - Waiting for connections...");
			
			while(true){
				
				try{
					Socket clientSocket = serverSocket.accept();
					clientCount++;
					System.out.println("Connection #"+ clientCount);
					Server(clientSocket,genreList);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			System.out.println("Server error!!");
			e.printStackTrace();
		}
	}
	
	private static void Server(Socket clientSocket, GenreList genreList){
		new Handler(clientSocket,genreList).start();
	}
}