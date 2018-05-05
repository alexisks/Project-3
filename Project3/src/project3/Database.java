package project3;

/*************************************************************/
/* Alex Schoonover                                           */
/* Login ID: scho9419                                        */
/* CS-102, Winter 2018                                       */
/* Programming Assignment 3                                  */
/* Database Class: Collection of Person and Donation objects */
/*************************************************************/

import java.time.LocalDate;
import java.util.LinkedList;
import static javafx.scene.input.KeyCode.O;
public class Database
{ //Database class
   public LinkedList<Person> personList; 
   
   public Database()
   {//Database
     personList = new LinkedList();  
   }//end Database
   

/*************************************************************/
/*Method: searchBloodType                                    */
/*Purpose: Find all peope with a specific blood type         */
/*Parameters:                                                */
/*            : String   -   that include ths bloodtype      */
/*      Return: void -   prints matches                      */
/*************************************************************/
   public void searchBloodType(String bloodType) 
   {//search
      int count = 0; //created counter
      for(Person current: personList){
         if (current.getBloodType().equals(bloodType))//if bloodtypes match 
         System.out.println(current); //prints matches
      }
   }

/******************************************************************/
/*Method: searchID                                                */
/*Purpose: Find all instances of the ID in the donation txt file  */
/*Parameters:                                                     */
/*            : String   -will be user's ID search                */
/*      Return: int      -will contain the index of match        */
/*                       -returns -100 if no match found          */                      
/******************************************************************/
  
    public int searchID(String ID){    
       int index = personList.indexOf(personList.getLast()); //defaults index to end
        for(Person current: personList){ //goes through person list
            if(current.getPersonID().equals(ID)){ //if the IDs match
                index = personList.indexOf(current); //gets index of match
                System.out.print(current); //prints tbe person
                return index; //returns the index of match
            }
                 
        }
        
        return -100;  //for use in printDonation() to avoid errors

    }//end searchID   
   /*************************************************************/
/*Method: insertDonor()                                         */
/*Purpose: adds a donor to the linked list in sorted order      */
/*Parameters:                                                   */
/*            : Person object - object to be added to the list  */
/*      Return: void                                            */
/*************************************************************/
   public void insertDonor(Person splice){
       if(personList.isEmpty()) //checks if empty
            {personList.addFirst(splice); return; } //adds donor to the beginning of the list
       for(Person current: personList){
           int check;
           check = searchID(splice.getPersonID());
           if(check != -100){
               System.out.println("Error: ID already being used. Try again with new number"); 
               return; 
           }
               
       }
       int index = personList.indexOf(personList.getLast()); //defaults index to the end of list
       for(Person current: personList){ //goes through the list until at end
           if(current.getPersonID().compareTo(splice.getPersonID()) == 0){ //if the IDs are equal
               System.out.println("This user ID already exists."); //tells user issue
               return; //leaves method
           }
           else if(splice.getLastName().compareTo(current.getLastName()) < 0)
           {                                          //if last name goes before
               index = personList.indexOf(current); //gets the index
               personList.add(index, splice); //adds at index
               return; //leaves method
           }
           else if(splice.getLastName().compareTo(current.getLastName()) == 0)
           { //last names are equal
               if(splice.getFirstName().compareTo(current.getFirstName()) < 0)
               { //if this first name goes before the other
                   index = personList.indexOf(current); //gets index
                   personList.add(index, splice); //adds person at index
                   return; //leaves method
               }
           }
       }
       personList.addLast(splice); //defaults person at the end
   }
   
/*************************************************************/
/*Method: insertDonation()                                      */
/*Purpose: adds a donor to the linked list in sorted order      */
/*Parameters:                                                   */
/*            : Person object - object to be added to the list  */
/*      Return: void                                            */
/*************************************************************/
   
