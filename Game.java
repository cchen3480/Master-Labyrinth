package code;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game.class - This class will basically control the entire back end of the project.
 * @author Brian Badura
 * @author Yong Chen 
 * @author Kevin Chan
 * @author Brian Coyne
 *
 */
public class Game
{
    /**
     * 2d array of Tile representing the gameBoard.
     * @author Brian
     */
    private Tile[][] tileBoard;
    
    /**
     * Tile storing the current Extra Tile.
     */
    private Tile extraTile;
    
    /**
     * ArrayList<Player> containing references to all Players in order.
     */
    private ArrayList<Player> allPlayers;
    
    /**
     * int currentPlayerIndex representing whose turn it is.
     * Meant to be used in conjunction with ArrayList<Player> allPlayers.
     */
    private int currentPlayerIndex;
    
    /**
     * Constructor.
     */
    public Game(){
        initializeTiles();
        initializePlayers();
    }
    
    /**
     * Initializes the tileBoard, in addition to the extraTile.
     * @author Brian Coyne
     * @author Yong Chen
     * @author Brian Badura
     */
    public void initializeTiles(){
        tileBoard = new Tile[7][7];
        //Create the fixed tiles
        //Row 0
        tileBoard[0][0] = new Tile(false, true, true, false);
        tileBoard[2][0] = new Tile(false, true, true, true);
        tileBoard[4][0] = new Tile(false, true, true, true);
        tileBoard[6][0] = new Tile(false, false, true, true);
        //Row 2
        tileBoard[0][2] = new Tile(true, true, true, false);
        tileBoard[2][2] = new Tile(true, true, true, false);
        tileBoard[4][2] = new Tile(false, true, true, true);
        tileBoard[6][2] = new Tile(true, false, true, true);
        //Row 4
        tileBoard[0][4] = new Tile(true, true, true, false);
        tileBoard[2][4] = new Tile(true, true, false, true);
        tileBoard[4][4] = new Tile(true, false, true, true);
        tileBoard[6][4] = new Tile(true, false, true, true);
        //Row 6
        tileBoard[0][6] = new Tile(true, true, false, false);
        tileBoard[2][6] = new Tile(true, true, false, true);
        tileBoard[4][6] = new Tile(true, true, false, true);
        tileBoard[6][6] = new Tile(true, false, false, true);
        
        //Now create the unfixed tiles, plus the extra tile (15 corners, 6 t's, 13 lines)
        ArrayList<Tile> tileBag = new ArrayList<Tile>();
        Random r = new Random();
        for (int x = 0; x < 15; x++){
            tileBag.add(new Tile(true, true, false, false));
        }
        for (int x = 0; x < 6; x++){
            tileBag.add(new Tile(true, true, true, false));
        }
        for (int x = 0; x < 13; x++){
            tileBag.add(new Tile(true, false, true, false));
        }
        //Randomize Orientation
        for (int x = 0; x < tileBag.size(); x++){
            int rand = r.nextInt(4);
            for (int y = 0; y <= rand; y++){
                tileBag.get(x).rotateClockwise();
            }
        }
        
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 7; y++){
                if (tileBoard[x][y] == null){
                    tileBoard[x][y] = tileBag.remove(r.nextInt(tileBag.size()));
                }
            }
        }
        extraTile = tileBag.remove(0);
    }
    
    /**
     * Initilaizes the Players and adds them to the allPlayers ArrayList.
     * @author Kevin Chan
     */
    public void initializePlayers(){
        allPlayers = new ArrayList<Player>();
        currentPlayerIndex = 0;
        Player redPlayer = new Player(1, new Location(2, 2), Color.RED);
        Player bluePlayer = new Player(2, new Location(4, 2), Color.BLUE);
        Player whitePlayer = new Player(3, new Location(2, 4), Color.WHITE);
        Player yellowPlayer = new Player(4, new Location(4, 4), Color.YELLOW);
        allPlayers.add(redPlayer);
        allPlayers.add(bluePlayer);
        allPlayers.add(whitePlayer);
        allPlayers.add(yellowPlayer);
    }
    
    /**
     * inserExtraTile(c, r) accepts two ints (column & row) and attempts 
     * to insert the extraTile into the given location. 
     * @author Brian Badura
     * @author Brian Coyne
     * @param c Column index where the extraTile will be inserted.
     * @param r Row index where the extraTile will be inserted.
     * @return True if the method accepted the input (not a fixed tile location).
     *         False otherwise.
     */
    public boolean insertExtraTile(int c, int r){
        Tile temp;
        //Inserted from the top
        if (c % 2 == 1 && r == 0){
            temp = tileBoard[c][6];
            for (int i = 6; i >= 1; i--){
                tileBoard[c][i] = tileBoard[c][i - 1];
            }
            tileBoard[c][0] = extraTile;
            extraTile = temp;
            return true;
        }
        //Inserted from the right
        if (c == 6 && r % 2 == 1){
            temp = tileBoard[0][r];
            for (int i = 0; i < 6; i++){
                tileBoard[i][r] = tileBoard[i + 1][r];
            }
            tileBoard[6][r] = extraTile;
            extraTile = temp;
            return true;
        }
        //Inserted from the bottom
        if (c % 2 == 1 && r == 6){
            temp = tileBoard[c][0];
            for (int i = 0; i < 6; i++){
                tileBoard[c][i] = tileBoard[c][i + 1];
            }
            tileBoard[c][6] = extraTile;
            extraTile = temp;
            return true;
        }
        //Inserted from the left
        if (c == 0 && r % 2 == 1){
            temp = tileBoard[6][r];
            for (int i = 6; i >= 1; i--){
                tileBoard[i][r] = tileBoard[i - 1][r];
            }
            tileBoard[0][r] = extraTile;
            extraTile = temp;
            return true;
        }
        return false;
    }
    
    /**
     * tileBoard Getter.
     * @return tileBoard 2D array of tiles.
     */
    public Tile[][] getTileBoard(){
        return tileBoard;
    }
    
    /**
     * tileBoard Setter.
     * @param tb new tileBoard.
     */
    public void setTileBoard(Tile[][] tb){
        tileBoard = tb;
    }
    
    /**
     * extraTile Getter.
     * @return extraTile as Tile.
     */
    public Tile getExtraTile(){
        return extraTile;
    }
    
    /**
     * extraTile Setter.
     * @param t new extraTile.
     */
    public void setExtraTile(Tile t){
        extraTile = t;
    }
    
    /**
     * allPlayers Getter.
     * @return allPlayers as ArrayList<Player>.
     */
    public ArrayList<Player> getAllPlayers(){
        return allPlayers;
    }
    
    /**
     * allPlayers Setter.
     * @param p new allPlayers array list.
     */
    public void setAllPlayers(ArrayList<Player> p){
        allPlayers = p;
    }
    
    /**
     * currentPlayerIndex Getter. This number represents whose turn 
     * it is when used with ArrayList<Player> allPlayers.
     * @return currentPlayerIndex as int.
     */
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }
    
    /**
     * currentPlayerIndex Setter.
     * @param i new currentPlayerIndex.
     */
    public void setCurrentPlayerIndex(int i){
        currentPlayerIndex = i;
    }

}

