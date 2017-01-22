import java.util.ArrayList;
class King extends ChessPiece
{
  //Local variables
  String currentPlayer;
  ArrayList<ChessLocation> possibleMove = new ArrayList<ChessLocation>();
  
  //Constructor for King
  public King(String player, ChessGame game, ChessLocation initial_location)
  {
    //Initialize the super class
    super(player, initial_location, game);
    
    //Black chess = upper case letter, White chess = lower case letter
    if(player.equals("Black"))
      this.id = 'K';
    else if(player.equals("White"))
      this.id = 'k';
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
        //If the location is not threatening by a piece, then move to the new location
        if(locationInDanger(newLocation) == null)
        {
          super.moveTo(newLocation);
          return true;
        }
        else 
        {
          System.out.println("Error: This location is being threatened by " + locationInDanger(newLocation).getChar());
          return false;
        }
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
  
  //Check if the move is possible 
  public boolean isValid(ChessLocation newLocation)
  {
    int oldR = this.getLocation().getRow();
    int oldC = this.getLocation().getCol();
    int newR = newLocation.getRow();
    int newC = newLocation.getCol();
    
    //If statement according to the move of King
    if(((newR - oldR) <= 1 &&(newR - oldR) >= -1) && ((newC - oldC) <= 1 && (newC - oldC) >= -1))
    {
      if(!super.checkLineOfSight(this.getLocation(),newLocation))
        return true;     
      else
        System.out.println("There's a piece blocking the way, please try again\n");
    } 
    return false;
  }
  
  //Update the threatening location
  public void updateThreateningLocation(ChessLocation newLocation)
  {
    int row = newLocation.getRow();
    int col = newLocation.getCol();
    ChessBoard board = super.getBoard();
    
    if(row-1 >=0 && col-1>=0 && col+1 <=7)
    {
      for(int j=col-1; j<=col+1; j++)
      {
        if(board.isPieceAt(row-1, j) && !board.getBoard()[row-1][j].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row-1,j)))
          super.getThreatArr().add(new ChessLocation(row-1,j));
      }
    }
    if(row+1<=7 && col-1>=0 && col+1 <=7)
    {
      for(int j=col-1; j<=col+1; j++)
      {
        if(board.isPieceAt(row+1, j) && !board.getBoard()[row+1][j].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row+1,j)))
          super.getThreatArr().add(new ChessLocation(row+1,j));
      }
    }
    
    if(col-1>=0)
    {
      if(board.isPieceAt(row, col-1) && !board.getBoard()[row][col-1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row,col-1)))
        super.getThreatArr().add(new ChessLocation(row,col-1));
    }
    if(col+1 <=7)
    {
      if(board.isPieceAt(row,col+1) && !board.getBoard()[row][col+1].getPlayer().equals(currentPlayer) && !super.checkLineOfSight(newLocation, new ChessLocation(row,col+1)))
        super.getThreatArr().add(new ChessLocation(row,col+1));
    }
    
  }
  
  //Loop each piece in the board, once a location occured in array, return the piece
  public ChessPiece locationInDanger(ChessLocation destinationLocation)
  {
    super.updateToNewArray();
    ChessBoard board = super.getBoard();
    for(int i=0; i<8; i++)
    {
      for(int j=0; j<8; j++)
      {
        if(board.getBoard()[i][j] != null)
        {
          if(!board.getBoard()[i][j].getPlayer().equals(currentPlayer))
          {
            ChessLocation loc = new ChessLocation(i,j);
            board.getPieceAt(loc).updateThreateningLocation(loc);
            if(super.getThreatArr().contains(destinationLocation))
              return board.getPieceAt(loc);
          }
        }
      }
    }
    return null;
  }
  
  //Once all posible moves occur in threatening location, it returns true
  public boolean anyMovesLeft()
  {
    int row = super.getLocation().getRow();
    int col = super.getLocation().getCol();
    
    if(col-1 >=0)
    {      
      possibleMove.add(new ChessLocation(row, col-1));
    }
    if(col+1 <=7)
    {
      possibleMove.add(new ChessLocation(row, col+1));
    }
    if(row-1 >=0 && col-1>=0 && col+1 <=7)
    {
      for(int j=col-1; j<=col+1; j++)
      {
        possibleMove.add(new ChessLocation(row, col));
      }                 
    }
    if(row+1<=7 && col-1>=0 && col+1 <=7)
    {
      for(int j=col-1; j<=col+1; j++)
      {
        possibleMove.add(new ChessLocation(row+1, col));
      }  
    }
    
    locationInDanger(super.getLocation());
    if(super.getThreatArr().containsAll(possibleMove))
    {
      return true;
    }
    return false;
  }
  
  //Return the piece that is in danger(threatening by a piece)
  public ChessPiece check()
  {    
    return locationInDanger(super.getLocation());  
  }
  
  //Return the current player
  public String getPlayer()
  {
    return currentPlayer;
  }
}