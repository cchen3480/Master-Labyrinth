package code;

/**
 * Tile.class - Tiles make up the board. Each tile has a boolean value representing the 
 * directions that their paths point. 
 * @author Kevin Chan
 * @author Yong Chen
 * @author Brian Badura
 *
 */
public class Tile
{
    /**
     * boolean Direction variables.
     */
    boolean goesUp, goesRight, goesDown, goesLeft;
    
    /**
     * Constructor. Initializes Tile path.
     * @param u True if the Tile has an 'up' path, false otherwise.
     * @param r True if the Tile has a 'right' path, false otherwise.
     * @param d True if the Tile has a 'down' path, false otherwise.
     * @param l True if the Tile has a 'left' path, false otherwise.
     */
    public Tile(boolean u, boolean r, boolean d, boolean l){
        goesUp = u;
        goesRight = r;
        goesDown = d;
        goesLeft = l;
    }
    
    /**
     * goesUp Getter.
     * @return boolean goesUp.
     */
    public boolean goesUp(){
        return goesUp;
    }
    
    /**
     * goesRight Getter.
     * @return boolean goesRight.
     */
    public boolean goesRight(){
        return goesRight;
    }
    
    /**
     * goesDown Getter.
     * @return boolean goesDown.
     */
    public boolean goesDown(){
        return goesDown;
    }
    
    /**
     * goesLeft Getter.
     * @return boolean goesLeft.
     */
    public boolean goesLeft(){
        return goesLeft;
    }
    
    /**
     * Rotates the Tile representation 90 degrees clockwise by rotating 
     * boolean variables.
     */
    public void rotateClockwise(){
        boolean temp = goesUp;
        goesUp = goesLeft;
        goesLeft = goesDown;
        goesDown = goesRight;
        goesRight = temp;
    }
    
    /**
     * Rotates the Tile representation 90 degrees counter-clockwise by rotating 
     * boolean variables.
     */
    public void rotateCounterClockwise(){
        boolean temp = goesUp;
        goesUp = goesRight;
        goesRight = goesDown;
        goesDown = goesLeft;
        goesLeft = temp;
    }
    
    /**
     * Checks whether two tiles have the same  paths.
     * @param t Tile object being ccompared.
     * @return True if the tile paths are the same, false otherwise.
     */
    public boolean equals(Tile t){
        if (this.goesUp() == t.goesUp())
            if (this.goesRight() == t.goesRight())
                if (this.goesDown() == t.goesDown())
                    if (this.goesLeft() == t.goesLeft())
                        return true;
        return false;
        
    }
}
