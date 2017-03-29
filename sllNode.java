import java.io.Serializable;

//package testingproject;

public class sllNode implements Serializable{
	public Actor actor; //insert last name first
	public sllNode next;

	//Constructors
	public sllNode(){
		this.actor = new Actor();
		this.next = null;
	}

	public sllNode(Actor actor){
		this.actor = actor;
		this.next = null;
	}

	//Get methods
	public Actor getActor(){return this.actor;}
	public sllNode getNext(){return this.next;}

	//Set methods
	public void setActor(Actor actor){
		this.actor = actor;
	}
	public void setNext(sllNode next){
		this.next = next;
	}
}