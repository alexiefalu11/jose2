import java.io.Serializable;

//package testingproject;

public class bstNode implements Serializable{
    public Genre genre;
    public bstNode left;
    public bstNode right;
    
    //Constructor
    public bstNode(){
        genre = null;
        left = null;
        right = null;
    }
    
    public bstNode(Genre genre){
        this.genre = genre;
        this.left = null;
        this.right = null;
    }
    
    //Gets
    public Genre getGenre(){return this.genre;}
    public bstNode getLeft(){return this.left;}
    public bstNode getRight(){return this.right;}
    
    //Sets
    public void setGenre(Genre genre){
        this.genre = genre;
    }
    public void setLeft(bstNode left){
        this.left = left;
    }
    
    public void setRight(bstNode right){
        this.right = right;
    }
}