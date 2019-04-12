//Battleship Project

public class BattleshipClient
{
   public static void main(String [] args)
   {
      GameController game = new GameController();
      
      game.printBoards(1);
      game.printBoards(2);
      
      game.placeShips(1);
   }
}