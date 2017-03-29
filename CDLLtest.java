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
public class CDLLtest {
    static MovieList movieList = new MovieList();
    
    public static void main(String args[]){
        fillWithData();
        movieList.displayList();
        if(movieList.locateMovie("Star Wars") == null){
            System.out.println("error. Locating 'Star Wars' returned NULL");
        }
        else{
            System.out.println("Located 'Star Wars'");
        }
        movieList.removeMovie("Star Wars");
        if(movieList.locateMovie("Star Wars") != null){
            System.out.println("Error. Locating Star Wars after deletion returned a pointer");
        }
        else{
            System.out.println("Deleted 'Star Wars' so couldn't locate it");
        }
        
        movieList.displayList();
        
        fillWithData();
        movieList.displayList();
        System.out.println("Reached end without problems");
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
        
        movieList.addMovie(StarWars);
        movieList.addMovie(HYMIM);
        movieList.addMovie(ForrestGump);
    }
}