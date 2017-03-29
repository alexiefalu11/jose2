import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private ObjectInputStream inStream;
	private ObjectOutputStream onStream;
	private String serverAdd;
	private int serverPort;
	
 public static void main(String[] args) throws ClassNotFoundException{
	 String serverAdd = "localhost";
	 int serverPort = 1024;
	 Client client = new Client(serverAdd,serverPort);
	 System.out.println("Connecting to " + serverAdd + " through " + serverPort);
	 client.conn();
 	}
 public Client(String serverAdd, int serverPort){
	 this.serverAdd = serverAdd;
	 this.serverPort = serverPort;
 }
 private void conn(){
	 System.out.println("Entre conn");
	
	 int select = 0;
	 try{
		 InetAddress canAdd = InetAddress.getByName(this.serverAdd);
	 Socket socket = new Socket(canAdd,this.serverPort); //Se crea connection
	 System.out.println(canAdd);
	 System.out.println(socket.isConnected());
	 System.out.println("Entre conn try");
	 this.onStream = new ObjectOutputStream(socket.getOutputStream());
	 this.inStream = new ObjectInputStream(socket.getInputStream());
	 System.out.println("Entre conn try2");
	 
	 System.out.println("Entre conn try3");
	// Runtime.getRuntime().exec("clear");
	 
	 do{System.out.println("Entre conn do");
		 select = this.Menu();
		 
		 switch(select){
		 case Codes.ADD_GENRE:
			 this.newGen();
			 break;
		 case Codes.ADD_MOVIE:
			 this.newMov();
		 case Codes.LIST_GENRE:
		 	this.ListGen();
			 break;
		 case Codes.LIST_MOV_GEN:
			 ListMov();
			 break;
		 case Codes.CLOSECONN:
			 System.out.println("Saliendo de Programa! Adios");
			 break;
		 }
	 }while(select != Codes.CLOSECONN);
	 socket.close();
	 }catch(IOException e){
		 e.printStackTrace();
	 }
 }
private int Menu(){
		 
		 int select =0;
		 
		 Scanner input = new Scanner(System.in);
		 
		 System.out.println("=============== Movie Database =================");
		 System.out.println("Favor de escojer la opción a utilizar de la siguiente lista de pelicula");
		 System.out.println("(1) Añadir un genero de pelicula");
		 System.out.println("(2) Añadir una pelicula");
		 System.out.println("(3) Modificar una pelicula");
		 System.out.println("(4) Enlistar todos los generos");
		 System.out.println("(5) Enlistar pelicula por genero");
		 System.out.println("(6) Enlistar una pelicula especifica");
		 System.out.println("(7) Buscar por pelicula");
		 System.out.println("(8) Salir");
		 
		 System.out.println("Seleccion --> ");
		 try{
		 select = input.nextInt();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return select;
	 }
private void newGen(){	 
	 int code = 0;
	 
	 System.out.println("********Añadiendo nuevo Genero********");
	 System.out.println("Nuevo Genero --> ");
	 String Genre = new Scanner (System.in).nextLine();
	 
	 try{
		 this.onStream.writeInt(Codes.ADD_GENRE);
		 this.onStream.flush();
	 }catch(IOException e){
		 e.printStackTrace();
		 return;
	 }
	 
	 try{
		 code = this.inStream.readInt();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 
	 if(code == Codes.OK){
		 try{
			 this.onStream.writeUTF(Genre);
			 this.onStream.flush();
		 }catch(IOException e){
			 e.printStackTrace();
			 return;
		 }
		 code = 0;
		 try{
			 code = this.inStream.readInt();
		 }catch(IOException e){
			 e.printStackTrace();
			 return;
		 }
		 if(code == Codes.OK){
			 System.out.println("EL Genero "+Genre+" ha sido Añadido!");
		 }else{
			 System.out.println("ERROR al añadir genero");
			 return;
		 }
	 }else{
		 System.out.println("Error al recivir OK");
		 return;
	 	}
	}
private void newMov(){
	 int code = 0;
	 Scanner input = new Scanner(System.in);
	 GenreList genreList = new GenreList();
	try{
		 this.onStream.writeInt(Codes.ADD_MOVIE);
		 this.onStream.flush();
	 }catch(IOException e){
		 e.printStackTrace();
		 return;
	 }
	try{
		 code = this.inStream.readInt();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 
	 if(code == Codes.OK){
		 try{
				genreList = (GenreList)this.inStream.readObject();
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		 System.out.println("********Añadiendo nueva Pelicula***********");
			System.out.println("Favor de ingresar la siguiente información");
			System.out.print("Titulo --> ");
			String title = input.nextLine();
			int count =1, select = 0;
			System.out.println("\n********Añadir Actores********"); 
			ActorList list = new ActorList();
			do{
				System.out.println("1 - Add Actor");
				System.out.println("2 - Exit");
				select = input.nextInt();
				if(select ==1){
					System.out.println("Actor #"+count++);
					System.out.print("Last Name--> ");
				String last = input.next();
				System.out.print("\tFirst Name--> ");
				String first = input.next();		
				Actor actor = new Actor(first,last);
				System.out.println();
				list.addActor(actor);
				}
				
			}while(select != 2);
			genreList.displayGenreList();
			String genre = null;
			
			do{
				System.out.print("Ingrese genero --> ");
				genre = input.next();
				System.out.println();
				if(!(genreList.findGenre(genre))){
					System.out.println("Favor de ingresar genero valido!!");
				}
			}while(!(genreList.findGenre(genre)));
			input.nextLine();
			System.out.print(" Plot --> ");
			String plot = input.nextLine();
			System.out.println();
			System.out.print(" Director --> ");
			String director = input.nextLine();
			System.out.println();
			System.out.print(" Year -->");
			int year = input.nextInt();
			System.out.println();
			
			Movie  movie= new Movie(title,genre,plot,list,director,year);
			
			try{
				System.out.println("Sending new movie");
				this.onStream.writeObject(movie);
				this.onStream.flush();
			}catch(Exception e){
				System.out.println("Error sending new Movie");
				return;
			}
			code = 0;
			 try{
				 code = this.inStream.readInt();
			 }catch(IOException e){
				 e.printStackTrace();
				 return;
			 }
			 if(code == Codes.OK){
				 System.out.println("La pelicula "+title+" ha sido Añadido!");
			 }else{
				 System.out.println("ERROR al añadir genero");
				 return;
			 }
	 }
	 else{
		 System.out.println("Error al recivir OK");
		 return;
	 }
}
private void ListGen(){
	GenreList genreList = new GenreList();
	try{
		 this.onStream.writeInt(Codes.LIST_GENRE);
		 this.onStream.flush();
	 }catch(IOException e){
		 e.printStackTrace();
		 return;
	 }
	
	try{
		genreList = (GenreList)this.inStream.readObject();
	}catch(Exception e){
		e.printStackTrace();
		return;
	}
	genreList.displayGenreList();
	
	}
private void ListMov(){
	GenreList genreList = new GenreList();
	try{
		 this.onStream.writeInt(Codes.LIST_GENRE);
		 this.onStream.flush();
	 }catch(IOException e){
		 e.printStackTrace();
		 return;
	 }
	
	try{
		genreList = (GenreList)this.inStream.readObject();
	}catch(Exception e){
		e.printStackTrace();
		return;
	}
	genreList.displayMovieListByGenre();
}
 }
	 
	 