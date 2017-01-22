import java.util.ArrayList;
abstract class ChessPiece implements ChessPieceInterface
{
  //Local Variables
  private ChessGame game;
  private String player;
  private ChessLocation location;  
  protected char id;
  private ChessBoard board;
  private ArrayList<ChessLocation> threateningLocations;
  
  //Constructor
  public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game)
  {
    this.player = owner;
    this.game = game;
    this.location = null;
    board = game.getBoard();
    board.placePieceAt(this, initialLocation);
    threateningLocations = new ArrayList<ChessLocation>();
  }
  
  //Move the piece to given location
  public boolean moveTo(ChessLocation newLocation)
  {
    if(!board.isPieceAt(newLocation.getRow(),newLocation.getCol()))
    {
      game.getBoard().placePieceAt(this, newLocation); //Place the piece
      game.getBoard().removePiece(location);//Remove the piece from the old location    
      location = newLocation; //Assign the new location 
      return true;
    }
    else if(board.isPieceAt(newLocation.getRow(), newLocation.getCol()))
    {
      // New Location's Player and Old Location's Player
      String newLoPlayer = board.getPieceAt(newLocation).getPlayer();
      String oldLoPlayer = board.getPieceAt(location).getPlayer();
      if(!newLoPlayer.equals(oldLoPlayer))
      {
        game.getBoard().placePieceAt(this, newLocation); //Place the piece
        game.getBoard().removePiece(location);//Remove the piece from the old location    
        location = newLocation; //Assign the new location 
        return true;
      }
    }
    
    return false;
  }
  
  //Check for shadows move
  protected boolean checkLineOfSight(ChessLocation start,ChessLocation end)
  {
    int startR = start.getRow();
    int startC = start.getCol();
    int endR = end.getRow();
    int endC = end.getCol();
    
    //Verticle and Horizontal Check
    if(startR < endR && startC == endC)
    {
      for(int i=startR+1; i<endR; i++)
      {
        if(board.isPieceAt(i,startC))
          return true;
      }
    }
    else if(startR > endR && startC == endC)
    {
      for(int i=startR-1; i>endR; i--)
      {
        if(board.isPieceAt(i,startC))
          return true;
      }
    }
    else if(startR == endR && startC < endC)
    {
      for(int i=startC+1; i<endC; i++)
      {
        if(board.isPieceAt(startR,i))
          return true;
      }
    }
    else if(startR == endR && startC > endC)
    {
      for(int i=startC-1; i>endC; i--)
      {
        if(board.isPieceAt(startR,i))
          return true;
      }      
    }
    
    //Diagonal check
    if(startC-endC != 0)
    {
      if((startR-endR)/(startC-endC) == 1 || (startR-endR)/(startC-endC) == -1)
      {
        if(startR > endR && startC > endC)
        {
          for(int i=startR; i>endR+1; i--)
          {
            if(game.getBoard().isPieceAt(startR-1,startC-1))            
              return true;
            startC--;
          }
        }
        else if(startR > endR && startC < endC)
        {
          for(int i=startR; i>endR+1; i--)
          {
            if(game.getBoard().isPieceAt(startR-1,startC+1))            
              return true;
            startC++;
          }
        }
        else if(startR < endR && startC > endC)
        {
          for(int i=startR; i<endR-1; i++)
          {
            if(game.getBoard().isPieceAt(startR+1,startC-1))            
              return true;
            startC--;
          }
        }
        else if(startR < endR && startC < endC)
        {
          for(int i=startR; i<endR-1; i++)
          {
            if(game.getBoard().isPieceAt(startR+1,startC+1))            
              return true;
            startC++;
          }
        }
      }
    }
    return false;
  }
  
  //Update the threatening location 
  public abstract void updateThreateningLocation(ChessLocation newLocation);
  
  //Update the existing array to an new array 
  public void updateToNewArray()
  {
    threateningLocations = new ArrayList<ChessLocation>();
  }
  //Accessors and mutators methods for the private variables
  public char getChar()
  {
    return this.id;
  }
  public String getPlayer()
  {
    return player;
  }
  public ChessLocation getLocation()
  {
    return location;
  }
  
  public void assignLocation(ChessLocation newLocation)
  {
    location = newLocation;
  }
  
  public ChessBoard getBoard()
  {
    return board;   
  }
  
  public ArrayList<ChessLocation> getThreatArr()
  {
    return threateningLocations;
  }
  
  public abstract ChessPiece check();
  //Accessors and mutators methods for the private variables
}