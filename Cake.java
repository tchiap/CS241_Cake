/**
 *  Name: Tom Chiapete
 *  Course: CSCI 241 - Computer Science I
 *  Section: 001
 *  Assignment: 7
 *  Due date:  Nov 8, 2005
 *  
 *  Description:
 *  This program will draw a cake shape to the screen and will 
 *  show what cuts need to be made based on the methods cut()
 *  and cutRow(), and the arguments passed to them.
 *  The cut() method cuts the whole cake, the cutRow() method 
 *  will only cut the number of rows you tell it to.
 *  
 *  There are two constructors to this class.  One is the 
 *  default constructor which will set the size and position of 
 *  the cake to default values.  The alternate constructor lets
 *  the user place the cake where he or she wants it, and sets 
 *  the dimensions of the cake on there own.
 *  
 *  This program extends the GraphicsProgram class and 
 *  uses the ACM and Java Swing libraries.
 *  
 *  Known Bugs:
 *  Error that affects very large total numbers 
 *  which makes the last row and last column of the cake not 
 *  equal to the rest.  Tested total of 1024 when that happened.
 *  Otherwise it should work fine for reasonable cuts.
 *  I tried changing the cut variables from int to double, but
 *  nothing obvious happened.  Still looks like a precision error
 *  that I could not find.
 */

import acm.graphics.*;
import acm.program.*;
import javax.swing.*;

public class Cake extends GraphicsProgram
{
    // instance variables
    private int x; // x point of top left corner of rectangle
    private int y; // y point of top left corner of rectangle
    private int width; // width of cake
    private int height; // height of cake
       
    /**
     * Cake() default contructor
     * Set the Cakes dimensions to preset values.
     */
    public Cake()
    {
        // start graphics
        super();
        start();
        
        // set up Cake with default values
        x = 100; // x coordinate of upper left corner of cake
        y = 100; // y coordinate of upper left corner of cake
        height = 200; // height of the cake
        width = 200; // width of the cake
    }

    /**
     * Cake() alternate contructor
     * Set the Cakes dimensions to values specified by the user 
     * 
     * @param   x       x-coordinate of the center of the Cake
     * @param   y       y-coordinate of the center of the Cake
     * @param   width   width of the Cake
     * @param   height  height of the Cake
     */
    public Cake(int x, int y, int width, int height)
    {
        // start graphics
        super();
        start();
        
        // initialise instance variables with parameters
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * cut() method
     * Takes in one parameter, total, which should be a perfect 
     * square such as 4, 9, 16, 25, 36, 49, 64, 81, 100...
     * If not, the user will be given a little error message.
     * 
     * This method will then create lines to show where the 
     * cake should be cut for even pieces.
     */
    public void cut(int total)
    {
        // Next few lines find out if input is a perfect square
        double squareRoot = Math.sqrt(total);
        int cuts = (int)Math.sqrt(total);
        if (squareRoot - cuts != 0.0)
            JOptionPane.showMessageDialog(null,total+
                " is not a perfect square.\n"+
                "Please try again.");

        double vertCuts = width / cuts; // spacing of vert cuts
        double horiCuts = height / cuts; // spacing of horizontal cuts
        
        // Create a rectange as the outter edge of the cake.
        GRect rect = new GRect(x,y,width,height);
        add(rect);
        
        
        double tempX = x; // temporary x variable used for line output
        
        // Loop to draw cuts.
        for (int i=1;i<cuts;i++)
        {
            tempX += vertCuts; // sets next x
            
            // Add 3 lines for thickness
            GLine vertLineA = new GLine(tempX-1,y,tempX-1,y+height);
            GLine vertLineB = new GLine(tempX,y,tempX,y+height);
            GLine vertLineC = new GLine(tempX+1,y,tempX+1,y+height);
            
            // Set the color to blue.
            vertLineA.setColor(BLUE);
            vertLineB.setColor(BLUE);
            vertLineC.setColor(BLUE);
            
            // Add to screen
            add(vertLineA);
            add(vertLineB);
            add(vertLineC);
        }
        
        double tempY = y; // temporary y variable used for line output
        
        // Loop to draw cuts
        for (int i=1;i<cuts;i++)
        {
            tempY += horiCuts; // sets next y
            
            // Add 3 lines for thickness
            GLine horiLineA = new GLine(x,tempY-1,width+x,tempY-1);
            GLine horiLineB = new GLine(x,tempY,width+x,tempY);
            GLine horiLineC = new GLine(x,tempY+1,width+x,tempY+1);
            
            // Set the color to red.
            horiLineA.setColor(RED);
            horiLineB.setColor(RED);
            horiLineC.setColor(RED);
            
            // Add lines to screen.
            add(horiLineA);
            add(horiLineB);
            add(horiLineC);
        }
    }
    
    /** 
     * cutRows() method
     * Takes in two parameters, rows and total.  The rows will note how
     * many rows we should cut. The number of pieces in each row should 
     * be the square root of the total.  The rows times the square 
     * root of the total should be the number of pieces we should cut.
     * 
     * Check for perfect square again.  If invalid, let the user know.
     */
    public void cutRows(int rows, int total)
    {
        // Check for perfect square.
        double squareRoot = Math.sqrt(total);
        int cuts = (int)Math.sqrt(total);
        if (squareRoot - cuts != 0.0)
            JOptionPane.showMessageDialog(null,total+" is not a perfect "+
            "square.\n"+"Please try again.");
            
        double vertCuts = width / cuts;  // separation between verticle cut
        double horiCuts = height / cuts; // separation between horizontal cut
        
        // Create a rectangle as the outter edge of the cake.
        // Add to screen
        GRect rect = new GRect(x,y,width,height);
        add(rect);
        

        double tempY = y;  // temporary y value used for spacing
        int j = 1; // used so we don't create too many rows.
        double greatestY = 0.0; // finds the greatest value
                            // of y so our verticle lines stop the the 
                            // lowest horizontal line.
                            
        // Loop to draw cuts.
        for (int i=1;i<cuts;i++)
        {
            if (j<=rows)
            {
                tempY += horiCuts; // finds next y
                
                // Create three lines for thickness
                GLine horiLineA = new GLine(x,tempY-1,x+width,tempY-1);
                GLine horiLineB = new GLine(x,tempY,x+width,tempY);
                GLine horiLineC = new GLine(x,tempY+1,x+width,tempY+1);
                
                // Set the color to red.
                horiLineA.setColor(RED);
                horiLineB.setColor(RED);
                horiLineC.setColor(RED);
                
                // Add these lines to the screen.
                add(horiLineA);
                add(horiLineB);
                add(horiLineC);
                
                greatestY = tempY; // new greatest y is set
                j++; // increment j to keep a count of rows.
            }
        }        
        double tempX = x; // temporary value of x used for spacing
        
        // Loop to draw cuts.
        for (int i=1;i<cuts;i++)
        {
            tempX += vertCuts; // finds next x
            
            // Draw three lines for thickness
            GLine vertLineA = new GLine(tempX-1,y,tempX-1,greatestY);
            GLine vertLineB = new GLine(tempX,y,tempX,greatestY);
            GLine vertLineC = new GLine(tempX+1,y,tempX+1,greatestY);
            
            // Set the line color to blue.
            vertLineA.setColor(BLUE);
            vertLineB.setColor(BLUE);
            vertLineC.setColor(BLUE);
            
            // Add the verticle lines to the screen.
            add(vertLineA);
            add(vertLineB);
            add(vertLineC);
        }
    }
}
