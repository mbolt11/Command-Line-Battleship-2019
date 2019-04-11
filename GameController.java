//Battleship game control

public class GameController
{
   //Arrays to represent boards
   private String [][] player1board, player1opponent, player2board, player2opponent;
   private int currentPlayer;
   
   //Constructor
   public GameController()
   {
      player1board = new String[11][11];
      player1opponent = new String[11][11];
      player2board = new String[11][11];
      player2opponent = new String[11][11];
      currentPlayer = 1;
      
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
                  player1board[i][j] = " ";
                  player1opponent[i][j] = " ";
                  player2board[i][j] = " ";
                  player2opponent[i][j] = " ";
               }
               else
               {
                  player1board[i][j] = Integer.toString(j);
                  player1opponent[i][j] = Integer.toString(j);
                  player2board[i][j] = Integer.toString(j);
                  player2opponent[i][j] = Integer.toString(j);
               }
            }
            //Row labels (letters)
            else if(j == 0)
            {
               if(i != 0)
               {
                  char letter = 'A';
                  player1board[i][j] = Character.toString((char)(letter + (i-1)));
                  player1opponent[i][j] = Character.toString((char)(letter + (i-1)));
                  player2board[i][j] = Character.toString((char)(letter + (i-1)));
                  player2opponent[i][j] = Character.toString((char)(letter + (i-1)));
               }
            }
            //The dashes in all other spots
            else
            {
               player1board[i][j] = "-";
               player1opponent[i][j] = "-";
               player2board[i][j] = "-";
               player2opponent[i][j] = "-";
            }
         }
      }
   }
   
   //Function that prints the boards on each turn
   public void printBoards()
   {
      //Player 1's turn
      if(currentPlayer == 1)
      {
         System.out.println("      Your Board                Opponent Board");
         //i is the rows
         for(int i = 0; i < 11; i++)
         {
            String row = "";
            
            //Loop through player's board columns
            for(int j = 0; j < 11; j++)
            {
               row += player1board[i][j] + " ";
            }
            
            //Add spaces which separate the two boards
            if(i == 0)
               row += "     ";
            else
               row += "      ";  
                         
            //Loop through opponent's board columns
            for(int k = 0; k < 11; k++)
            {
               row += player1opponent[i][k] + " ";
            }
            
            System.out.println(row);
         }
      }
      //Player 2's turn
      else
      {
         System.out.println("      Your Board                Opponent Board");
         //i is the rows
         for(int i = 0; i < 11; i++)
         {
            String row = "";
            
            //Loop through player's board columns
            for(int j = 0; j < 11; j++)
            {
               row += player2board[i][j] + " ";
            }
            
            //Add spaces which separate the two boards
            if(i == 0)
               row += "     ";
            else
               row += "      ";
            
            //Loop through opponent's board columns
            for(int k = 0; k < 11; k++)
            {
               row += player2opponent[i][k] + " ";
            }
            
            System.out.println(row);
         }
      }
   }
}