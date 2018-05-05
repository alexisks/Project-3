package project3;

/************************************************************/
/* Alex Schoonover                                          */
/* Login ID: scho9419                                       */
/* CS-102, Winter 2018                                      */
/* Programming Assignment 3                                 */
/* Person Class: informatin about the people donating blood */
/************************************************************/
import java.time.LocalDate;
import java.util.LinkedList;
public class Person
{ //Person Class
   private String firstName; 
   private String lastName; 
   private String ID; 
   private String bloodType; 
   private LinkedList<Donation> donationList = new LinkedList(); 
   
   public Person(String ID, String lastName, String firstName, String bloodType){ //Person 
      this.ID = ID;
      this.lastName = lastName; 
      this.firstName = firstName; 
      this.bloodType = bloodType; 
   }//End Person

   public String getLastName(){return lastName;}
   public String getFirstName(){return firstName;}
   public String getBloodType(){return bloodType;}
   public String getPersonID(){return ID;}
   public LinkedList<Donation> getDonationList(){return donationList;}
   public void setDonationList(LinkedList<Donation> donationList){
       this.donationList = donationList;}
   
   
/****************************************************************/
/*Method: eligible                                              */
/*Purpose: Find all if a person is eligible to donate blood     */
/*Parameters:                                                   */
/*            : String                                          */
/*      Return: boolean                                         */   
/****************************************************************/
   public boolean eligible(Donation item){//start eligible
   
      LocalDate today = LocalDate.now(); //gets todays date
      LocalDate lastDate = item.getDate(); //creates variable to compare to todays date
      lastDate = lastDate.plusDays(56); //adds 56 days to the date being compared
      return lastDate.isBefore(today); //checks if it's before todays date
 
   }//end eligible
/****************************************************************/
/*Method: toString                                              */
/*Purpose: allows Person objects to be printed in a readable    */
/*         format                                               */
/*Parameters: None.                                             */
/*                                                              */
/*      Return: String                                          */   
/****************************************************************/      
   public String toString(){//start toString
      String str = ""; //creates an empty string
      str += firstName + " " + lastName + " " + "(" + ID + ")"; ///adds the first, last, and ID
      str += "\nBloodtype: " + bloodType + "\n";  //adds the bloodtype to the string
      
      return str; //returns string to be printed
   }//end toString
}//end Person class