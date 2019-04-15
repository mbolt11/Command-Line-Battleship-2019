//Battleship Project

import java.util.*;

public class BattleshipClient
{
   public static void main(String [] args)
   {
      Scanner scan = new Scanner(System.in);
      GameController game = new GameController();
      
      System.out.println("Welcome to BATTLESHIP!");
      System.out.println("Press enter to begin the game");
      scan.nextLine();
      
      game.printBoards(true);
      game.placeShips(1);
      
      System.out.println("Press enter to continue");
      scan.nextLine();
      
      game.printBoards(true);
      game.placeShips(2);
      
      System.out.println("Press enter to continue");
      scan.nextLine();
      
      System.out.println("\n\nLET THE GAMES BEGIN!\n\n");
      
      while(!game.isWon())
      {
         game.printBoards(true);
         game.play();
         game.printBoards(false);
         
         System.out.println("Press enter to continue");
         scan.nextLine(); 
         System.out.println("\n\n\n\n\n");
      }
   
      System.out.println("Congratulations "+game.getCurrentPlayer().name()+"! You win.");
   }
}