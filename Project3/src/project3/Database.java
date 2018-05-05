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
//     // while(current != null) 
//     // {
//         
////         Person temp = (Person) current.getDatum(); //gets the current's datu
////         String personBlood = temp.getBloodType(); //get bloodtyp from the datum
////         if (personBlood.equals(bloodType)) 
////         {count++; } //counts the amount of people who have the same bloodtype
////         current = current.getNext(); //gets the next node
//  //    }
//      Person[] bloodMatches = new Person[count]; //array created for the matching bloodtypes
//      count = 0; //counter back at 0
//      current =  personList.getFirst(); //current is now back at the top of the list
//      while(current != null) //while we're not at the end of the list
//      {
// //        Person temp = (Person) current.getDatum(); //get the datum from the current node
// //        String personBlood = temp.getBloodType(); //gets the bloodtype from the datum
////         if (personBlood.equals(bloodType)) //checks to see if the bloodtypes are equal
//         {
////            bloodMatches[count] = temp; //adds the match to the array
//            count++; //adds to counter
//         } 
// //        current = current.getNext(); //goes to the next node in the list
//      }
//     
//      return bloodMatches; //returns the array of donors who match bloodtypes
//   }//search

   

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
        System.out.print("No user with this ID\n"); //lets user know no user found
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
       int index = personList.indexOf(personList.getLast()); //defaults index to the end of list
       for(Person current: personList){ //goes through the list until at end
           if(current.getPersonID().equals(splice.getPersonID())){ //if the IDs are equal
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
                       System.out.print(current.getDonationList());
                       return; 
               }
               
               for(Donation currentDonation: current.getDonationList()){
                   int index = current.getDonationList().indexOf(current.getDonationList().getLast()); 
                   if(splice.getDate().compareTo(currentDonation.getDate()) > 0){
                       index = current.getDonationList().indexOf(currentDonation); //gets the index
                       current.getDonationList().add(index, splice); //adds at index
                       System.out.print(current.getDonationList());
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
       if(index == -100){return;} //check for no user found
       if(personList.get(index).getDonationList().isEmpty()){ //if no donations
           System.out.println("No donation dates on file."); //tells user
       }
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

    
} //end Database class