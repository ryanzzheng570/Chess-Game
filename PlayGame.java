import java.util.Scanner;

class PlayGame
{
  //Check if the row and col is out of boundary
  public static boolean outBound(int row, int col)
  {
    if((row > 7 || col > 7) || ( row < 0 || col < 0))    
      return true;   
    else
      return false;
  }
  
  public static void main (String[] args)
  {
    Scanner sc = new Scanner(System.in); //Scanner for user input
    
    //Intro and description
    System.out.println("Welcome to chess game,");
    System.out.println("This program is designed as a chess game, top chesses are assigned as Black(Player1), and bottom chesses are assigned as White(Player2)"); 
    
    String player1 = "Black";
    String player2 = "White";
    
    ChessGame chessGame = new ChessGame(player1, player2); //Create a new game
    
    System.out.println("The chess board will similar to other chess board like: \nBoard:");
    chessGame.printBoard();
    
    //Variable to store answers.
    String ans = "";
    int counter = 0;
    int rowS = 0;
    int colS = 0;
    int rowD = 0;
    int colD = 0;
    char selectedChar;
    String currentPlayer = "Black";
    boolean isPlayer1 = true;
    boolean isPlayer2 = false;
    boolean isKingDie = false;
    boolean isThreaten = false;
    
    System.out.println("Let's start: ");
    System.out.println("You're able to move any of you own pieces you want");
    do
    {      
      //do-while loop, when answer is out of bounary, loop again to get the correct input   
      do
      {
        //Loop after the first time
        if(counter > 1)
        {         
          System.out.println("CurrentPlayer: " + currentPlayer);     
          do
          {
            System.out.print("If you want to continue, quit or restart, please enter 'move','quit' or 'restart': ");
            ans = sc.next();
            System.out.println();
          }while(!ans.equalsIgnoreCase("move") && !ans.equalsIgnoreCase("quit") && !ans.equalsIgnoreCase("restart"));
        } 
        
        //Check if they want to restart
        if(ans.equalsIgnoreCase("restart"))
        {
          chessGame = new ChessGame(player1, player2);
          System.out.println("Restart:");
          chessGame.printBoard();
          currentPlayer = "Black";
          ans = "";
          counter = 0;
          rowS = 0;
          colS = 0;
          rowD = 0;
          colD = 0;
          isPlayer1 = true;
          isPlayer2 = false;
        }
        
        //If it's a move
        if(ans.equalsIgnoreCase("move") || ans.equals(""))
        {
          do
          {
            do
            {
              do
              {
                System.out.println("CurrentPlayer: " + currentPlayer);
                
                for(int i=0; i<8;i++)
                {
                  for(int j=0; j<8; j++)
                  {
                    if(chessGame.getBoard().getBoard()[i][j] != null)
                    {
                      if(chessGame.getBoard().getBoard()[i][j].getChar() == 'K' || chessGame.getBoard().getBoard()[i][j].getChar() == 'k')
                      {
                        if(chessGame.getBoard().getBoard()[i][j].getPlayer().equals(currentPlayer))
                        {
                          if(chessGame.getBoard().getBoard()[i][j].check() != null)
                          {
                            System.out.println("Your king is treaten");
                            isThreaten= true;                                                                 
                          }
                        }
                      }
                    }
                  }
                }                
                
                //User inputs
                System.out.print("Please enter the row of the piece you want to move: ");
                rowS = sc.nextInt();
                System.out.print("Please enter the col of the piece you want to move: ");
                colS = sc.nextInt();
                
                System.out.print("Please enter the destination of row of the piece you want to move: ");
                rowD = sc.nextInt();
                System.out.print("Please enter the destination of col of the piece you want to move: ");
                colD = sc.nextInt();
                System.out.println();
                
                // If the row and col is out bounded, display the error message
                if(outBound(rowS,colS) || outBound(rowD,colD) || (rowS == rowD && colS == colD))
                  System.out.println("Error, not a valid entry, please enter row and col between 0 and 7 and different location between source and destination.\n");
                
              }while(outBound(rowS,colS) || outBound(rowD,colD) ||(rowS == rowD && colS == colD));
              
              if(!chessGame.getBoard().isPieceAt(rowS,colS))
                System.out.println("Error, there isn't a piece in this location.\n");
              
            }while(!chessGame.getBoard().isPieceAt(rowS,colS));
            
            if(isPlayer1 == true && isPlayer2 == false && !chessGame.getBoard().getPieceAt(new ChessLocation(rowS,colS)).getPlayer().equals("Black"))            
              System.out.println("Error, Please select Black(UpperCase)'s Pieces\n");
            
            if(isPlayer1 == false && isPlayer2 == true && !chessGame.getBoard().getPieceAt(new ChessLocation(rowS,colS)).getPlayer().equals("White"))            
              System.out.println("Error, Please select White(LowerCase)'s Pieces\n");
            
          }while(!chessGame.getBoard().getPieceAt(new ChessLocation(rowS,colS)).getPlayer().equals(currentPlayer));
        }
      }while(outBound(rowS,colS) && !ans.equalsIgnoreCase("quit"));
      
      //If it's not out of boundary or quitting, move to the input location
      if(!outBound(rowS,colS) && !ans.equalsIgnoreCase("quit") && !ans.equalsIgnoreCase("restart"))
      {
        //Output the selected character
        selectedChar = chessGame.getBoard().getChar(rowS, colS);
        System.out.println("Selected Piece: " + selectedChar + ", At location (" + rowS + "," + colS + ") to (" + rowD + "," + colD + ")");
        ChessLocation oldLocation = new ChessLocation(rowS, colS);
        ChessLocation newLocation = new ChessLocation(rowD,colD);
        
        if(isPlayer1 == true && isPlayer2 == false)
        {
          if(chessGame.makeAMove(oldLocation, newLocation))
          {
            isPlayer1 = false;
            isPlayer2 = true;
            currentPlayer = "White";
            chessGame.printBoard(); 
          }
        }
        else if(isPlayer1 == false && isPlayer2 == true)
        {
          if(chessGame.makeAMove(oldLocation, newLocation))
          {
            isPlayer1 = true;
            isPlayer2 = false;
            currentPlayer = "Black";
            chessGame.printBoard(); 
          }
        }
      }
      
      counter++;  
      if(isThreaten)
      {
        for(int i=0; i<8;i++)
        {
          for(int j=0; j<8; j++)
          {
            if(chessGame.getBoard().getBoard()[i][j] != null)
            {
              if(chessGame.getBoard().getBoard()[i][j].getChar() == 'K' || chessGame.getBoard().getBoard()[i][j].getChar() == 'k')
              {
                if(!chessGame.getBoard().getBoard()[i][j].getPlayer().equals(currentPlayer))
                {
                  if(chessGame.getBoard().getBoard()[i][j].check() != null)
                  {
                    isKingDie = true;                                                                
                  }
                  else
                  {
                    isThreaten = false;
                  }
                }
              }
            }
          }
        }
      }
      }while(!ans.equals("quit") || isKingDie == true);
      
      System.out.println("See you again");
      sc.close();
    }
  }
