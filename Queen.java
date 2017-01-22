class Queen extends ChessPiece
{
  //Current Player
  String currentPlayer;
  
  //Constructor for Queen
  public Queen(String player, ChessGame game, ChessLocation initial_location)
  {
    super(player, initial_location, game);
    if(player.equals("Black"))
      this.id = 'Q';
    else if(player.equals("White"))
      this.id = 'q';
    currentPlayer = player; //Identify the player
    super.assignLocation(initial_location); //Assign the location to the initial_location
  }
  
  public boolean moveTo(ChessLocation newLocation)
  {   
    //If the move is valid and there isn't any piece at that location, Move it.
    if(isValid(newLocation))
    {
      //Check line of sight
      if(!super.checkLineOfSight(this.getLocation(),newLocation))
      {
        super.moveTo(newLocation);
        return true;
      }
      else
      {
        System.out.println("There's a piece blocking the way, please try again\n");
        return false;
      }
    }   
    else
    {
      //Error message if it's not a valid move
      System.out.println("Error, It's not an valid move or the piece is your own, please enter again.\n");
      return false;
    }
    
  }
  public boolean isValid(ChessLocation newLocation)
  {
    int oldR = this.getLocation().getRow();
    int oldC = this.getLocation().getCol();
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    
    if((oldR < row || oldR > row) && oldC == col)
    {
      return true;
    }
    else if(oldR == row && (oldC < col || oldC > col))
    {
      return true;
    }
    else if(oldC-col != 0 && ((oldR-row)/(oldC-col) == 1 || (oldR-row)/(oldC-col) == -1))
    {
      return true;   
    }
    return false;
  }
  
  public void updateThreateningLocation(ChessLocation newLocation)
  {
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    ChessBoard board = super.getBoard();
    
    for(int i=0; i<8; i++)
    {
      ChessLocation loc = new ChessLocation(i,col);
      if(board.isPieceAt(i,col) && !board.getPieceAt(loc).getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, loc))
      {
        super.getThreatArr().add(loc);
      }
    }
    
    for(int j=0; j<8; j++)
    {
      ChessLocation loc = new ChessLocation(row,j);
      if(board.isPieceAt(row,j) && !board.getPieceAt(loc).getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, loc))
      {
        super.getThreatArr().add(loc);
      }
    }
    
    for(int i=0; i<8; i++)
    {
      for(int j=0; j<8;j++)
      {
        if(j - col != 0)
        {
          if((i-row)/(j-col) == 1 || (i-row)/(j-col) == -1)
          {
            ChessLocation loc = new ChessLocation(i,j);
            if(board.isPieceAt(i,j) && !board.getPieceAt(loc).getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, loc))
              super.getThreatArr().add(loc);
          }
        }
      }
    }
  }
  
  public String getPlayer()
   {
     return currentPlayer;
   }
  public ChessPiece check()
  {
    return null;
  }
}