class ChessGame
{
  //Private variables
  private  ChessBoard board;
  String player1;
  String player2;
  private ChessPiece[] pieces;
  
  //Constructor method for class ChessGame
  public ChessGame(String player1,String player2)
  {    
    board = new ChessBoard();
    this.player1 = player1;
    this.player2 = player2;
    
    //Create all the pieces on the board
    pieces = new ChessPiece[]{
      new King(player1, this, new ChessLocation(0,4)),
        new Queen(player1, this, new ChessLocation(0,3)),
        new Knight(player1, this, new ChessLocation(0,1)),
        new Knight(player1, this, new ChessLocation(0,6)),
        new Bishop(player1, this, new ChessLocation(0,2)),
        new Bishop(player1, this, new ChessLocation(0,5)),
        new Rook(player1, this, new ChessLocation(0,0)),
        new Rook(player1, this, new ChessLocation(0,7)),
        new Pawn(player1, this, new ChessLocation(1,0)),
        new Pawn(player1, this, new ChessLocation(1,1)),
        new Pawn(player1, this, new ChessLocation(1,2)),
        new Pawn(player1, this, new ChessLocation(1,3)),
        new Pawn(player1, this, new ChessLocation(1,4)),
        new Pawn(player1, this, new ChessLocation(1,5)),
        new Pawn(player1, this, new ChessLocation(1,6)),
        new Pawn(player1, this, new ChessLocation(1,7)),      
        new King(player2, this, new ChessLocation(7,4)),
        new Queen(player2, this, new ChessLocation(7,3)),
        new Knight(player2, this, new ChessLocation(7,1)),
        new Knight(player2, this, new ChessLocation(7,6)),
        new Bishop(player2, this, new ChessLocation(7,2)),
        new Bishop(player2, this, new ChessLocation(7,5)),
        new Rook(player2, this, new ChessLocation(7,0)),
        new Rook(player2, this, new ChessLocation(7,7)),
        new Pawn(player2, this, new ChessLocation(6,0)),
        new Pawn(player2, this, new ChessLocation(6,1)),
        new Pawn(player2, this, new ChessLocation(6,2)),
        new Pawn(player2, this, new ChessLocation(6,3)),
        new Pawn(player2, this, new ChessLocation(6,4)),
        new Pawn(player2, this, new ChessLocation(6,5)),
        new Pawn(player2, this, new ChessLocation(6,6)),
        new Pawn(player2, this, new ChessLocation(6,7))};
  }
  
  //Return the board
  public ChessBoard getBoard()
  {
    return board;
  }
  
  //Make a move according to the user input
  public boolean makeAMove(ChessLocation oldLocation, ChessLocation newLocation)
  {    
    return board.getBoard()[oldLocation.getRow()][oldLocation.getCol()].moveTo(newLocation);
  }
  
  //Print the board
  public void printBoard()
  {
    board.printBoard();
  }
  
  //Return the pieces
  public ChessPiece[] getPieces()
  {
    return pieces;
  }
}
