import java.io.*;
import java.net.Socket;
import java.util.*;


public class Handler extends Thread{

	private ObjectInputStream inStream;
	private ObjectOutputStream onStream;
	
	private GenreList genreList;
	public Handler(Socket clientSocket,GenreList genreList){
		try{System.out.println("Entre al handler");
		this.onStream = new ObjectOutputStream(clientSocket.getOutputStream());
			this.inStream = new ObjectInputStream(clientSocket.getInputStream());
			
			this.genreList = genreList;
			System.out.println("Entre al handler2");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			
			int readCommand =0;
			do{System.out.println("Entre al run");
				readCommand = this.inStream.readInt();
				System.out.println("Option Selected #"+readCommand);
				
				switch(readCommand){
				case Codes.ADD_GENRE: 
					this.NewGen();  
					break;
					case Codes.ADD_MOVIE:
					this.NewMov();
					break;
					case Codes.LIST_GENRE:
					this.ListGen();
					break;			
					case Codes.LIST_MOV_GEN:
					ListMov();
					case Codes.CLOSECONN:
					System.out.println("Bye Bye");					
					break;
				}
			}while(readCommand!= Codes.CLOSECONN);
		}catch(IOException e){
		e.printStackTrace();
		}
	}
	private synchronized void NewGen(){
		try{
			System.out.println("Sending the ok");
		      this.onStream.writeInt(Codes.OK);
		      System.out.println("flush");
		      this.onStream.flush();
		      System.out.println("OK the sent");
		}catch (Exception e) {
		      e.printStackTrace();
	    }
String title = "\0";
		
		try{
			System.out.println("Getting Genre");
			title = this.inStream.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Genero por añadir "+ title);
		
		if(title != "\0"){
			System.out.println("Adding "+title+" to BST");
			Genre genre = new Genre(title);
			this.genreList.addGenre(genre);
			System.out.println("Genre '"+ title+"' has been add!");
		}
		else{
			System.out.println("Can't be add");
		}
		try{
			this.onStream.writeInt(Codes.OK);
			this.onStream.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private synchronized void NewMov(){
		Movie movie = new Movie();
		try{
			System.out.println("Sending the ok");
		      this.onStream.writeInt(Codes.OK);
		      System.out.println("flush");
		      this.onStream.flush();
		      System.out.println("OK the sent");
		}catch (Exception e) {
		      e.printStackTrace();
	    }
		try{
			System.out.println("Sending genreList");
			this.onStream.writeObject(this.genreList);
			this.onStream.flush();
		}catch(Exception e){
			System.out.println("Error sending genreList");
		}
		try{
			movie = (Movie)this.inStream.readObject();
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		genreList.addMovie(movie);
		try{
			this.onStream.writeInt(Codes.OK);
			this.onStream.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private synchronized void ListGen(){
	/*	try{
			System.out.println("Sending the ok");
		      this.onStream.writeInt(Codes.OK);
		      System.out.println("flush");
		      this.onStream.flush();
		      System.out.println("OK the sent");
		}catch (Exception e) {
		      e.printStackTrace();
	    }  */
		genreList.displayGenreList();
		try{
			System.out.println("Sending genreList");
			this.onStream.writeObject(this.genreList);
			this.onStream.flush();
		}catch(Exception e){
			System.out.println("Error sending genreList");
		}
	}
	private synchronized void ListMov(){
		try{
			System.out.println("Sending genreList");
			this.onStream.writeObject(this.genreList);
			this.onStream.flush();
		}catch(Exception e){
			System.out.println("Error sending genreList");
		}
	}
}