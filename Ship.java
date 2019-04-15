//Ship class

public class Ship
{
   private int length;
   private char direction;
   private boolean sunk;
   private int row;
   private int column;
   private int life;
   private int [][] shipLocations;
   
   public Ship(int len, char dir, int row_in, int column_in)
   {
      length = len;
      life = len;
      direction = dir;
      row = row_in;
      column = column_in;
      sunk = false;
      
      //will keep track of all locations on the board that the ship occupies
      shipLocations = new int [len][2];
      
      if(dir == 'H')
      {
         for(int i = 0; i < len; i++)
         {
            for(int j = 0; j < 2; j++)
            {
               if(j == 0)
                  shipLocations[i][j] = row;
               else
                  shipLocations[i][j] = column + i;
            }
         }
      }
      else if(dir == 'V')
      {
         for(int i = 0; i < len; i++)
         {
            for(int j = 0; j < 2; j++)
            {
               if(j == 0)
                  shipLocations[i][j] = row + i;
               else
                  shipLocations[i][j] = column;
            }
         }
      }
   }
   
   public boolean isHit(int col, int row)
   {
      for(int i = 0; i < length; i++)
      {
         if(shipLocations[i][0] == row && shipLocations[i][1] == col)
         {
            hit();
            return true;
         }
      }
      
      return false;
   }
   
   public void hit()
   {
      life--;
      if(life == 0)
         sunk = true;
   }
   
   public boolean isSunk()
   {
      return sunk;
   }
}