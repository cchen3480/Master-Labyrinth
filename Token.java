package code;

/**
 * Token.class - Token object.
 * @author Brian Badura
 * @author Brian Coyne
 *
 */
public class Token
{
    /**
     * The game value of the Token.
     */
    private int tokenValue;
    
    /**
     * The location of the Token on the gameBoard.
     */
    private Location tokenLocation;
    
    /**
     * Constructor.
     * @param val The value of the Token.
     */
    public Token(int val){
        tokenValue = val;
    }
    
    /**
     * tokenValue Getter.
     * @return tokenValue as int.
     */
    public int getTokenValue(){
        return tokenValue;
    }
    
    /**
     * tokenLocation Setter.
     * @param l the new Location.
     */
    public void setLocation(Location l){
        tokenLocation = l;
    }
    
    /**
     * tokenLocation Getter.
     * @return tokenLocation as Location.
     */
    public Location getLocation(){
        return tokenLocation;
    }
    
    /**
     * Moves the location up one space; Added to the bottom of the 
     * board if pushed off.
     */
    public void moveUp(){
        int r = this.getLocation().getRow() - 1;
        if (r == -1)
            r = 6;
        this.setLocation(new Location(this.getLocation().getCol(), r));
    }
    
    /**
     * Moves the location down one space; Added to the top of the 
     * board if pushed off.
     */
    public void moveDown(){
        int r = this.getLocation().getRow() + 1;
        if (r == 7)
            r = 0;
        this.setLocation(new Location(this.getLocation().getCol(), r));
    }
    
    /**
     * Moves the location right one space; Added to the left of the 
     * board if pushed off.
     */
    public void moveRight(){
        int c = this.getLocation().getCol() + 1;
        if (c == 7)
            c = 0;
        this.setLocation(new Location(c, this.getLocation().getRow()));
    }
    
    /**
     * Moves the location left one space; Added to the right of the 
     * board if pushed off.
     */
    public void moveLeft(){
        int c = this.getLocation().getCol() - 1;
        if (c == -1)
            c = 6;
        this.setLocation(new Location(c, this.getLocation().getRow()));
    }
}
