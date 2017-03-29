import java.io.Serializable;

//package testingproject;
/*
José M. Flores | CPEN 457 | 2017-02

Actor class
Simple class with 2 attributes: First and Last name(Strings)
*/
public class Actor implements Serializable{
    public String firstName;
    public String lastName;

    //Constructors
    public Actor(){
        this.firstName = null;
        this.lastName = null;
    }

    public Actor(String firstName, String lastName){
	this.firstName = firstName;
	this.lastName = lastName;
    }

    //Get methods
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}

    //Set methods
    public void setFirstName(String firstName){
	this.firstName = firstName;
    }
    public void setLastName(String lastName){
	this.lastName = lastName;
    }
}