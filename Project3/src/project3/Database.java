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
/*      Return: Person[] -   array of matches                */
/*************************************************************/
   public Person[] searchBloodType(String bloodType) 
   {//search
      int count = 0; //created counter
      
      Person current = personList.getFirst(); //found the head of the linked list
      while(current != null) 
      {
         
//         Person temp = (Person) current.getDatum(); //gets the current's datu
//         String personBlood = temp.getBloodType(); //get bloodtyp from the datum
//         if (personBlood.equals(bloodType)) 
//         {count++; } //counts the amount of people who have the same bloodtype
//         current = current.getNext(); //gets the next node
      }
      Person[] bloodMatches = new Person[count]; //array created for the matching bloodtypes
      count = 0; //counter back at 0
      current =  personList.getFirst(); //current is now back at the top of the list
      while(current != null) //while we're not at the end of the list
      {
 //        Person temp = (Person) current.getDatum(); //get the datum from the current node
 //        String personBlood = temp.getBloodType(); //gets the bloodtype from the datum
//         if (personBlood.equals(bloodType)) //checks to see if the bloodtypes are equal
         {
//            bloodMatches[count] = temp; //adds the match to the array
            count++; //adds to counter
         } 
 //        current = current.getNext(); //goes to the next node in the list
      }
     
      return bloodMatches; //returns the array of donors who match bloodtypes
   }//search

   

/******************************************************************/
/*Method: searchID                                                */
/*Purpose: Find all instances of the ID in the donation txt file  */
/*Parameters:                                                     */
/*            : String                                            */
/*      Return: Node                                              */
/******************************************************************/
  
   public Node searchID(String ID)
   {  
 //     Node current = (Node) personList.getFirst(); 
       
//      while(current != null)
//      {
//         Person IdMatches = (Person) current.getDatum();
//         if(IdMatches.getPersonID().equals(ID))
//         return current; 
//         
//         else 
//         {
//            current = current.getNext(); 
//         } 
      
//      }
//      System.out.print("No user with this ID\n"); 
//      throw new NullPointerException(); 
      return null;
    }   
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
                   if(splice.getDate().compareTo(currentDonation.getDate()) < 0){
                       index = current.getDonationList().indexOf(current.getDonationList()); //gets the index
                       current.getDonationList().add(index, splice); //adds at index
                       System.out.print(current.getDonationList());
                       return; 
                   }
                   else{
                       current.getDonationList().addLast(splice); 
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
           System.out.println(current.toString());//goes through the list and prints the Person object
       }
   }
   public void print(LinkedList<Node> people)
   {//start print
         Node current = people.getFirst(); 
         Person donorTest = (Person) current.getDatum(); 
         while(current != null)
         {//while
            System.out.print((Person)current.getDatum()); 
            if(current.getDateNode() != null)
            {
               Donation test = (Donation) current.getDateNode().getDatum(); 
               System.out.println("Last donation date: " + test.getDate());
               //}
                
               if(test.getDate() != LocalDate.MIN)
               {//start if
                                    if(donorTest.eligible(test))
                  System.out.println("Eligible: yes.");
                  else
                  System.out.println("Eligible: no.");
                  System.out.println("");
               }
               else 
                  System.out.println("Never donated before.\n");
            }// end if
            else
            {
               System.out.println("Last donation date: never");
               System.out.println("Eligible: yes.");
               System.out.println("");
            }
            
            current = current.getNext(); 
         }//end while
         
      
   }// end print


/*************************************************************/
/*Method: printDonations()                                    */
/*Purpose: Prints the donor and all donations on file       */
/*Parameters:                                                */
/*            : Node                                         */
/*      Return: void                                          */
/*************************************************************/
   public void printDonations(Node item)
   {
      Node current = item;
      Person donorTest = (Person) current.getDatum(); 
      //while(current != null)
         //{//while
            System.out.print(donorTest); 
            if(current.getDateNode() != null)
            {
               Donation test = (Donation) current.getDateNode().getDatum(); 
               System.out.println("Last donation dates: ");
               while(current.getDateNode() != null)
               {
                  System.out.println(test.getDate()); 
                  current = current.getDateNode(); 
               }
            }
            else
            System.out.println("No donation dates on file."); 
         // }
             

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
      String year = date.substring(0,4);
      String month = date.substring(4,6);
      String day = date.substring(6,8); 
      
      int intYear = Integer.parseInt(year); 
      int intMonth = Integer.parseInt(month);
      int intDay = Integer.parseInt(day);
      
      LocalDate formatDate = LocalDate.of(intYear,intMonth,intDay);
      return formatDate; 
    }

    
} //end Database class