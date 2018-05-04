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
   
   public void insertDonor(Person splice){
       if(personList.isEmpty()) //checks if empty
            {personList.addFirst(splice);}
       boolean last = true; 
       int index = personList.indexOf(personList.getLast());
       for(Person current: personList){
           if(current.getPersonID().equals(splice.getPersonID())){
               System.out.println("This user ID already exists.");
               return; 
           }
           else if(splice.getLastName().compareTo(current.getLastName()) < 0)
           {
               index = personList.indexOf(current);
               personList.add(index, splice);
               return; 
           }
           else if(splice.getLastName().compareTo(current.getLastName()) == 0)
           {
               if(splice.getFirstName().compareTo(current.getFirstName()) < 0)
               {
                   index = personList.indexOf(current); 
                   personList.add(index, splice);
                   return; 
               }
           }
       }
       personList.addLast(splice); 
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