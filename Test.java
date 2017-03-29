import java.util.Scanner;

public class Test {
	
	public static void main(String []args){
		GenreList genreList = new GenreList();
		fillWithData(genreList);
		int select = 0;
		do{
			select = Menu();
				switch(select){
					case 1:
						NewGen(genreList);
						break;
					case 2:
						NewMov(genreList);
						break;
					case 3:
						ModMov(genreList);
						break;
					case 4:
						ListGen(genreList);
						break;
					case 5:
						ListMov(genreList);
						break;
					case 6:
						ListMovGen(genreList);
						break;
					case 7:
						SeaMov(genreList);
						break;
					case 8:
						System.out.println(" Bye Bye");
				}
		}while(select != 8);
	}
	static int Menu(){
		 
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
		 select = input.nextInt();
		 
		 return select;
	 }
	static void NewGen(GenreList genreList){
		
		String title;
		Scanner input = new Scanner(System.in);
		System.out.println("********Añadiendo nuevo Genero********");
		System.out.println("Nuevo Genero --> ");
		title = input.nextLine();
		Genre genre = new Genre(title);
		genreList.addGenre(genre);
		System.out.println("EL Genero "+title+" ha sido Añadido!"); 
		input.close();
	}
	
	static void NewMov(GenreList genreList){
/*	static Scanner input = new Scanner(System.in);	
	static ActorList actorList = new ActorList();
	
	
	} */ 
	Scanner input = new Scanner(System.in);
	ActorList actorList = new ActorList();
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
	ListGen(genreList);
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
	genreList.addMovie(movie);
//	input.close();
	}
	static void ModMov(GenreList genreList){
		int select =0;
		Scanner input = new Scanner(System.in);
		do{
		System.out.println("Ingrese titulo para modificar --> ");
		String title = input.nextLine();
		genreList.showMovie(title);
			System.out.println("Are you sure want to modify "+title+" ?");
			System.out.println("[1-Yes]/[2-No] --> ");
			select = input.nextInt();
			if(select==1){
				genreList.deleteMovie(title);
				int count =1, select2 = 0;
				System.out.println("********Añadir Actores********");
				ActorList list = new ActorList();
				do{
					System.out.println("1 - Add Actor");
					System.out.println("2 - Exit");
					select2 = input.nextInt();
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
					
				}while(select2 != 2);
				ListGen(genreList);
				String genre = null;
				
				do{
					System.out.println("Ingrese genero --> ");
					genre = input.next();
					if(!(genreList.findGenre(genre))){
						System.out.println("Favor de ingresar genero valido!!");
					}
				}while(!(genreList.findGenre(genre)));
				System.out.print(" Plot --> ");
				String plot = input.nextLine();
				System.out.print(" \nDirector --> ");
				String director = input.nextLine();
				System.out.print(" \nYear -->");
				int year = input.nextInt();
				System.out.println();
				Movie  movie= new Movie(title,genre,plot,list,director,year);
				genreList.addMovie(movie);
				select = 2;
			}
		}while(select != 2);
		
	}
	
	static void ListGen(GenreList genreList){
		genreList.displayGenreList();
	}
	static void ListMov(GenreList genreList){
		genreList.displayMovieListByGenre();
	}
	static void ListMovGen(GenreList genreList){
		
		System.out.println("-----------Categoria de Generos-----------");
		ListGen(genreList);
		System.out.println("Ingrese genero para buscar --> ");
		Scanner input = new Scanner(System.in);
		String genre = input.next();
		genreList.showMovieForGenre(genre);
		
	}
	static void SeaMov(GenreList genreList){
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese titulo de pelicula --> ");
		String movie = input.nextLine();
		genreList.showMovie(movie);
	}
	private static void fillWithData(GenreList genreList){
		Genre genre = new Genre("Comedy");
		genreList.addGenre(genre);
		Genre genre2 = new Genre("Sci-Fi");
		genreList.addGenre(genre2);
        Actor ObiWan = new Actor("Obi", "Wan");
        Actor LukeSkywalker = new Actor("Luke", "Skywalker");
        Actor PrincessLeia = new Actor("Princess", "Leia");
        ActorList swActorList = new ActorList();
        swActorList.addActor(ObiWan);
        swActorList.addActor(LukeSkywalker);
        swActorList.addActor(PrincessLeia);
        String swTitle = "Star Wars";
        String scifi = "Sci-Fi";
        String swPlot = "A long time ago, in a galaxy far, far away...";
        String GeorgeLucas = "George Lucas";
        Movie StarWars = new Movie(swTitle, scifi, swPlot, swActorList, GeorgeLucas, 1979);
        
        Actor TedMosby = new Actor("Ted", "Mosby");
        Actor BarneyStinson = new Actor("Barney", "Stinson");
        Actor RobinScherbatsky = new Actor("Robin", "Scherbatsky");
        Actor TheMom = new Actor("The", "Mom");
        ActorList hymimActorList = new ActorList();
        hymimActorList.addActor(TedMosby);
        hymimActorList.addActor(BarneyStinson);
        hymimActorList.addActor(RobinScherbatsky);
        hymimActorList.addActor(TheMom);
        String hymimTitle = "How I Met Your Mother";
        String comedy = "Comedy";
        String hymimPlot = "Something to do with a yellow umbrella, a blue french horn and falling in love at the wrong time";
        String Pam = "Pamela Fryman";
        Movie HYMIM = new Movie(hymimTitle, comedy, hymimPlot, hymimActorList, Pam, 2005);
        
        Actor TomHanks = new Actor("Tom", "Hanks");
        Actor RobinWright = new Actor("Robin", "Wright");
        ActorList fgActorList = new ActorList();
        fgActorList.addActor(TomHanks);
        fgActorList.addActor(RobinWright);
        String fgTitle = "Forrest Gump";
        String fgPlot = "RUN FORREST, RUN! Also, shrimp.";
        String RobertZemeckis = "Robert Zemeckis";
        Movie ForrestGump = new Movie(fgTitle, comedy, fgPlot, fgActorList, RobertZemeckis, 1994);   
        
        /*Genre SciFi = new Genre(scifi);
        genreList.addGenre(SciFi);
        
        Genre Comedy = new Genre(comedy);
        genreList.addGenre(Comedy);*/  
        
        genreList.addMovie(StarWars);
        genreList.addMovie(HYMIM);
        genreList.addMovie(ForrestGump);
    }
}
