import java.io.Serializable;

//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

MovieList class - Circular Doubly Linked List(Ordered)
This list will be used for the movie's data: title, genre, plot, list of actors, director, year
*/

/////////////////////////////////////////////////////////////////////////////
public class MovieList implements Serializable{
    private cdllNode head;
    private cdllNode tail;
    private int size;

    //Constructor
    public MovieList(){
        head = null;
        tail = null;
        size = 0;
    }
    
/////////////////////////////////////////////////////////////////////////////
    public void addMovie(Movie movie){
        cdllNode movieNode = locateMovie(movie.getMovieTitle());
            if(movieNode != null){
            System.out.println(movie.movieTitle + " already exists.");
            return;
        }
        
        //if movie doesn't exists, proceed to add it
        cdllNode node = new cdllNode(movie, null, null);
        
        //if list is empty, add it as the only node
        if(head == null){
            node.setNext(node);
            node.setPrevious(node);
            head = node;
            tail = head;
            
            size++;
        }
        //otherwise, gotta check where it goes and then insert it
        else{
            //check if movie goes before first node
            if(movie.movieTitle.compareToIgnoreCase(head.getMovie().getMovieTitle()) < 0){
                node.setNext(head);
                node.setPrevious(tail);
                tail.setNext(node);
                head.setPrevious(node);
                head = node;
                
                size++;
            }
            //if movie goes after tail
            else if(movie.movieTitle.compareToIgnoreCase(tail.getMovie().getMovieTitle()) > 0){
                node.setNext(head);
                node.setPrevious(tail);
                tail.setNext(node);
                head.setPrevious(node);
                tail = node;
                
                size++;
            }
            //if movie goes at any other point
            else{
                //we'll need 2 temporary nodes to check if new movie goes between these nodes
                cdllNode preNode = head;
                cdllNode postNode = head.getNext();
                
                //circle through the list until the end is reached
                while(preNode.getNext() != head){
                    if(movie.getMovieTitle().compareToIgnoreCase(postNode.getMovie().getMovieTitle()) < 0){
                        node.setNext(postNode);
                        node.setPrevious(preNode);
                        preNode.setNext(node);
                        postNode.setPrevious(node);
                        
                        size++;
                        return;
                    }
                    else{
                        preNode = preNode.getNext();
                        postNode = preNode.getNext();
                    }
                }
            }
        }
    }//addMovie
    
    public void displayList(){
        //check if list is empty
        if(head == null){
            System.out.println("Movie list is empty");
            return;
        }
        
        System.out.println("Movie List: ");

        //if list has only 1 movie
        if(head == tail){
            System.out.println("- " + head.getMovie().getMovieTitle());
        }
        else{
            cdllNode tempNode = head;
            //circle through the list
            while(tempNode.getNext() != head){
                System.out.println("- " + tempNode.getMovie().getMovieTitle());
                tempNode = tempNode.getNext();
            }
            
            System.out.println("- " + tempNode.getMovie().getMovieTitle());
        }
    }//displayList
    
    public void displayListWithInfo(){
        //check if list is empty
        if(head == null){
            System.out.println("Movie list is empty");
            return;
        }
        
        System.out.println("Movie List: ");

        //if list has only 1 movie
        if(head == tail){
            System.out.println("- " + head.getMovie().getMovieTitle() + "("
            + head.getMovie().getYear() + ") by " + head.getMovie().getDirector());
        }
        else{
            cdllNode tempNode = head;
            //circle through the list
            while(tempNode.getNext() != head){
                System.out.println("- " + tempNode.getMovie().getMovieTitle() + "("
                + tempNode.getMovie().getYear() + ") by " + tempNode.getMovie().getDirector());
                tempNode = tempNode.getNext();
            }
            
            System.out.println("- " + tempNode.getMovie().getMovieTitle() + "("
                + tempNode.getMovie().getYear() + ") by " + tempNode.getMovie().getDirector());
        }
    }//displayListWithInfo
    
    public void removeMovie(String movieTitle){
        cdllNode movieNode = locateMovie(movieTitle);
        
        //if movie isn't found, it can't de deleted, tengo hambre
        if(movieNode == null){
            return;
        }
        //if there's only 1 movie on the list
        else if(size == 1){
            head = null;
            tail = null;
            size --;
            return;
        }
        //if node is the head node
        else if(movieNode == head){
            head = head.getNext();
            head.setPrevious(tail);
            tail.setNext(head);
            size--;
            return;
        }
        //if node is the tail node
        else if(movieNode == tail){
            tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail);
            size--;
            return;
        }
        else{
            movieNode.getPrevious().setNext(movieNode.getNext());
            movieNode.getNext().setPrevious(movieNode.getPrevious());
            movieNode.setPrevious(null);
            movieNode.setNext(null);
            System.out.println(movieTitle + " has been removed.");
            
            size--;
        }
    }//removeMovie
    
    public cdllNode locateMovie(String movieTitle){
        
        //if list is empty, there's no movie, so no movie can be found
        if(head == null){
            return null;
        }
        //if list has only 1 item, check if it's that item
        else if(head == tail){
            if(movieTitle.compareToIgnoreCase(head.getMovie().getMovieTitle()) == 0){
                return head;
            }
        }
        //otherwise iterate through the list
        else{
            //gonna use a temp node to circle through the list
            cdllNode tempNode = head;
            //iterate until the head is reached again
            while(tempNode.getNext() != head){
                if(movieTitle.compareToIgnoreCase(tempNode.getMovie().getMovieTitle()) == 0){
                    return tempNode;
                }
                else{
                    tempNode = tempNode.getNext();
                }
            }
            if(movieTitle.compareToIgnoreCase(tempNode.getMovie().getMovieTitle()) == 0){
                return tempNode;
            }
        }
        return null;
    }//findMovie
    
    public void showMovieInfo(String movieTitle){
        cdllNode movieNode = locateMovie(movieTitle);
        
        //first check if movie exists
        if(movieNode == null){
            System.out.println("Movie doesn't exist");
        }
        else{
            System.out.println("Movie Title: " + movieNode.getMovie().getMovieTitle());
            System.out.println("Genre: " + movieNode.getMovie().getGenre());
            System.out.println("Plot: " + movieNode.getMovie().getPlot());
            movieNode.getMovie().getActorList().displayList();
            System.out.println("Director: " + movieNode.getMovie().getDirector());
            System.out.println("Release Year: " + movieNode.getMovie().getYear());
        }
    }//showMovieInfo
    
    //Will need to take into consideration 2 things: 
    //when the new movie's genre is the same as before(this means that the new movie stays in the current genre's movielist)
    //when the new movie's genre is different:
        //here gotta check if the new movie's genre exists or not.
    public void modifyMovie(String movieTitle){
        
    }//modifyMovie
}