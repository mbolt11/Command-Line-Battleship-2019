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
            System.out.println("In which column (1-10) would you like your " + shipnames[i] + " ship?");
            
            int col = -1;
            
            do{
               try{
                  String colString = scan.next();
                  col = Integer.parseInt(colString);
                  }
               catch(InputMismatchException ime)
                  {
                     System.out.println("Please enter an integer in the range 1-10.");
                  }
            }while(col < 1 || col > 10);
            
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
            char dir = ' ';
            while(dir != 'H' && dir !='V')
            {
               dir = scan.next().charAt(0);
               dir = Character.toUpperCase(dir);
               System.out.println("check: "+dir);
               
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
      int col = -1;
      char row = ' ';
      
      while(validFireLocation == false)
      {
         System.out.println(currentPlayer.name()+" in which column (1-10) of the opponent's board would you like to fire at?");
         
         do{
            try{
               String colString = scan.next();
               col = Integer.parseInt(colString);
               }
            catch(InputMismatchException ime)
               {
                  System.out.println("Please enter an integer in the range 1-10.");
               }
         }while(col < 1 || col > 10);
         
         System.out.println(currentPlayer.name()+" in which row (A-J) of the opponent's board would you like to fire at?");
         int rowInt = -1;
         
         do{
            try{
               row = scan.next().charAt(0); 
               row = Character.toUpperCase(row);
               rowInt = row - 64;
               }
            catch(InputMismatchException ime)
               {
               }
               
            if(rowInt < 1 || rowInt > 10)
               System.out.println("Please enter an charcater in the range A-J.");
         }while(rowInt < 1 || rowInt > 10);
         
         //check if the opponent's board at that location has not already been fired at
         //must remember to move the row and column in and down by 1 so that the correct string value is looked at
         validFireLocation = currentPlayer.isValidFire(col, row); 
         
         if(!validFireLocation)
            System.out.println("You have already fired this location. Enter another location.");
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