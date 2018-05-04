package project3;
/*******************************************/
/* Alex Schoonover                         */
/* Login ID: scho9419                      */
/* CS-102, Winter 2018                     */
/* Programming Assignment 3                */
/* Node Class                              */
/*******************************************/
import java.time.LocalDate;
public class Node 
{
   Object datum;
   Node next; 
   Node dateNode; 
   
   public Node() {
      datum = null;
      next = null; 
      dateNode = null; 
   }
   
   public Object getDatum(){return datum;}
   public Node getNext(){return next;}
   public void setDatum(Object datum){this.datum = datum;}
   public void setNext(Node next){this.next = next;}
   public void setDateNode(Node dateNode){this.dateNode = dateNode;}
   public Node getDateNode(){return dateNode;}
   public void setNextDate(Node next){this.dateNode = next;}
   
}