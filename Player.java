package code;

import java.awt.Color;

/**
 * Player.java - Class which contains and manipulates data about the Players.
 * @author Brian Coyne
 * @author Yong Chen
 * @author Brian Badura
 *
 */
public class Player
{
    /**
     * Location of this player as Location.
     */
    private Location playerLocation;
    
    /**
     * Red = 1, Blue = 2, White = 3, Yellow = 4.
     */
    private int playerNumber;
    
    /**
     * Color of the player as Color.
     */
    private Color playerColor;
    
    /**
     * Constructor.
     * @param num Player Number.
     * @param l Player Location.
     * @param c Player Color.
     */
    public Player(int num, Location l, Color c){
        playerNumber = num;
        playerLocation = l;
        playerColor = c;
    }
    
    /**
     * playerLocation Getter.
     * @return current Player location.
     */
    public Location getLocation(){
        return playerLocation;
    }
    
    /**
     * playerNumber Getter.
     * @return current Player Number.
     */
    public int getPlayerNumber(){
        return playerNumber;
    }
    
    /**
     * playerNumber Setter.
     * @param n new Player Number.
     */
    public void setPlayerNumer(int n){
        playerNumber = n;
    }
    
    /**
     * playerColor Getter.
     * @return current Player Color.
     */
    public Color getPlayerColor(){
        return playerColor;
    }
    
    /**
     * playerColor Setter.
     * @param c new Player Color.
     */
    public void setPlayerColor(Color c){
        playerColor = c;
    }
    
    /**
     * 'moveUp()' subtracts 1 from the Player's current row index.
     * If row is 0, it is reset to 6.
     * @author Yong Chen
     */
    public void moveUp(){
        int row = this.getLocation().getRow();
        if (row == 0){
            row = 6;
        }
        else{
            row = row - 1;
        }
        this.getLocation().setRow(row);
    }
    
    /**
     * Determines whether the Player would be able to move up given
     * the currentBoard.
     * @author Brian Badura
     * @author Brian Coyne
     * @param currentBoard the current tileBoard of the game.
     * @return True if the currentTile and the destinationTile connect
     * to one another in terms of their paths, and false otherwise.
     */
    public boolean canMoveUp(Tile[][] currentBoard){
        int row = this.getLocation().getRow();
        if (row == 0){
            row = 6;
        }
        else{
            row -= 1;
        }
        if (currentBoard[this.getLocation().getCol()][this.getLocation().getRow()].goesUp() 
            && currentBoard[this.getLocation().getCol()][row].goesDown()){
            return true;
        }
        return false;
    }
    
    /**
     * 'moveDown()' adds 1 to the Player's current row index.
     * If row is 6, it is reset to 0.
     * @author Yong Chen
     */
    public void moveDown(){
        int row = this.getLocation().getRow();
        if (row == 6){
            row = 0;
        }
        else{
            row = row + 1;
        }
        this.getLocation().setRow(row);
    }
    
    /**
     * Determines whether the Player would be able to move down given
     * the currentBoard.
     * @author Brian Badura
     * @author Brian Coyne
     * @param currentBoard the current tileBoard of the game.
     * @return True if the currentTile and the destinationTile connect
     * to one another in terms of their paths, and false otherwise.
     */
    public boolean canMoveDown(Tile[][] currentBoard){
        int row = this.getLocation().getRow();
        if (row == 6){
            row = 0;
        }
        else{
            row += 1;
        }
        if (currentBoard[this.getLocation().getCol()][this.getLocation().getRow()].goesDown() 
            && currentBoard[this.getLocation().getCol()][row].goesUp()){
            return true;
        }
        return false;
    }
    
    /**
     * 'moveRight()' adds 1 to the Player's current column index.
     * If column is 6, it is reset to 0.
     * @author Yong Chen
     */
    public void moveRight(){
        int col = this.getLocation().getCol();
        if (col == 6){
            col = 0;
        }
        else{
            col = col + 1;
        }
        this.getLocation().setCol(col);
    }
    
    /**
     * Determines whether the Player would be able to move right given
     * the currentBoard.
     * @author Brian Badura
     * @author Brian Coyne
     * @param currentBoard the current tileBoard of the game.
     * @return True if the currentTile and the destinationTile connect
     * to one another in terms of their paths, and false otherwise.
     */
    public boolean canMoveRight(Tile[][] currentBoard){
        int col = this.getLocation().getCol();
        if (col == 6){
            col = 0;
        }
        else{
            col += 1;
        }
        if (currentBoard[this.getLocation().getCol()][this.getLocation().getRow()].goesRight() 
            && currentBoard[col][this.getLocation().getRow()].goesLeft()){
            return true;
        }
        return false;
    }
    
    /**
     * 'moveLeft()' subtracts 1 from the Player's current column index.
     * If column is 0, it is reset to 6.
     * @author Yong Chen
     */
    public void moveLeft(){
        int col = this.getLocation().getCol();
        if (col == 0){
            col = 6;
        }
        else{
            col = col - 1;
        }
        this.getLocation().setCol(col);
    }
    
    /**
     * Determines whether the Player would be able to move left given
     * the currentBoard.
     * @author Brian Badura
     * @author Brian Coyne
     * @param currentBoard the current tileBoard of the game.
     * @return True if the currentTile and the destinationTile connect
     * to one another in terms of their paths, and false otherwise.
     */
    public boolean canMoveLeft(Tile[][] currentBoard){
        int col = this.getLocation().getCol();
        if (col == 0){
            col = 6;
        }
        else{
            col -= 1;
        }
        if (currentBoard[this.getLocation().getCol()][this.getLocation().getRow()].goesLeft() 
            && currentBoard[col][this.getLocation().getRow()].goesRight()){
            return true;
        }
        return false;
    }

}

