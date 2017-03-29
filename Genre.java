import java.io.Serializable;

//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

Genre class
Simple class with the Genre's info: Genre Title and Movie List; used in the BST nodes
*/

public class Genre implements Serializable{
    String genreTitle;
    MovieList movieList;
    
    //Constructors
    public Genre(){
        this.genreTitle = null;
        this.movieList = null;
    }
    
    public Genre(String genreTitle){
        this.genreTitle = genreTitle;
        this.movieList = new MovieList();
    }
    
    public Genre(String genreTitle, MovieList movieList){
        this.genreTitle = genreTitle;
        this.movieList = movieList;
    }
    
    //Gets
    public String getGenreTitle(){return this.genreTitle;}
    public MovieList getMovieList(){return this.movieList;}
    
    //Sets
    public void setGenreTitle(String genreTitle){
        this.genreTitle = genreTitle;
    }
    public void setMovieList(MovieList movieList){
        this.movieList = movieList;
    }
}