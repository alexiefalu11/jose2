//package testingproject;

import java.util.Scanner;

public class SLLtest{
        static Scanner scanner = new Scanner(System.in);
        static ActorList actorList = new ActorList();
    public static void main(String[] args) {
        Actor luisSuarez = new Actor("Luis", "Suarez");
        
        
        /*int check = luisSuarez.firstName.compareToIgnoreCase(null);
        if(check < 0)
            System.out.println("<");
        else if(check > 0)
            System.out.println(">");
        else if(check == 0)
            System.out.println("==");
        
        int menu = 0;
        do{
        System.out.println("Choose a name to compare with " + luisSuarez.getFirstName() + " " + luisSuarez.getLastName());
        System.out.print("First name: ");*/
        int menu;
        do{
            System.out.println("Menu: ");
            System.out.println("1 - Add Actor");
            System.out.println("2 - Remove Actor");
            System.out.println("3 - Modify Actor");
            System.out.println("4 - Display List");
            System.out.println("5 - Clear List");
            System.out.println("6 - Exit");
            
            menu = scanner.nextInt();
            
            switch(menu){
                case 1: 
                    addActor();
                    break;
                case 2: 
                    removeActor();
                    break;
                case 3:
                    modifyActor();
                    break;
                case 4:
                    displayList();
                    break;
                case 5:
                    actorList.clearList();
                    break;
                default: 
                    break;
            }//switch
        }while(menu != 6);
        Actor inputName = new Actor();
        inputName.setFirstName(scanner.next());
        
        System.out.print("Last name: ");
        inputName.setLastName(scanner.next());
        
       /* String comparator = null;
        
        
        if(inputName.lastName.compareToIgnoreCase(luisSuarez.getLastName()) > 0){
            comparator = ">";
        }
        else if(inputName.lastName.compareToIgnoreCase(luisSuarez.getLastName()) < 0){
            comparator = "<";
        } 
        else if(inputName.lastName.compareToIgnoreCase(luisSuarez.getLastName()) == 0){
            if(inputName.firstName.compareToIgnoreCase(luisSuarez.getFirstName()) > 0){
                comparator = ">";
            }
            else if(inputName.firstName.compareToIgnoreCase(luisSuarez.getFirstName()) < 0){
                comparator = "<";
            }
            else if(inputName.firstName.compareToIgnoreCase(luisSuarez.getFirstName()) == 0){
                comparator = "==";
            }
        }
        
        System.out.println(inputName.firstName + " " + inputName.lastName + " " + comparator + " " + luisSuarez.firstName + " " + luisSuarez.lastName);
        
        System.out.println("Press 0 to quit");
        menu = scanner.nextInt();
        }while(menu != 0);*/
        
    }//main
    
    private static void addActor(){
        System.out.println("-Add actor-");       
        Actor actor = new Actor();
        
        System.out.println("First name:");
        actor.setFirstName(scanner.next());
        
        System.out.println("Last name:");
        actor.setLastName(scanner.next());
        
        actorList.addActor(actor);
        //System.out.println(actor.getFirstName() + actor.getLastName() + "added to list");
    }
    
    private static void removeActor(){
        System.out.println("-Remove actor-");
        Actor actor = new Actor();
        
        System.out.println("First name:");
        actor.setFirstName(scanner.next());
        
        System.out.println("Last name:");
        actor.setLastName(scanner.next());
        
        actorList.removeActor(actor);
    }
    
    private static void modifyActor(){
        System.out.println("-Modify actor-");
        Actor oldActor = new Actor();
        Actor newActor = new Actor();
        
        System.out.println("Old actor's First name:");
        oldActor.setFirstName(scanner.next());
        
        System.out.println("Old actor's Last name:");
        oldActor.setLastName(scanner.next());
        
        System.out.println("New actor's First Name:");
        newActor.setFirstName(scanner.next());
        
        System.out.println("New actor's Last Name:");
        newActor.setLastName(scanner.next());
        
        if(!actorList.modifyActor(oldActor, newActor)){
            System.out.println("Old Actor not found, add New Actor to list anyway? (y/n)");
            if(scanner.next().compareToIgnoreCase("y") == 0){
                actorList.addActor(newActor);
            }
        }
    }
    
    private static void displayList(){
        actorList.displayList();
    }
    
}//testingproject

/*class Actor{
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
}*/