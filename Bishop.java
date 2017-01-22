class Bishop extends ChessPiece
{
  //Current Player
  String currentPlayer;
  
  //Constructor for Bishop
  public Bishop(String player, ChessGame game, ChessLocation initial_location)
  {
    //Initialize the super class
    super(player, initial_location, game);
    
    //Black chess = upper case letter, White chess = lower case letter
    if(player.equals("Black"))
      this.id = 'B';
    else if(player.equals("White"))
      this.id = 'b';
    currentPlayer = player; //Identify the player
    super.assignLocation(initial_location); //Assign the location to the initial_location
  }
  
  // Move the chess to the new location given by the user
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
        //Error Message
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
    //Reference to row and column
    int oldR = this.getLocation().getRow();
    int oldC = this.getLocation().getCol();
    int newR = newLocation.getRow();
    int newC = newLocation.getCol();
    
    //They must be none zero, because can't divide 0
    if(oldC-newC != 0)
    {
      if((oldR-newR)/(oldC-newC) == 1 || (oldR-newR)/(oldC-newC) == -1)
        return true;   
    }
    return false;
  }
  
  public void updateThreateningLocation(ChessLocation newLocation)
  {
    //Reference to row and column
    int newR = newLocation.getRow();
    int newC = newLocation.getCol();
    
    //Obtain the reference of the board
    ChessBoard board = super.getBoard();
    
    // Diagonal Check by using for loop
    for(int i=0; i<8; i++)
    {
      for(int j=0; j<8;j++)
      {
        if(j - newC != 0)
        {
          //Diagional value occurs in increment of 1 in row and column
          if((i-newR)/(j-newC) == 1 || (i-newR)/(j-newC) == -1)
          {
            ChessLocation loc = new ChessLocation(i,j);
            if(board.isPieceAt(i,j) && !board.getPieceAt(loc).getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, loc))
              super.getThreatArr().add(loc);
          }
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