import java.io.Serializable;

//package testingproject;

public class cdllNode implements Serializable{
    public Movie movie;
    public cdllNode next;
    public cdllNode previous;

    //Constructors
    public cdllNode(){
        this.movie = null;
        this.next = null;
        this.previous = null;
    }
    public cdllNode(Movie movie, cdllNode next, cdllNode previous){
        this.movie = movie;
        this.next = next;
        this.previous = previous;
    }
    
    //Get methods
    public Movie getMovie(){return movie;}
    public cdllNode getNext(){return next;}
    public cdllNode getPrevious(){return previous;}

    ///Set methods
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setNext(cdllNode next) {
        this.next = next;
    }
    public void setPrevious(cdllNode previous) {
        this.previous = previous;
    }  
}
