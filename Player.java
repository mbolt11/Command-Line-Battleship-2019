//Player class

import java.util.*;

public class Player
{
   private String [][] myBoard, opponentBoard;
   private ArrayList<Ship> myShips;
   
   public Player()
   {
      myBoard = new String[11][11];
      opponentBoard = new String[11][11];
      myShips = new ArrayList<Ship>();
   }
   
   public String[][] getMyBoard()
   {
      return myBoard;
   }
   
   public String[][] getOppBoard()
   {
      return opponentBoard;
   }
   
   public boolean placeShip(int length, char direction, int row, int column)
   {
      //Check validity of placement
      for(int i = 0; i < length; i++)
      {
         if(direction == 'H')
         {
            //Checks if ship runs off board
            if( (column+i) > 10)
            {
               System.out.println("Invalid placement, try again.");
               return false;
            }
            //Checks if ship collides with another ship
            if(!myBoard[row][column+i].equals("-"))
            {
               System.out.println("Invalid placement, try again.");
               return false;
            }   
         }
         else if(direction == 'V')
         {
            //Checks if ship runs off board
            if( (row+i) > 10)
            {
               System.out.println("Invalid placement, try again.");
               return false;
            }
            //Checks if ship collides with another ship
            if(!myBoard[row+i][column].equals("-"))
            {
               System.out.println("Invalid placement, try again.");
               return false;
            } 
         }
      }
      //Create and place the ship if it has been found to be valid
      String shipID = Integer.toString(length);
      Ship theShip = new Ship(length,direction,row,column);
      myShips.add(theShip);
      for(int i = 0; i < length; i++)
      {
         if(direction == 'H')
         {
            myBoard[row][column+i] = shipID;
         }
         else if(direction == 'V')
         {
            myBoard[row+i][column] = shipID;
         }
      }
      return true;
   }
}