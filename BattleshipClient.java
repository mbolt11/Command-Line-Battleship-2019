//Battleship Project

public class BattleshipClient
{
   public static void main(String [] args)
   {
      GameController game = new GameController();
      
      game.printBoards(true);
      game.placeShips(1);
      
      game.printBoards(true);
      game.placeShips(2);
      
      System.out.println("\n\nLET THE GAMES BEGIN!\n\n");
      
      while(!game.isWon())
      {
         game.printBoards(true);
         game.play();
         game.printBoards(false);
         System.out.println("\n\n\n\n\n");
      }
   
      System.out.println("Congratulations "+game.getCurrentPlayer().name()+"! You win.");
   }
}