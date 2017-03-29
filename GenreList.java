//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

GenreList class - Binary Search Tree
This list will hold each genre's title and the movielist for each genre
*/

import java.util.Scanner;
import java.io.*;

public class GenreList implements Serializable{
    private bstNode root;
    
    //Constructor
    public GenreList(){
        root = null;
    }
    
/////////////////////////////////////////////////////////////////////////////
    public boolean isEmpty(){
        return root == null;
    }
    
    //to add a Genre we will be using two methods, first one will receive the Genre's data and second one will receive a node
    //this second method will make use of the node and recursion to place the new node in the correct place
    public void addGenre(Genre genre){
        root = addGenre(root, genre);
    }//void addGenre
    private bstNode addGenre(bstNode node, Genre genre){
        //if the current node is null, it means this is where the genre should be add it, so insert it there
        if(node == null){
            node = new bstNode(genre);
        }
        //otherwise, gotta compare the new genre's title to the one at each side of the current's node
        else{
            if(genre.genreTitle.compareToIgnoreCase(node.getGenre().getGenreTitle()) < 0){
                node.left = addGenre(node.left, genre);
            }
            else if(genre.genreTitle.compareToIgnoreCase(node.getGenre().getGenreTitle()) > 0){
                node.right = addGenre(node.right, genre);
            }
            else{
                System.out.println(genre.genreTitle + " is already in the database");
                return null;
            }
        }
        return node;
    }//bstNode addGenre
    
    //don't need to implement a removeGenre
    //to find a genre, 2 methods will be used, again recursion will be used to locate
    public boolean findGenre(String genreTitle){
        return findGenre(root, genreTitle);
    }//findGenre
    private boolean findGenre(bstNode node, String genreTitle){
        boolean found = false;
        
        //iterate while the node isn't empty and the genre hasn't been found
        while((node != null) && !found){
            if(genreTitle.compareToIgnoreCase(node.genre.genreTitle) < 0){
                node = node.getLeft();
            }
            else if(genreTitle.compareToIgnoreCase(node.genre.genreTitle) > 0){
                node = node.getRight();
            }
            else{
                found = true;
                break;
            }
            found = findGenre(node, genreTitle);
        }
        return found;
    }//findGenre
    
    //gonna add a locateGenre that works like findGenre, but returns a bstNode instead
    public bstNode locateGenre(String genreTitle){
        return locateGenre(root, genreTitle);
    }
    private bstNode locateGenre(bstNode node, String genreTitle){
        boolean found = false;
        
        //iterate while the node isn't empty and the genre hasn't been found
        while((node != null) && !found){
            if(genreTitle.compareToIgnoreCase(node.genre.genreTitle) < 0){
                node = node.getLeft();
            }
            else if(genreTitle.compareToIgnoreCase(node.genre.genreTitle) > 0){
                node = node.getRight();
            }
            else{
                found = true;
                break;
            }
            locateGenre(node, genreTitle);
        }
        return node;
    }//private locate genre
    
    //these methods will display each genre in the tree, in alphabetical order
    public void displayGenreList(){
        System.out.println("Genres:");
        displayGenreList(root);
    }
    private void displayGenreList(bstNode node){
        if(node != null){
            displayGenreList(node.getLeft());
            System.out.println("- " + node.getGenre().getGenreTitle());
            displayGenreList(node.getRight());
        }
    }//displayGenreList
    
    //to display the movies for each genre, the process is similar to displaying each genre, 
    //but we have to add some instructions to have the movie list displayed for each genre
    public void displayMovieListByGenre(){
        System.out.println("Movies by Genre:");
        displayMovieListByGenre(root);
    }
    private void displayMovieListByGenre(bstNode node){
        if(node != null){
            displayMovieListByGenre(node.getLeft());
            System.out.println("");
            System.out.println("Genre: " + node.getGenre().getGenreTitle());
            node.getGenre().getMovieList().displayListWithInfo();
            displayMovieListByGenre(node.getRight());
        }
    }//private displayMovieListByGenre
    
    //gonna need an addMovie in this class to sort the movie into the right genre
    public void addMovie(Movie movie){
        //locate genre
        bstNode node = locateGenre(movie.getGenre());
        //if genre exists, add the movie to that genre
        if(node != null){
            node.getGenre().getMovieList().addMovie(movie);
        }
        //if genre doesn't exist, ask user if he wants to add the genre
        else{
            System.out.println("Genre doesn't exist. Type 'Y' or 'YES' to add it to list of genres and to add the movie.");
            Scanner scanner = new Scanner(System.in);
            
            String input = scanner.nextLine();
            //if user wants the genre to be added
            if(input.compareToIgnoreCase("yes") == 0 ||
               input.compareToIgnoreCase("y") == 0){
                //add the genre and then add the movie
               /* Genre newGenre = new Genre(movie.getGenre());
                addGenre(newGenre);
                addMovie(movie); */
            	String genre = scanner.next();
            	Genre newGenre = new Genre(genre);
            	movie.setGenre(genre);
            	addMovie(movie);
            }
            else{
            	System.out.println("Please select a valid genre!");
            	return;
            }
        }
    }//addMovie
    
    //implementation of a method that will traverse through all genres' movie lists
    //looking for a specific movie
    public bstNode locateMovie(String movieTitle){
        return locateMovie(root, movieTitle, null);
    }
    private bstNode locateMovie(bstNode node, String movieTitle, bstNode prevNode){
        if(node != null){
            node = locateMovie(node.getLeft(), movieTitle, node);
            //look for movie in the current genre's movielist
            //if it's found, return the current node
            if(node.getGenre().getMovieList().locateMovie(movieTitle) != null){
                return node;
            }
            else{
            node = locateMovie(node.getRight(), movieTitle, node);
            }
            return prevNode;
        }
       return prevNode;
    }//locateMovie
    
    //method that shows all movies for a specific genre
    public void showMovieForGenre(String genreTitle){
        bstNode node = locateGenre(genreTitle);
        if(node == null){
            System.out.println("Genre doesn't exist");
        }
        else{
            node.getGenre().getMovieList().displayListWithInfo();
        }
    }
    public void showMovie(String movieTitle){
    	bstNode node = locateMovie(movieTitle);
    	if(node == null){
            System.out.println("Movie doesn't exist");
        }
        else{
            node.getGenre().getMovieList().showMovieInfo(movieTitle);
            
        }
    }
    public void deleteMovie(String movieTitle){
    	bstNode node = locateMovie(movieTitle);
    	node.getGenre().getMovieList().removeMovie(movieTitle);
    }
}