class ChessBoard
{
  //Private variables
  private  ChessPiece[][] chessBoard = new ChessPiece[8][8]; //Counting the 4 sided number from 0-7 too
  private  String[] printNums = {"0 ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ",};
  
  //Constructor method for class ChessBoard
  public ChessBoard()
  {
    for(int i=0; i<8; i++)
    {
      for(int j=0; j<8; j++)
      {
        chessBoard[i][j] = null;
      }
    }
  }
  
  //Determine whether there's a piece in the provided location
  public boolean isPieceAt(int row, int col)
  {
    if(chessBoard[row][col] != null)
    {
      return true;
    }
    else 
    {
      return false;
    }
  }
  
  //Get the piece from the provided location
  public ChessPiece getPieceAt(ChessLocation location)
  {
    return chessBoard[location.getRow()][location.getCol()];    
  }
  
  //Get the Char from given location
  public char getChar(int row, int col)
  {
    return chessBoard[row][col].getChar();
  }
  
  //Place the knight at the given location. The method will overwrite the old piece with the new one
  public void placePieceAt(ChessPiece piece, ChessLocation newLocation)
  {
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    
    if(isPieceAt(row,col))
    { 
      removePiece(newLocation);
      chessBoard[row][col] = piece;
    }
    else 
    {
      chessBoard[row][col] = piece;
    }
  }
  
  //Remove the piece at the specified row and col
  public void removePiece(ChessLocation location)
  {
    int row = location.getRow();
    int col = location.getCol();
    chessBoard[row][col] = null;
  }
  
  //Return the board 
  public ChessPiece[][] getBoard()
  {
    return chessBoard;
  }
  
  //Print the Board
  public void printBoard()
  {    
    System.out.print("  ");
    for(int i=0; i<8;i++)
    {
      System.out.print(printNums[i]);
    }
    System.out.println();
    
    for(int i=0; i<8; i++)
    {
      System.out.print(printNums[i]);
      for(int j=0; j<8; j++)
      {
        if(chessBoard[i][j] != null)
        {
          System.out.print(chessBoard[i][j].getChar() + " ");
        }
        else
        {
          System.out.print("- ");
        }
      }
      System.out.println();
    }   
    System.out.println();
  }
  
}
