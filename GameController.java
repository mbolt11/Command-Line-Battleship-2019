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
      
      player1 = new Player(1);
      player2 = new Player(2);
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
   
   public Player getCurrentPlayer()
   {
      return currentPlayer;
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
            int col = -1;
            System.out.println("In which column (1-10) would you like your " + shipnames[i] + " ship?");
            while(col < 1 || col > 10)
            {               
               try
               {
                  String colString = scan.next();
                  col = Integer.parseInt(colString);
               }
               catch(Exception e)
               {
                  System.out.println("Column must be an integer.");
               }
               
               if(col < 1 || col > 10)
                  System.out.print("Invalid column, try again: ");
            }
            
            //Get row
            System.out.println("In which row (A-J) would you like your " + shipnames[i] + " ship?");
            int row = -1;
            while(row < 1 || row > 10)
            {
               String rowstring = scan.next();
               if(rowstring.length() > 1)
                  System.out.println("Using the first character of your input.");
               char rowletter = rowstring.charAt(0);
               rowletter = Character.toUpperCase(rowletter);
               row = rowletter - 64;
            
               if(row < 1 || row > 10)
                  System.out.print("Invalid row, try again: ");
            }
            
            //Get direction
            System.out.println("Horizontal (H) or vertical (V)?");
            char dir = ' ';
            while(dir != 'H' && dir !='V')
            {
               String dirstring = scan.next();
               if(dirstring.length() > 1)
                  System.out.println("Using the first character of your input.");
               dir = dirstring.charAt(0);
               dir = Character.toUpperCase(dir);
               
               if(dir != 'H' && dir !='V')
                  System.out.println("Invlaid direction. Try again.");
            }
            
            //Attempt to place the ship
            success = currentPlayer.placeShip(shiplengths[i],dir,row,col);
         }
         //Print the board
         printBoards(true);
      }
      
      //switch current Player for the next turn or placement
      if(currentPlayer.getNum() == 1)
      {
         currentPlayer = player2;
      }
      else
      {
         currentPlayer = player1;
      }  
      
      System.out.println("Placements complete!\n\n");  
   }
   
   public void play()
   {
      //get a row and column from the user
      boolean validFireLocation = false;
      boolean validInputs = false;
      int col = -1;
      char row = ' ';
      
      //Loop makes sure the input in a valid position on the board
      while(validFireLocation == false)
      {
         //Loop makes sure the correct data types are entered
         validInputs = false;
         while(!validInputs)
         {
            System.out.println(currentPlayer.name()+", where would you like to fire?");
            System.out.println("Indicate a row letter and column number separated by a space.");
            
            //If the first input is an integer
            if(scan.hasNextInt())
            {
               col = scan.nextInt();
               
               //Second input must be a character
               if(scan.hasNextInt())
               {
                  String clear = scan.next();
                  System.out.println("You need to enter a letter and a number. Try again.");
               }
               else
               {
                  String rowstring = scan.next();
                  row = rowstring.charAt(0);
                  validInputs = true;
               }
            }
            //If the first input is a character
            else
            {
               String rowstring = scan.next();
               row = rowstring.charAt(0);
               
               //Second input must be an integer
               if(scan.hasNextInt())
               {
                  col = scan.nextInt();
                  validInputs = true;
               }
               else
               {
                  String clear = scan.next();
                  System.out.println("You need to enter a letter and a number. Try again.");
               }
            }
         }
         
         row = Character.toUpperCase(row);
         validFireLocation = currentPlayer.isValidFire(col,row);
         if(!validFireLocation)
         {
            System.out.println("That is not a valid shot, try again.");
         }
      }
      
      //specify which player object is the opponent
      Player opponent;
      
      if(currentPlayer.getNum() == 1)
      {
         opponent = player2;
      }
      else
      {
         opponent = player1;
      }  
      
      //update each player's boards according to the results of the fire
      currentPlayer.opponentBoardFiredAt(col, row, opponent);
      opponent.playerBoardFiredAt(col, row);
      
      //check for any ships are sunk on the opponent board
      currentPlayer.opponentBoardUpdateSunk(opponent);
      //opponent.playerBoardUpdateSunk(); --> made this on accident, not necessary
      
      //switch current Player for the next turn
      if(currentPlayer.getNum() == 1)
      {
         currentPlayer = player2;
      }
      else
      {
         currentPlayer = player1;
      }  
   }
   
   
   //if all the ships of one player have been Sunk, then one player wins the game
   public boolean isWon()
   {
      Player opponent;
      
      if(currentPlayer.getNum() == 1)
      {
         opponent = player2;
      }
      else
      {
         opponent = player1;
      }
      
      boolean playerShipsSunk = true;
      boolean opponentShipsSunk = true;
      
      //loop through all the ships of each player
      for(int i = 0; i < 5; i++)
      {
         if(!currentPlayer.getShips().get(i).isSunk())
            playerShipsSunk = false;
         if(!opponent.getShips().get(i).isSunk())
            opponentShipsSunk = false;
      }  
      
      if(playerShipsSunk)
      {
         currentPlayer = opponent;
         return true;
      }
      else if(opponentShipsSunk)
      {
         return true;
      }
      
      return false;
   }
   
   //Function that prints the boards on each turn
   public void printBoards(boolean printCurrentPlayerBoard)
   {   
      Player trackCurrentPlayer = currentPlayer;
      
      //Temporarily switching current player for 2nd print
      if(!printCurrentPlayerBoard)
      {
         if(currentPlayer.getNum() == 1)
         {
            currentPlayer = player2;
         }
         else
         {
            currentPlayer = player1;
         } 
      }
      //Print the message above boards for first print
      else
      {
         System.out.println(currentPlayer.getMessage());
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
      
      if(!printCurrentPlayerBoard)
      {
         currentPlayer = trackCurrentPlayer;
      }
   }
}