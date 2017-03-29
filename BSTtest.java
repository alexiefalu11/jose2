/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testingproject;

/**
 *
 * @author Jose
 */
public class BSTtest {
    static GenreList genreList = new GenreList();
    public static void main(String[] args){
        
        genreList.displayGenreList();
        genreList.displayMovieListByGenre();
        fillWithData();
        genreList.displayMovieListByGenre();
        if(genreList.findGenre("Sci-Fi") == true){
            System.out.println("CORRECT! 'Sci-Fi' was found in the genreList");
        }
        else{
            System.out.println("Shiiiiet, Sci-Fi not found CHECK findGenre METHOD when a genre should be found");
        }
        if(genreList.findGenre("Comedy") == true){
            System.out.println("CORRECT! 'Comedy' was found in the genreList");
        }
        else{
            System.out.println("Shiiiiet, Comedy not found CHECK findGenre METHOD");
        }
        
       if(genreList.isEmpty() == true){
           System.out.println("Shiiiiet, list appears to be empty, check isEmpty() method");
       }
       else{
           System.out.println("CORRECT! List isn't empty");
       }
        
        if(genreList.locateGenre("Drama") == null){
            System.out.println("CORRECT! Drama shouldn't exist");
        }
        else{
            System.out.println("ERROR! check locateGenre, when a genre doesn't exist");
        }
        
        if(genreList.locateMovie("Star Wars") == null){
            System.out.println("ERROR! check locateMovie when a movie exists");
        }
        else{
            System.out.println("CORRECT! Star Wars does exist");
        }
        
        if(genreList.locateMovie("Cenicienta") != null){
            System.out.println("ERROR! check locateMovie when a movie doesn't exists");
        }
        else{
            System.out.println("CORRECT! Cenicienta doesn't exist");
        }
        
        if(genreList.locateMovie("How I met Your Mother") == null){
            System.out.println("ERROR! check locateMovie when a movie exists on the first genre");
        }
        else{
            System.out.println("CORRECT! HIMYM exists on the first genre");
        }
        
        if(genreList.locateMovie("Forrest Gump") == null){
            System.out.println("ERROR! check locateMovie when a movie exists on the first genre");
        }
        else{
            System.out.println("CORRECT! what the fuck, why did I put Forrest Gump as 'Comedy'");
        }
    }
    
    private static void fillWithData(){
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
