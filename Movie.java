import java.io.*;

//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

Movie class
Simple class with all of a movie's attributes: title, genre, plot, list of actors, director, release year
*/

public class Movie implements Serializable{
    public String movieTitle;
    public String genre;
    public String plot;
    public ActorList actorList;
    public String director;
    public int year;
    
    //Constructors
    public Movie(){
        this.movieTitle = null;
        this.genre = null;
        this.plot = null;
        this.actorList = null;
        this.director = null;
        this.year = 0;
    }
    
    public Movie(String movieTitle, String genre, String plot, ActorList actorList, String director, int year){
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.plot = plot;
        this.actorList = actorList;
        this.director = director;
        this.year = year;
    }
    
    //Get methods
    public String getMovieTitle(){return this.movieTitle;}
    public String getGenre(){return this.genre;}
    public String getPlot(){return this.plot;}
    public ActorList getActorList(){return this.actorList;}
    public String getDirector(){return this.director;}
    public int getYear(){return this.year;}
    
    //Set methods
    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setPlot(String plot){
        this.plot = plot;
    }
    public void setActorList(ActorList actorList){
        this.actorList = actorList;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setYear(int year){
        this.year = year;
    }
}