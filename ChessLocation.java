class ChessLocation
{
  private int row;
  private int col;
  
  //Constructor method for class ChessLocation
  public ChessLocation(int inputRow, int inputColumn)
  {
    row = inputRow;
    col = inputColumn;
  }
  
  //Check if the given location has the same row and column as that of itself
  public boolean equals(ChessLocation cp)
  {
    if(cp.getRow() == this.row && cp.getCol() == this.col)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //Accessors and mutators methods for the private variables
  public int getRow()
  {
    return row;
  }
  public void assignRow(int r)
  {
    row = r;
  }
  public int getCol()
  {
    return col;
  } 
  public void assignCol(int c)
  {
    col = c;
  }
  //Accessors and mutators methods for the private variables
}