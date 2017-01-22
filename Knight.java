class Knight extends ChessPiece
{  
  //Current Player
  String currentPlayer;
  
  //Constructor for Knight
  public Knight(String player, ChessGame game, ChessLocation initial_location)
  {
    //Initialize the super class
    super(player, initial_location, game);
    
    //Black chess = upper case letter, White chess = lower case letter
    if(player.equals("Black"))
      this.id = 'N';
    else if(player.equals("White"))
      this.id = 'n';
    currentPlayer = player; //Identify the player
    super.assignLocation(initial_location); //Assign the location to the initial_location
  }
  
  // Move the chess to the new location given by the user
  public boolean moveTo(ChessLocation newLocation)
  {   
    //If the move is valid and there isn't any piece at that location, Move it.
    if(isValid(newLocation))
    {
      super.moveTo(newLocation);
      return true;
    }   
    else
    {
      //Error message if it's not a valid move
      System.out.println("Error, It's not an valid move or the piece is your own, please enter again.\n");
      return false;
    }
    
  }
  
  //Method to check the legality of movement 
  public boolean isValid(ChessLocation newLocation)
  {
    int row = this.getLocation().getRow(); 
    int col = this.getLocation().getCol();
    
    int newRow = newLocation.getRow();
    int newCol = newLocation.getCol();
    
    if((row-2 >= 0) && ((row-2 == newRow) && ((col-1 == newCol) || (col+1 == newCol))))  
    {
      return true;
    }
    else if((row+2 <=7) && ((row+2 == newRow) && ((col-1 == newCol) || (col+1 == newCol))))
    {
      return true;
    }
    else if((col-2 >= 0) && ((col-2 == newCol) && ((row-1 == newRow) || (row+1 == newRow))))              
    {
      return true;
    }
    else if((col+2 <= 7) && ((col+2 == newCol) && ((row-1 == newRow) || (row+1 == newRow))))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //Update the threatening location
  public void updateThreateningLocation(ChessLocation newLocation)
  {
    ChessBoard board = super.getBoard();
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    
    if(row-2 >= 0 && col+1 <8 && col-1 >=0)
    {
      if(board.isPieceAt(row-2,col-1))
      {
        if(!board.getBoard()[row-2][col-1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-2,col-1)))
          super.getThreatArr().add(new ChessLocation(row-2,col-1));
      }
      if(board.isPieceAt(row-2,col+1))
      {
        if(!board.getBoard()[row-2][col+1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-2,col+1)))
          super.getThreatArr().add(new ChessLocation(row-2,col+1));      
      }
    }
    else if(row+2 <=7 && col+1 <8 && col-1 >=0)
    {
      if(board.isPieceAt(row+2,col-1))
      {
        if(!board.getBoard()[row+2][col-1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+2,col-1)))
          super.getThreatArr().add(new ChessLocation(row+2,col-1));
      }
      if(board.isPieceAt(row+2,col+1))
      {
        if(!board.getBoard()[row+2][col+1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+2,col+1)))
          super.getThreatArr().add(new ChessLocation(row+2,col+1));
      }
    }
    else if(col-2 >= 0 && row+1 <8 && row-1 >=0)              
    {
      if(board.isPieceAt(row-1,col-2))
      {
        if(!board.getBoard()[row-1][col-2].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-1,col-2)))
          super.getThreatArr().add(new ChessLocation(row-1,col-2));
      }
      if(board.isPieceAt(row+1,col-2))
      {
        if(!board.getBoard()[row+1][col-2].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+1,col-2)))
          super.getThreatArr().add(new ChessLocation(row+1,col-2));
      }
    }
    else if(col+2 <= 7 && row+1 <8 && row-1 >=0)
    {
      if(board.isPieceAt(row-1,col+2))
      {
        if(!board.getBoard()[row-1][col+2].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-1,col+2)))
          super.getThreatArr().add(new ChessLocation(row-1,col+2));
      }
      if(board.isPieceAt(row+1,col+2))
      {
        if(!board.getBoard()[row+1][col+2].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+1,col+2)))
          super.getThreatArr().add(new ChessLocation(row+1,col+2));
      }
    }
  }
  
  //Accessors and mutators methods for the private variables
  public String getPlayer()
  {
    return currentPlayer;
  }
  public ChessPiece check()
  {
    return null;
  }
}