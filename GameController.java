//Battleship game control

import java.util.*;

public class GameController
{
   //Players- 1, 2, and a reference to current
   private Player player1, player2;
   private Player currentPlayer;
   private Scanner scan;
   
   //Constructor
   public GameController()
   {
      scan = new Scanner(System.in);
      
      player1 = new Player();
      player2 = new Player();
      currentPlayer = player1;
      
      //Fill boards initially with all dashes except for labels
      for(int i = 0; i < 11; i++)
      {
         for(int j = 0; j < 11; j++)
         {
            //Column labels (numbers)
            if(i == 0)
            {
               if(j == 0)
               {  
                  player1.getMyBoard()[i][j] = " ";
                  player1.getOppBoard()[i][j] = " ";
                  player2.getMyBoard()[i][j] = " ";
                  player2.getOppBoard()[i][j] = " ";
               }
               else
               {  
                  player1.getMyBoard()[i][j] = Integer.toString(j);
                  player1.getOppBoard()[i][j] = Integer.toString(j);
                  player2.getMyBoard()[i][j] = Integer.toString(j);
                  player2.getOppBoard()[i][j] = Integer.toString(j);
               }
            }
            //Row labels (letters)
            else if(j == 0)
            {
               if(i != 0)
               {
                  char letter = 'A';
                  
                  player1.getMyBoard()[i][j] = Character.toString((char)(letter + (i-1)));
                  player1.getOppBoard()[i][j] = Character.toString((char)(letter + (i-1)));
                  player2.getMyBoard()[i][j] = Character.toString((char)(letter + (i-1)));
                  player2.getOppBoard()[i][j] = Character.toString((char)(letter + (i-1)));
               }
            }
            //The dashes in all other spots
            else
            {  
               player1.getMyBoard()[i][j] = "-";
               player1.getOppBoard()[i][j] = "-";
               player2.getMyBoard()[i][j] = "-";
               player2.getOppBoard()[i][j] = "-";
            }
         }
      }
   }
   
   //Function to place ships on board at beginning of game
   public void placeShips(int playernum)
   {
      //Set the current player based on the int passed in
      if(playernum == 1)
      {
         currentPlayer = player1;
      }
      else
      {
         currentPlayer = player2;
      }
      
      //Intro message
      System.out.println("Hello Player " + playernum + "! Let's place your ships.");
      String[] shipnames = new String[] {"2-unit","first 3-unit","second 3-unit","4-unit","5-unit"};
      int[] shiplengths = new int[] {2,3,3,4,5};
      
      //Loop through placing all 5 ships
      for(int i = 0; i < 5; i++)
      {
         boolean success = false;
         while(!success)
         {
            //Get column
            System.out.println("In which column (1-10) would you like your " + shipnames[i] + " ship?");
            int col = scan.nextInt();
          
            while(col < 1 || col > 10)
            {
               System.out.print("Invalid column, try again: ");
               col = scan.nextInt();
            }
            
            //Get row
            System.out.println("In which row (A-J) would you like your " + shipnames[i] + " ship?");
            char rowletter = scan.next().charAt(0);
            rowletter = Character.toUpperCase(rowletter);
            int row = rowletter - 64;
            
            while(row < 1 || row > 10)
            {
               System.out.print("Invalid row, try again: ");
               rowletter = scan.next().charAt(0);
               rowletter = Character.toUpperCase(rowletter);
               row = rowletter - 64;
            }
            
            //Get direction
            System.out.println("Horizontal (H) or vertical (V)?");
            char dir = scan.next().charAt(0);
            dir = Character.toUpperCase(dir);
            
            //Attempt to place the ship
            success = currentPlayer.placeShip(shiplengths[i],dir,row,col);
         }
         //Print the board
         printBoards(playernum);
      }
      
      System.out.println("Placements complete!");  
   }
   
   //Function that prints the boards on each turn
   public void printBoards(int playernum)
   {
      //Set the current player based on the int passed in
      if(playernum == 1)
      {
         currentPlayer = player1;
      }
      else
      {
         currentPlayer = player2;
      }
     
      //Print the boards
      System.out.println("      Your Board                Opponent Board");
      for(int i = 0; i < 11; i++) //i is the rows
      {
         String row = "";
         
         //Loop through player's board columns
         for(int j = 0; j < 11; j++)
         {
            row += currentPlayer.getMyBoard()[i][j] + " ";
         }
         
         //Add spaces which separate the two boards
         if(i == 0)
            row += "     ";
         else
            row += "      ";  
                      
         //Loop through opponent's board columns
         for(int k = 0; k < 11; k++)
         {
            row += currentPlayer.getOppBoard()[i][k] + " ";
         }
         
         System.out.println(row);
      }
   }
}