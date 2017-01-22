class Pawn extends ChessPiece
{
  //Current Player
  String currentPlayer;
  private boolean firstMove = false;
  
  //Constructor for Pawn
  public Pawn(String player,ChessGame game, ChessLocation initial_location)
  {
    //Initialize the super class
    super(player, initial_location, game);
    
    //Black chess = upper case letter, White chess = lower case letter
    if(player.equals("Black"))
      this.id = 'P';
    else if(player.equals("White"))
      this.id = 'p';
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
        this.firstMove = true;
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
  
  //Method to check the legality of movement 
  public boolean isValid(ChessLocation newLocation)
  {
    int oldR = this.getLocation().getRow();
    int oldC = this.getLocation().getCol();
    int newR = newLocation.getRow();
    int newC = newLocation.getCol();
    
    
    if(super.getBoard().getPieceAt(newLocation) != null)
    {
      if(currentPlayer.equals("Black") && super.getBoard().getPieceAt(newLocation).getPlayer().equals("White"))
      {
        if(oldC-1 >= 0)
        {
          if(newR-oldR == 1 && newC-oldC == 1)
            return true;
        }
        else if(oldC+1 <= 7)
        {
          if(newR-oldR == 1 && newC-oldC == -1)
            return true;
        }
      }
    }
    else 
    {
      if(currentPlayer.equals("Black"))
      {
        if(firstMove == false)
        {
          if(newR-oldR <= 2 && newR-oldR >=1 && oldC == newC)
          {
            return true;
          }
        }
        else
        {
          if(newR-oldR == 1 && oldC == newC)
            return true;
        }
      }
    }
    
    if(super.getBoard().getPieceAt(newLocation) != null)
    {
      if(currentPlayer.equals("White") && super.getBoard().getPieceAt(newLocation).getPlayer().equals("Black"))
      {
        if(oldC-1 >= 0)
        {
          if(oldR-newR == 1 && oldC-newC == 1)
            return true;
        }
        else if(oldC+1 <= 7)
        {
          if(oldR-newR == 1 && oldC-newC == -1)
            return true;
        }
      }
    }
    else
    {
      if(currentPlayer.equals("White"))
      {
        if(firstMove == false)
        {
          if(oldR-newR <= 2 && oldR-newR >=1 && oldC == newC)
          {
            return true;
          }
        }
        else
        {
          if(oldR-newR == 1 && oldC == newC)
            return true;
        }
      }
    }
    return false;
  }
  
  //Update the threatening location
  public void updateThreateningLocation(ChessLocation newLocation)
  {
    ChessBoard board = super.getBoard();
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    
    if(board.getBoard()[row][col].getPlayer().equals("Black"))
    {
      if(row+1 <= 7 && col-1 >=0 && col+1 <=7)
      {
        if(board.isPieceAt(row+1,col-1))
        {
          if(!board.getBoard()[row+1][col-1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+1,col-1)))
            super.getThreatArr().add(new ChessLocation(row+1,col-1));
        }
        if(board.isPieceAt(row+1,col+1))
        {
          if(!board.getBoard()[row+1][col+1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+1,col+1)))
            super.getThreatArr().add(new ChessLocation(row+1,col+1));
        }
      }
    }
    else if(board.getBoard()[row][col].getPlayer().equals("White"))
    {
      if(row-1 >= 0 && col-1 >=0 && col+1<=7)
      {
        if(board.isPieceAt(row-1,col-1))
        {
          if(!board.getBoard()[row-1][col-1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-1,col-1)))
            super.getThreatArr().add(new ChessLocation(row-1,col-1));
        }
        if(board.isPieceAt(row-1,col+1))
        {
          if(!board.getBoard()[row-1][col+1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-1,col+1)))
            super.getThreatArr().add(new ChessLocation(row-1,col+1));      
        }
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