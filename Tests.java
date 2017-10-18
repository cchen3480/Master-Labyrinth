package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class Tests
{
    @Test
    public void testInitializeTileBoard_FixedTiles(){
        //Checking the other fixed tiles (edges of the board).
        code.Game myGame = new code.Game();
        code.Tile tileBoard[][] = myGame.getTileBoard();
        code.Tile TLCorner = new code.Tile(false, true, true, false);
        code.Tile alongTop = new code.Tile(false, true, true, true);
        code.Tile TRCorner = new code.Tile(false, false, true, true);
        code.Tile alongRight = new code.Tile(true, false, true, true);
        code.Tile BRCorner = new code.Tile(true, false, false, true);
        code.Tile alongBottom = new code.Tile(true, true, false, true);
        code.Tile BLCorner = new code.Tile(true, true, false, false);
        code.Tile alongLeft = new code.Tile(true, true, true, false);
        boolean all = tileBoard[0][0].equals(TLCorner) && 
                      tileBoard[2][0].equals(alongTop) && tileBoard[4][0].equals(alongTop) &&
                      tileBoard[6][0].equals(TRCorner) && 
                      tileBoard[6][2].equals(alongRight) && tileBoard[6][4].equals(alongRight) &&
                      tileBoard[6][6].equals(BRCorner) && 
                      tileBoard[4][6].equals(alongBottom) && tileBoard[2][6].equals(alongBottom) &&
                      tileBoard[0][6].equals(BLCorner) && 
                      tileBoard[0][4].equals(alongLeft) && tileBoard[0][2].equals(alongLeft);
        assertTrue("Fixed tile paths met game specifications: " + all, all);
    }
    
    @Test
    public void testInitializeTileBoard_UnfixedTiles(){
        //Checking to make sure each unfixed tile has only 2 or 3 paths.
        code.Game myGame = new code.Game();
        code.Tile[][] tileBoard = myGame.getTileBoard();
        code.Tile extraTile = myGame.getExtraTile();
        
        java.util.ArrayList<code.Tile> unfixedTiles = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++){
            for (int r = 0; r < 7; r++){
                if (c % 2 == 1){
                    unfixedTiles.add(tileBoard[c][r]);
                }   
                else{
                    if (r % 2 == 1){
                        unfixedTiles.add(tileBoard[c][r]);
                    }
                }
            }
        }
        unfixedTiles.add(extraTile);
        
        int countCorners = 0, countLines = 0, countTs = 0;
        for (int z = 0; z < unfixedTiles.size(); z++){
            code.Tile t = unfixedTiles.get(z);
            if ((t.goesRight() && t.goesLeft() && !(t.goesUp() || t.goesDown())) || 
                (t.goesUp() && t.goesDown() && !(t.goesLeft() || t.goesRight()))){
                countLines += 1;
            }
            else if ((t.goesUp() && t.goesRight() && !(t.goesDown() || t.goesLeft())) ||
                     (t.goesRight() && t.goesDown() && !(t.goesLeft() || t.goesUp())) ||
                     (t.goesDown() && t.goesLeft() && !(t.goesUp() || t.goesRight())) ||
                     (t.goesLeft() && t.goesUp() && !(t.goesDown() || t.goesRight()))){
                countCorners += 1;
            }
            else if ((t.goesUp() && t.goesRight() && t.goesDown()  && !(t.goesLeft())) ||
                     (t.goesRight() && t.goesDown() && t.goesLeft() && !(t.goesUp())) ||
                     (t.goesDown() && t.goesLeft() && t.goesUp() && !(t.goesRight())) ||
                     (t.goesLeft() && t.goesUp() && t.goesRight() && !(t.goesDown()))){
                countTs += 1;
            }
        }
        boolean allValidTiles = (countCorners == 15) && (countLines == 13) && (countTs == 6);
        assertTrue("The tiles to do not match the rules of the Game (15 C's, 6 T's, 13 L's)", allValidTiles);
    }
    
    @Test
    public void testInitializeTileBoard_PlayerSpawns(){
        //Checking the player spawn tiles for the correct paths.
        code.Game myGame = new code.Game();
        code.Tile[][] tileBoard = myGame.getTileBoard();
        boolean redSpawn = false;
        boolean blueSpawn = false;
        boolean whiteSpawn = false;
        boolean yellowSpawn = false;
        code.Tile expRedSpawn = new code.Tile(true, true, true, false);
        code.Tile expBlueSpawn = new code.Tile(false, true, true, true);
        code.Tile expWhiteSpawn = new code.Tile(true, true, false, true);
        code.Tile expYellowSpawn = new code.Tile(true, false, true, true);
        if (tileBoard[2][2].equals(expRedSpawn))
            redSpawn = true;
        if (tileBoard[4][2].equals(expBlueSpawn))
            blueSpawn = true;
        if (tileBoard[2][4].equals(expWhiteSpawn))
            whiteSpawn = true;
        if (tileBoard[4][4].equals(expYellowSpawn))
            yellowSpawn = true;
        boolean all = redSpawn && blueSpawn && whiteSpawn && yellowSpawn;
        assertTrue("Player Spawn tile paths met game specifications: " + all, all);
    }
    
    //---------------------------------------------------------------------------------------------------
    //The next 8 tests create a board scenario and moves the player according to the given 
    //path. After the moves, it then compares the final Player location with the final expected location.
    
    //Red Player starts at [2, 2]
    @Test
    public void testPawnMovement_01(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(5, 0);
        testBoard[3][2] = new code.Tile(false, true, false, true);
        testBoard[4][2] = new code.Tile(true, false, false, true);
        testBoard[4][1] = new code.Tile(true, true, true, false);
        testBoard[5][1] = new code.Tile(true, true, false, true);
        testBoard[5][0] = new code.Tile(false, true, true, false);
        code.Player redPlayer = myGame.getAllPlayers().get(0);
        //Red Player
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveUp(testBoard))
            redPlayer.moveUp();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveUp(testBoard))
            redPlayer.moveUp();
        code.Location finalLoc = redPlayer.getLocation();
        assertTrue("After moving right-right-up-right-up given a valid path, redPlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    @Test
    public void testPawnMovement_02(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(5, 0);
        testBoard[3][2] = new code.Tile(false, true, false, true);
        testBoard[4][2] = new code.Tile(true, false, false, true);
        testBoard[4][1] = new code.Tile(true, false, true, false);
        testBoard[5][1] = new code.Tile(true, true, false, true);
        testBoard[5][0] = new code.Tile(false, true, true, false);
        code.Player redPlayer = myGame.getAllPlayers().get(0);
        //Red Player
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveUp(testBoard))
            redPlayer.moveUp();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveUp(testBoard))
            redPlayer.moveUp();
        code.Location finalLoc = redPlayer.getLocation();
        assertTrue("After moving right-right-up-right-up given a valid path, redPlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end) != true);
    }
    
    @Test
    public void testPawnMovement_03(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(2, 6);
        testBoard[2][3] = new code.Tile(true, false, true, false);
        testBoard[3][4] = new code.Tile(false, false, true, true);
        testBoard[3][5] = new code.Tile(true, false, false, true);
        testBoard[2][5] = new code.Tile(false, true, true, false);
        code.Player redPlayer = myGame.getAllPlayers().get(0);
        //Red Player
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        if (redPlayer.canMoveLeft(testBoard))
            redPlayer.moveLeft();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        code.Location finalLoc = redPlayer.getLocation();
        assertTrue("After moving down-down-right-down-left-down given a valid path, redPlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    @Test
    public void testPawnMovement_04(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(2, 2);
        testBoard[3][2] = new code.Tile(false, false, false, false);
        testBoard[2][3] = new code.Tile(false, false, false, false);
        testBoard[1][2] = new code.Tile(false, false, false, false);
        testBoard[2][1] = new code.Tile(false, false, false, false);
        code.Player redPlayer = myGame.getAllPlayers().get(0);
        //Red Player
        if (redPlayer.canMoveUp(testBoard))
            redPlayer.moveUp();
        if (redPlayer.canMoveRight(testBoard))
            redPlayer.moveRight();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        if (redPlayer.canMoveLeft(testBoard))
            redPlayer.moveLeft();
        if (redPlayer.canMoveDown(testBoard))
            redPlayer.moveDown();
        //^^ALL Invalid moves^^
        code.Location finalLoc = redPlayer.getLocation();
        assertTrue("After moving up-right-down-down-left-down given a valid path, redPlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    //Blue Player starts at [4, 2]
    @Test
    public void testPawnMovement_05(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(0, 0);
        testBoard[3][2] = new code.Tile(true, true, false, false);
        testBoard[3][1] = new code.Tile(true, false, true, false);
        testBoard[3][0] = new code.Tile(false, false, true, true);
        testBoard[1][0] = new code.Tile(false, true, false, true);
        code.Player bluePlayer = myGame.getAllPlayers().get(1);
        //Blue Player this time. Not like it matters- all Players come from the same class anyway.
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        code.Location finalLoc = bluePlayer.getLocation();
        assertTrue("After moving left-up-up-left-left-left given a valid path, bluePlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    @Test
    public void testPawnMovement_06(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(4, 0);
        testBoard[4][1] = new code.Tile(false, false, false, false);
        testBoard[5][2] = new code.Tile(true, false, false, true);
        testBoard[5][1] = new code.Tile(true, false, true, false);
        testBoard[5][0] = new code.Tile(false, false, true, true);
        code.Player bluePlayer = myGame.getAllPlayers().get(1);
        //Blue Player
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        //^^Invalid Move^^
        if (bluePlayer.canMoveRight(testBoard))
            bluePlayer.moveRight();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        code.Location finalLoc = bluePlayer.getLocation();
        assertTrue("After moving up-right-up-up-left given a valid path, bluePlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    @Test
    public void testPawnMovement_07(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(6, 0);
        testBoard[5][2] = new code.Tile(false, true, false, true);
        testBoard[6][1] = new code.Tile(true, false, true, false);
        //Blue Player
        code.Player bluePlayer = myGame.getAllPlayers().get(1);
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        //^^Invalid Move^^
        if (bluePlayer.canMoveRight(testBoard))
            bluePlayer.moveRight();
        if (bluePlayer.canMoveRight(testBoard))
            bluePlayer.moveRight();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        code.Location finalLoc = bluePlayer.getLocation();
        assertTrue("After moving up(i)-right-right-up-up given a valid path, bluePlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    @Test
    public void testPawnMovement_08(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        code.Location end = new code.Location(4, 2);
        testBoard[4][1] = new code.Tile(false, true, false, true);
        testBoard[5][2] = new code.Tile(true, false, true, false);
        testBoard[3][2] = new code.Tile(true, false, true, false);
        testBoard[4][3] = new code.Tile(false, true, false, true);
        //Blue Player
        code.Player bluePlayer = myGame.getAllPlayers().get(1);
        if (bluePlayer.canMoveLeft(testBoard))
            bluePlayer.moveLeft();
        if (bluePlayer.canMoveUp(testBoard))
            bluePlayer.moveUp();
        if (bluePlayer.canMoveDown(testBoard))
            bluePlayer.moveDown();
        if (bluePlayer.canMoveRight(testBoard))
            bluePlayer.moveRight();
        //^^ALL Invalid Moves^^
        code.Location finalLoc = bluePlayer.getLocation();
        assertTrue("After moving left(i)-up(i)-down(i)-right(i) given a valid path, bluePlayer should've reached location "
                   + end.toString() + ", but was found at " + finalLoc.toString(), finalLoc.equals(end));
    }
    
    //-------------------------------------------------------------------------------------------------------------
    
    @Test
    public void testInsertTile_c0_r0(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [0, 0]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(0, 0);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c0_r1(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [0, 1]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][1]);
 
        boolean successful = myGame.insertExtraTile(0, 1);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][1]);
        actual.add(myGame.getExtraTile());
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c0_r2(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [0, 2]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(0, 2);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c0_r3(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [0, 3]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][3]);
 
        boolean successful = myGame.insertExtraTile(0, 3);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][3]);
        actual.add(myGame.getExtraTile());
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c0_r4(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [0, 4]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(0, 4);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c0_r5(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [0, 5]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][5]);
 
        boolean successful = myGame.insertExtraTile(0, 5);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][5]);
        actual.add(myGame.getExtraTile());
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c0_r6(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [0, 6]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(0, 6);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c1_r6(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [1, 6]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[1][r]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(1, 6);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[1][r]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c2_r6(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [2, 6]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(2, 6);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c3_r6(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [3, 6]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[3][r]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(3, 6);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[3][r]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c4_r6(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [4, 6]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(4, 6);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c5_r6(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [5, 6]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[5][r]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(5, 6);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[5][r]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    
    @Test
    public void testInsertTile_c6_r6(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [6, 6]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(6, 6);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c6_r5(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [6, 5]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][5]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(6, 5);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][5]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }

    @Test
    public void testInsertTile_c6_r4(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [6, 4]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(6, 4);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c6_r3(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [6, 3]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][3]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(6, 3);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][3]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c6_r2(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [6, 6]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(6, 2);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c6_r1(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [6, 1]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        for (int c = 0; c < 7; c++)
            expected.add(testBoard[c][1]);
        expected.add(myGame.getExtraTile());
 
        boolean successful = myGame.insertExtraTile(6, 1);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        actual.add(myGame.getExtraTile());
        for (int c = 0; c < 7; c++)
            actual.add(testBoard[c][1]);
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c6_r0(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [6, 0]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(6, 0);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c5_r0(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [5, 0]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[5][r]);
 
        boolean successful = myGame.insertExtraTile(5,  0);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[5][r]);
        actual.add(myGame.getExtraTile());
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c4_r0(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [4, 0]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(4, 0);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c3_r0(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [3, 0]
        
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[3][r]);
 
        boolean successful = myGame.insertExtraTile(3,  0);
        
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[3][r]);
        actual.add(myGame.getExtraTile());
        
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
    
    @Test
    public void testInsertTile_c2_r0(){
        code.Game myGame = new code.Game();
        //Try to insert into a fixed position [2, 0]
        boolean expected = false;
        boolean actual = myGame.insertExtraTile(2, 0);
        assertTrue("Tried to insert into a fixed col/row. Expected false and got " + actual + ".", actual == expected);
    }
    
    @Test
    public void testInsertTile_c1_r0(){
        code.Game myGame = new code.Game();
        code.Tile[][] testBoard = myGame.getTileBoard();
        //Try to insert into an unfixed position [1, 0]
        // *eT == extraTile
        //     ||
        //     ||
        //     \/
        // [d][eT][d][d][d][d][d]    *After* the insertion, in terms of the variable references *prior* to the 
        // [d][b0][d][d][d][d][d]    insertion, we expect the board to look like <-- this (focus on column 1), with the new extra 
        // [d][b1][d][d][d][d][d]    tile [b6] popping off the bottom of the board (given the [1, 0] insertion),
        // [d][b2][d][d][d][d][d]    then being stored as the extra tile.
        // [d][b3][d][d][d][d][d]
        // [d][b4][d][d][d][d][d]    With this, we create the 'expected' ArrayList<Tile> and we populate it in
        // [d][b5][d][d][d][d][d]    the form [eT, b[0], b[1], .... , b[6]]. *Note that the b[6] reference appears
        //     |                     at the end of the ArrayList. This is because the 'actual' ArrayList<Tile> stores
        //    [b6]    the reference of extra tile *after* the insertion at the end of it's list. 
        //           
        // (eT, b[0], b[1], .... , b[6]) <--This is what the arraylist looks like prior to the switch.
        //   ^   ^     ^            ^                        -BECAUSE-
        //   |   |     |            |
        // b[0],b[1], b[2], .... ,  eT   <-- This will be the order in which 'actual' stores its variables *after* the switch,
        //                                   meaning that "b[0](after)" should be the same tile as "eT(prior)", and consequently,
        //                                   "b[n](after)" should the the same tile as "b[n-1](prior)", and also,
        //                                   "eT(after)" should be the same tile as "b[6](prior)"
        //                                  
        java.util.ArrayList<code.Tile> expected = new java.util.ArrayList<code.Tile>();
        expected.add(myGame.getExtraTile());
        for (int r = 0; r < 7; r++)
            expected.add(testBoard[1][r]);
        //
        //        We insert the extra tile at the specified location. Since this method happens
        //        to return a boolean dependent on its ability to complete the insertion, we will
        //        keep track of that value in the 'successful' boolean.
        //
        boolean successful = myGame.insertExtraTile(1,  0);
        //
        //        We create 'actual' and populate it as {b[0], b[1], b[2], .... ,  eT}
        //
        java.util.ArrayList<code.Tile> actual = new java.util.ArrayList<code.Tile>();
        for (int r = 0; r < 7; r++)
            actual.add(testBoard[1][r]);
        actual.add(myGame.getExtraTile());
        //
        //        If the method actually accepted the input (tile wasn't fixed), we compare 
        //        the memory adresses of a(i) == e(i) for each i. If one pair is not equal to each other, 
        //        this means that the method produced incorrect references amongst the new tiles. If not,
        //        the loop will run without changing successful to false. Once we know that the lists are equal 
        //        in elemental memory addresses, we know the references have changed as expected, adn we can
        //        assertTrue(successful);.
        //
        if (successful){
            for (int i = 0; i < 7; i++){
                if (actual.get(i) != expected.get(i)){
                    successful = false;
                }
            }
        }
        assertTrue("Tried to insert into an unfixed column and got " + successful + ".", successful);
    }
}
