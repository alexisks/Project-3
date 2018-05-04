package project3;

/************************************************************/
/* Alex Schoonover                                          */
/* Login ID: scho9419                                       */
/* CS-102, Winter 2018                                      */
/* Programming Assignment 3                                 */
/* Donation Class: informatin about donations and dates     */
/************************************************************/
import java.lang.*;
import java.time.LocalDate;

public class Donation
{ //Donation class
   String ID; 
   LocalDate date;  
   
   
   public Donation(String ID, LocalDate date) {//Donation
      this.ID = ID; 
      this.date = date; 
   }//end Donation
   
   public String getID() { return ID; }
   public LocalDate getDate(){  return date; }
   
/****************************************************************/
/*Method: equalTo                                               */
/*Purpose: Checks to see if dates are the same                  */
/*                                                              */
/*      Parameters: Donation item                               */
/*      Return: boolean                                         */
/****************************************************************/
   public boolean equalTo(Donation item){ 
      if(this.date.compareTo(item.getDate()) == 0) //if dates are the same
      return true; //returns true  
      
      else //if the dates are not the same
      return false; //returns false
   }
} //end Donation class