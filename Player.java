//Player class

import java.util.*;

public class Player
{
   private String [][] myBoard, opponentBoard;
   private ArrayList<Ship> myShips;
   private int playerNumber;
   
   public Player(int playerNum_in)
   {
      myBoard = new String[11][11];
      opponentBoard = new String[11][11];
      myShips = new ArrayList<Ship>();
      playerNumber = playerNum_in;
   }
   
   public int getNum()
   {
      return playerNumber;
   }
   
   public String name()
   {
      return "Player "+playerNumber;
   }
   
   public String[][] getMyBoard()
   {
      return myBoard;
   }
   
   public String[][] getOppBoard()
   {
      return opponentBoard;
   }
   
   public ArrayList<Ship> getShips()
   {
      return myShips;
   }
   
   public boolean isValidFire(int col, char row_in)
   {
      int row = (row_in - 'A') + 1;
      
      /*System.out.println("Checking location "+row+" "+col);
      System.out.println("Opponent Board at location is "+opponentBoard[row][col]);
      System.out.println("isValidFire method returns "+ opponentBoard[row][col].equals("-"));*/
      if(opponentBoard[row][col].equals("-"))
         return true;
      else
         return false;
   }
   
   public void opponentBoardFiredAt(int col, char row_in, Player opponent)
   {
      int row = (row_in - 'A') + 1;
      char fireResult = '*';
      
      if(opponent.shipHit(col, row_in))
         fireResult = 'H';
         
      opponentBoard[row][col] = Character.toString(fireResult); 
   }
   
   public void playerBoardFiredAt(int col, char row_in)
   {
      int row = (row_in - 'A') + 1;
      char fireResult = 'X';
      
      myBoard[row][col] = Character.toString(fireResult); 
   }
   
   public boolean shipHit(int col, int row)
   {
      boolean success = false;
      
      for(int i = 0; i < myShips.size(); i++)
      {
         success = myShips.get(i).isHit(col, row);
         if(success)
            return success;
      }
      
      return success;
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