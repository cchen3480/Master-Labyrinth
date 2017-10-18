package code;
/**
 * Location.class - Class that maintains a col/row pair that represents
 * a location on the Game.gameBoard[][].
 * @author Brian Badura 
 * @author Kevin Chan
 *
 */
public class Location
{
    /**
     * int col, also the X-value (0 - 6 from left to right).
     */
    private int col;
    
    /**
     * int row, also the Y-value (0 - 6 from top to bottom).
     */
    private int row;
    
    /**
     * Constructor.
     * Location stored as [col, row] to match [x, y]. Columns are counted
     * left to right and Rows are counted top to bottom.
     * @param c Column [X].
     * @param r Row [Y].
     */
    public Location(int c, int r){
        col = c;
        row = r;
    }
    
   /**
    * 
    * @return Column index of this location.
    */
    public int getCol(){
        return col;
    }
    
    /**
     * Set column index of this location.
     * @param c Column index.
     */
    public void setCol(int c){
        col = c;
    }
    
    /**
     * 
     * @return Row index of this location.
     */
    public int getRow(){
        return row;
    }
    
    /**
     * Set row index of this location.
     * @param r Row index.
     */
    public void setRow(int r){
        row = r;
    }
    
    /**
     * Compares two locations for geographical equality.
     * @param l Location being compared to this location.
     * @return True if locations are equal, false otherwise.
     */
    public boolean equals(Location l){
        if (this.getCol() == l.getCol() && this.getRow() == l.getRow()){
            return true;
        }
        return false;
    }
    
    /**
     * Converts Location to String
     * @return Location in the form "[col][row]"
     */
    public String toString(){
        return ("[" + col + ", " + row + "]");
    }

}
