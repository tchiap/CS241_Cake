/**
 *  Name: Erica Eddy
 *  Course: CSCI 241 - Computer Science I
 *  Section: 001 or 002
 *  Assignment: 7
 *  Due date:  October 2005
 *  
 *  Description:
 *  This program tests the Cake class which determines how to cut a 
 *  rectangular cake into even-sized pieces.
 *  Methods tested include cut() to cut the entire cake, and cutRows() to cut a
 *  specified number of rows.  
 */
import acm.graphics.*;
import acm.program.*;

public class CakeCounter
{
    /**
     * Create a picture with 3 cakes
     * First, make one that has only one row cut
     * Second and third cakes have all pieces cut
     */
    public static void main (String [] args)
    {
       Cake medium = new Cake();
       medium.cutRows(1,9);

       Cake medium1 = new Cake(50,50,350,200);
       medium1.cutRows(1,9);
       
       Cake medium2 = new Cake(50,50,350,200);
       medium2.cutRows(4,9);
       
       
       Cake large = new Cake(200,100,300,300);
       large.cut(16);
        
       Cake snack = new Cake(400,50,300,200);
       snack.cut(25);
    }
}
