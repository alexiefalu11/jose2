import java.io.Serializable;

//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

ActorList class - Singly Linked List(Ordered)
SLL will be used for actors list, they will be ordered by last name
*/


/*
-In the Actor and sllNode classes, consider not using the set methods
(even though they're implemented)
-Why? Changing an actor's name without immediately changing its order 
can mess up the order
-Instead, going to do a removeActor then addActor when modifying, to make sure
all pointers and ordering are being correctly worked
*/

/////////////////////////////////////////////////////////////////////////////
//The Actor List is an ordered singly linked list
public class ActorList implements Serializable{
	private sllNode head;
	private int size;
        
        //Constructor
	public ActorList(){
		head = null;
		size = 0;
	}

	public void addActor(Actor actor){
		sllNode node = new sllNode(actor);
		
		//if the list is empty, there's only one place where the actor can go
		if(head == null){
			//node.next = head;
			head = node;
		}
		//if the new actor's name should be placed before the first node
		//or if their last name is the same, and the new actor's first name
		// should be placed before the first node
		else if(actor.lastName.compareToIgnoreCase(head.getActor().getLastName()) < 0
			|| (actor.lastName.compareToIgnoreCase(head.getActor().getLastName()) == 0 
			&& (actor.firstName.compareToIgnoreCase(head.getActor().getFirstName()) < 0))){
			node.setNext(head);
                        head = node;
		}
		//if the new actor's name is already on the actor's list, in the first place
		else if(actor.lastName.compareToIgnoreCase(head.getActor().getLastName()) == 0
			&& actor.firstName.compareToIgnoreCase(head.getActor().getFirstName()) == 0){
			//tell the user that it already exists, don't add a new entry
			System.out.println(actor.firstName + " " + actor.lastName
				+ " is already on the actors' list for this movie.");
                        return;
		}
		//if the new actor's name doesn't go to the first node, gotta circle through nodes
		else{
			//we'll need 2 temporary nodes: to check if new actor goes between these nodes
			sllNode preNode = head; //temp node is pointing at the first node or to null(if empty)
			sllNode postNode = head.getNext();
                        
                        boolean added = false;

			while(postNode != null){ //while the link's end hasn't been reached
				//if the new actor's name goes before the next node
				if(actor.lastName.compareToIgnoreCase(postNode.getActor().getLastName()) < 0
					|| (actor.lastName.compareToIgnoreCase(postNode.getActor().getLastName()) == 0 
					&& (actor.firstName.compareToIgnoreCase(postNode.getActor().getFirstName()) < 0))){
						preNode.setNext(node);
						node.setNext(postNode);
                                                added = true;
						break;
				}//if
				//if the new actor's name matches the next node's
				else if(actor.lastName.compareToIgnoreCase(postNode.getActor().getLastName()) == 0
					&& actor.firstName.compareToIgnoreCase(postNode.getActor().getFirstName()) == 0){
					//tell the user that it already exists, don't add a new entry
					System.out.println(postNode.getActor().getFirstName() + " " + postNode.getActor().getLastName()
						+ " is already on the actors' list for this movie.");
                                        return;
				}
				//if new actor's name goes is "bigger" than the next node's
				else{
					preNode = postNode;
					postNode = postNode.getNext();
				}
			}//while
                        //if new actor's name goes in the last node
			if(!added){
				preNode.setNext(node);
                        }
		}//else

		size++;
                System.out.println(node.getActor().getFirstName() + " " + node.getActor().getLastName()
                        + " has been added to the actor's list for this movie.");
	}//addActor
        
        public boolean removeActor(Actor actor){
            boolean removed = false;
            sllNode node = head;
            
            //if the list is empty, actor can't be removed because there are no actors
            if(node == null){
                System.out.println("*List is empty, nothing removed*");
                return removed;
            }
            //if the list is populated, go through it
            else{
                //if the actor being removed is the first actor
                if(node.getActor().getFirstName().equalsIgnoreCase(actor.firstName) &&
                        node.getActor().getLastName().equalsIgnoreCase(actor.lastName)){
                    removed = true;
                    --size;
                    //if after removing, the list would be empty, point the head to null
                    if(removed && size == 0){
                        head = null;
                    }
                    else{
                        head = head.getNext();
                    }
                }
                //otherwise loop through the list
                else{
                    do{
                    if(node.getNext().actor.getFirstName().equalsIgnoreCase(actor.firstName) &&
                            node.getNext().actor.getLastName().equalsIgnoreCase(actor.lastName)){
                            sllNode postNode = node.getNext();
                            node.setNext(postNode.getNext());
                            size--;
                        removed = true;
                        }
                    else{
                        node = node.getNext();
                    }
                }while(!removed && node != null);
                }//else
            }//else
            if(!removed){
                System.out.println("*" + actor.firstName + " " + actor.lastName + " not found, nothing removed*");
            }
            else if(removed){
                        System.out.println(actor.firstName + " " + actor.lastName + " has been removed");
            }
            return removed;
        }//removeActor
        
        public boolean modifyActor(Actor oldActor, Actor newActor){
            if(removeActor(oldActor)){
                addActor(newActor);
                return true;
            }
            else{
                return false;
            }
        }//modifyActor
        
        public void displayList(){
            System.out.println("Actor List for this movie(sorted alphabetically by last name): ");
            //TODO: CHECK IF INSTEAD OF MANUALLY TYPING "this movie" INSTEAD THE MOVIE TITLE CAN BE GOTTEN THROUGH INHERITANCE
            //if the list is empty
            if(size == 0){
                System.out.println("*List is empty*");
                return;
            }/*
            //if the list has only one actor
            if(head.getNext() == null){
                System.out.println("- " + head.actor.getLastName() + ", " + head.actor.getFirstName());
                return;
            }*/
            //circle through the list
            sllNode node = head;
            
            do{
                System.out.println("- " + node.actor.getLastName() + ", " + node.actor.getFirstName());
                node = node.getNext();
            }while(node != null);
        }//displayList
        
        public void clearList(){
            this.head.setNext(null);//was having problems using just this.head=null so added this to make sure the head isn't pointing at anything
            this.head = null;
            this.size = 0;
        }
}