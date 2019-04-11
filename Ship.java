//Ship class

public class Ship
{
   private int length;
   private char direction;
   private boolean sunk;
   private int row;
   private int column;
   private int life;
   
   public Ship(int len, int dir, int row_in, int column_in)
   {
      length = len;
      life = len;
      direction = dir;
      row = row_in;
      column = column_in;
      sunk = false;
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