   public void insertDonation(Donation splice){
       
       for(Person current: personList){ //goes through the list until at end
           if(splice.getID().compareTo(current.getPersonID()) == 0){
               if(current.getDonationList().isEmpty()){
                       current.getDonationList().addFirst(splice);
                       return; 
               }
               
               for(Donation currentDonation: current.getDonationList()){
                   int index = current.getDonationList().indexOf(current.getDonationList().getLast()); 
                   if(splice.getDate().compareTo(currentDonation.getDate()) > 0){
                       index = current.getDonationList().indexOf(currentDonation); //gets the index
                       current.getDonationList().add(index, splice); //adds at index
                       return; 
                   }
                     
               }//end for loop
                   
           }
       }
   }

/*************************************************************/
/*Method: print                                              */
/*Purpose: print the results of a program                    */
/*Parameters:                                                */
/*            : LinkedList                                  */
/*      Return: Nothing. But will print necassary information*/
/*************************************************************/
   public void print() {
       for(Person current: personList){
           System.out.print(current.toString());//goes through the list and prints the Person object
               if(current.getDonationList().isEmpty() == false){ //if list is not empty
               Donation test = current.getDonationList().getFirst(); //get first in list
               System.out.println("Last donation date: " + test.getDate()); //prints last donation date
               if(test.getDate() != LocalDate.MIN) //checks for valid date
               {//start if
                  if(current.eligible(test)) //if eligible to donate
                  System.out.println("Eligible: yes."); //prints yes
                  else //if not eligible to donate
                  System.out.println("Eligible: no.");//prints no
                  System.out.println(""); //prints a space
               }
               else //error check
                  System.out.println("Never donated before.\n"); //prints never donated
            }// end if
            else//if never donated
            {
               System.out.println("Last donation date: never"); //last donation date
               System.out.println("Eligible: yes."); //prints eligiblity
               System.out.println("");//prints space
            }
         //end while
    }
    
   }
   
/***************************************************************/
/*Method: printDonations()                                     */
/*Purpose: Prints the donor and all donations on file          */
/*Parameters:                                                  */
/*            : int    -The index of ID returned from searchID */
/*      Return: void   -prints donors.                         */
/***************************************************************/
   public void printDonations(int index) {//printDonations start
       if(index == -100){
           System.out.print("No user with this ID\n"); //lets user know no user found
           return;
       } //check for no user found
       if(personList.get(index).getDonationList().isEmpty()){ //if no donations
           System.out.println("No donation dates on file."); //tells user
           return;
       }
       System.out.println("Donation dates: ");
       for(Donation current: personList.get(index).getDonationList()){ //goes through donations
           System.out.println(current.getDate()); //prints donations
       }
   }

/*************************************************************/
/*Method: dateFormat                                         */
/*Purpose: Takes the text from file and converts it to a LocalDate */
/*Parameters:                                                */
/*            : String date from txt file                    */
/*      Return: LocalDate                                    */
/*************************************************************/
 
   public LocalDate dateFormat(String date)
    {
      String year = date.substring(0,4); //gets year from format
      String month = date.substring(4,6); //gets month from format
      String day = date.substring(6,8);  //gets day from format
      
      int intYear = Integer.parseInt(year);  //converts year to integer
      int intMonth = Integer.parseInt(month);//converts month to integer
      int intDay = Integer.parseInt(day); //converts day to integer
      //creates LocalDate with inputed data
      LocalDate formatDate = LocalDate.of(intYear,intMonth,intDay); 
      return formatDate; //returns new LocalDate object
    }
/*******************************************************************/
/*Method: compatibleDonors()                                       */
/*Purpose: Prints the donors that are compatible with the searched */
/*Parameters:                                                      */
/*            : int    -The index of ID returned from searchID     */
/*      Return: void   -prints donors.                             */
/*******************************************************************/
    public void compatibleDonors(String userType){
        if(userType.equals("O-"))
        {
            searchBloodType("O-");
        }
        else if(userType.equals("O+")){
            searchBloodType("O-");
            searchBloodType("O+");
        }
        else if(userType.equals("A-")){
            searchBloodType("O-");
            searchBloodType("A-");
        }
        else if(userType.equals("A+")){
            searchBloodType("O-");
            searchBloodType("O+");
            searchBloodType("A-");
            searchBloodType("A+");
        }
        else if(userType.equals("B-")){
            searchBloodType("O-");
            searchBloodType("B-");
        }
        else if(userType.equals("B+")){
            searchBloodType("O-");
            searchBloodType("O+");
            searchBloodType("B-");
            searchBloodType("B+");
        }
        else if(userType.equals("AB-")){
            searchBloodType("O-");
            searchBloodType("A-");
            searchBloodType("B-");
            searchBloodType("AB-");
        }
        else if(userType.equals("AB+")){
            searchBloodType("O-");
            searchBloodType("O+");
            searchBloodType("A-");
            searchBloodType("A+");
            searchBloodType("B-");
            searchBloodType("B+");
            searchBloodType("AB-");
            searchBloodType("AB+");
        }
        
    }//end compatibleDonors
   
} //end Database class