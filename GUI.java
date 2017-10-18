package userInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import code.Game;
import code.Location;

/**
 * GUI.class - This acts as a visual representation of a Game object.
 * @author Brian Coyne
 * @author Brian Badura
 * @author Kevin Chan
 * @author Yong Chen
 */
public class GUI
{
    /**
     * Holds references to all TileDisplay objects.
     */
    private TileDisplay[][] guiBoard;
    
    /**
     * Holds references to all Button_LaneSelector objects.
     */
    private ArrayList<Button_LaneSelector> laneSelectors;
    
    /**
     * Holds references to all Pawn_Token objects.
     */
    private ArrayList<Pawn_Token> boardTokens;
    
    /**
     * Holds references to all Pawn_Player objects.
     */
    private ArrayList<Pawn_Player> boardPlayers;
    
    /**
     * The size of each TileDisplay
     */
    private int tileSize = 70;
    
    /**
     * Holds the reference to the current Game.
     */
    private Game myGame;
    
    /**
     * Holds the reference to the JFrame object.
     */
    private JFrame window;
    
    /**
     * Holds the references to the panel housing the controls, and the 
     * panel housing the TileDisplays/Button_LaneSelectors.
     */
    private JPanel boardPanel, controlPanel;
    
    /**
     * Holds the references to the 4 Button_MovePlayers.
     */
    public Button_MovePlayer btnMoveUp, btnMoveDown, btnMoveLeft, btnMoveRight;
    
    /**
     * Holds the references to other unique JButtons.
     */
    private JButton btnMoveMid, btnEndTurn, btnRotateExtraTileCW, 
                    btnRotateExtraTileCCW, btnInsertExtraTile, btnExtraTile;
    
    /**
     * Holds the references to the JLabel objects.
     */
    private JLabel lblPlayerInfo, lblColumn, lblRow;
    
    /**
     * Holds the values of the selected Row/Col insertion pair.
     */
    private int userCol = 0, userRow = 0;
    
    /**
     * Holds true if the current player has moved from their original location this turn.
     */
    public boolean hasMoved;
    
    /**
     * Constructor which initializes all components, updates the GUI and sets the GUI state.
     * @author Brian Badura
     * @param g
     */
    public GUI(Game g){
        myGame = g;
        initializeWindow();
        initializePanels();
        initializeControlComponents();
        initializeGUIBoard();
        initializeActionListeners();
        updateGUI();
        setGUIState(0);
    }
    
    /**
     * Initializes visual aspects of the JFrame.
     * @author Yong Chen
     */
    public void initializeWindow(){
        //Creating the JFrame.
        window = new JFrame("Master Labyrinth®");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null); 
        window.setSize(815, 625);
        window.getContentPane().setBackground(Color.BLACK);
    }
    
    /**
     * Initializes visual aspects of the JPanels.
     * @author Kevin Chan
     * @author Yong Chen
     */
    public void initializePanels(){
        //Creating the JPanel to house the TileDisplays and the Button_LaneSelectors.
        boardPanel = new JPanel();
        window.add(boardPanel);
        boardPanel.setLayout(null);
        boardPanel.setSize(tileSize * 7 + 40, tileSize * 7 + 40);
        boardPanel.setLocation(30, 30);
        boardPanel.setBackground(Color.DARK_GRAY);
        boardPanel.setVisible(true);
        
        //Creating the JPanel to house the controls.
        controlPanel = new JPanel();
        window.add(controlPanel);
        controlPanel.setLayout(null);
        controlPanel.setSize(200, tileSize * 7);
        controlPanel.setLocation(565, 50);
        controlPanel.setBackground(Color.DARK_GRAY);
        controlPanel.setVisible(true);
    }
    
    /**
     * Initializes visual aspects of all the JComponents on the board (aside from
     * previosuly initialized JFrame and JPanels).
     * @author Brian Badura
     * @author Brian Coyne
     */
    public void initializeControlComponents(){
        //Creating JLabel for player information.
        lblPlayerInfo = new JLabel("Player 1's turn");
        controlPanel.add(lblPlayerInfo);
        lblPlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayerInfo.setVerticalAlignment(SwingConstants.CENTER);
        lblPlayerInfo.setLayout(new FlowLayout());
        lblPlayerInfo.setVisible(true);
        lblPlayerInfo.setSize(180, 90);
        lblPlayerInfo.setLocation(10, 10);
        lblPlayerInfo.setFont(new Font("Times New Roman", 1, 14));
        lblPlayerInfo.setOpaque(true);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 2);
        lblPlayerInfo.setBorder(b);
        
        //Creating the Up Button_MovePlayer.
        btnMoveUp = new Button_MovePlayer(80, 110, 1, myGame, this);
        controlPanel.add(btnMoveUp);
        
        //Creating the Right Button_MovePlayer.
        btnMoveRight = new Button_MovePlayer(120, 150, 2, myGame, this);
        controlPanel.add(btnMoveRight);
        
        //Creating the Down Button_MovePlayer.
        btnMoveDown = new Button_MovePlayer(80, 190, 3, myGame, this);
        controlPanel.add(btnMoveDown);
        
        //Creating the Left Button_MovePlayer.
        btnMoveLeft = new Button_MovePlayer(40, 150, 4, myGame, this);
        controlPanel.add(btnMoveLeft);
        
        //Creating a JButton to sit in between all of the "Move" buttons. It looks nicer.
        btnMoveMid = new JButton();
        controlPanel.add(btnMoveMid);
        btnMoveMid.setSize(40, 40);
        btnMoveMid.setLocation(80, 150);
        btnMoveMid.setIcon(new ImageIcon("src/images/MoveMidButton.png"));
        btnMoveMid.setBorder(null);
        btnMoveMid.setEnabled(false);
        
        //Creating a JButton to show the extra tile.
        btnExtraTile = new JButton();
        controlPanel.add(btnExtraTile);
        btnExtraTile.setSize(70, 70);
        btnExtraTile.setLocation(10, 240);
        btnExtraTile.setBorder(null);
        
        //Creating the RotateLeft JButton.
        btnRotateExtraTileCCW = new JButton();
        controlPanel.add(btnRotateExtraTileCCW);
        btnRotateExtraTileCCW.setSize(35, 35);
        btnRotateExtraTileCCW.setLocation(10, 317);
        btnRotateExtraTileCCW.setIcon(new ImageIcon("src/images/RotateLeftButton.png"));
        
        //Creating the RotateRight JButton.
        btnRotateExtraTileCW = new JButton();
        controlPanel.add(btnRotateExtraTileCW);
        btnRotateExtraTileCW.setSize(35, 35);
        btnRotateExtraTileCW.setLocation(45, 317);
        btnRotateExtraTileCW.setIcon(new ImageIcon("src/images/RotateRightButton.png"));
        
        //Creating the JLabel to display the selected column.
        lblColumn = new JLabel();
        controlPanel.add(lblColumn);
        lblColumn.setHorizontalAlignment(SwingConstants.CENTER);
        lblColumn.setFont(new Font("Times New Roman", 1, 14));
        lblColumn.setOpaque(true);
        lblColumn.setForeground(Color.BLACK);
        lblColumn.setBackground(Color.DARK_GRAY);
        lblColumn.setLocation(80, 240);
        lblColumn.setSize(100, 20);
        lblColumn.setVisible(true);
        
        //Creating the JLabel to display the selected row.
        lblRow = new JLabel();
        controlPanel.add(lblRow);
        lblRow.setHorizontalAlignment(SwingConstants.CENTER);
        lblRow.setFont(new Font("Times New Roman", 1, 14));
        lblRow.setOpaque(true);
        lblRow.setForeground(Color.BLACK);
        lblRow.setBackground(Color.DARK_GRAY);
        lblRow.setLocation(80, 260);
        lblRow.setSize(100, 20);
        lblRow.setVisible(true);
        
        //Creating the Insert Tile JButton.
        btnInsertExtraTile = new JButton("Insert");
        controlPanel.add(btnInsertExtraTile);
        btnInsertExtraTile.setLocation(90, 290);
        btnInsertExtraTile.setSize(100, 27);
        
        //Creating the End Turn JButton.
        btnEndTurn = new JButton("<html>End Turn/<br>Take Token</html>");
        controlPanel.add(btnEndTurn);
        btnEndTurn.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEndTurn.setMargin(new Insets(0, 0, 0, 0));
        btnEndTurn.setLocation(90, 317);
        btnEndTurn.setSize(100, 35);
        btnEndTurn.setEnabled(false);
        
        //Creating the Button_LaneSelectors.
        laneSelectors = new ArrayList<Button_LaneSelector>();
        Button_LaneSelector btn = new Button_LaneSelector(115, 0, new Location(1, 0), Color.RED, myGame, this);
        Button_LaneSelector btn1 = new Button_LaneSelector(255, 0, new Location(3, 0), Color.RED, myGame, this);
        Button_LaneSelector btn2 = new Button_LaneSelector(395, 0, new Location(5, 0), Color.RED, myGame, this);
        Button_LaneSelector btn3 = new Button_LaneSelector(0, 115, new Location(0, 1), Color.RED, myGame, this);
        Button_LaneSelector btn4 = new Button_LaneSelector(0, 255, new Location(0, 3), Color.RED, myGame, this);
        Button_LaneSelector btn5 = new Button_LaneSelector(0, 395, new Location(0, 5), Color.RED, myGame, this);
        Button_LaneSelector btn6 = new Button_LaneSelector(510, 115, new Location(6, 1), Color.RED, myGame, this);
        Button_LaneSelector btn7 = new Button_LaneSelector(510, 255, new Location(6, 3), Color.RED, myGame, this);
        Button_LaneSelector btn8 = new Button_LaneSelector(510, 395, new Location(6, 5), Color.RED, myGame, this);
        Button_LaneSelector btn9 = new Button_LaneSelector(115, 510, new Location(1, 6), Color.RED, myGame, this);
        Button_LaneSelector btn10 = new Button_LaneSelector(255, 510, new Location(3, 6), Color.RED, myGame, this);
        Button_LaneSelector btn11 = new Button_LaneSelector(395, 510, new Location(5, 6), Color.RED, myGame, this);
        laneSelectors.add(btn);
        laneSelectors.add(btn1);
        laneSelectors.add(btn2);
        laneSelectors.add(btn3);
        laneSelectors.add(btn4);
        laneSelectors.add(btn5);
        laneSelectors.add(btn6);
        laneSelectors.add(btn7);
        laneSelectors.add(btn8);
        laneSelectors.add(btn9);
        laneSelectors.add(btn10);
        laneSelectors.add(btn11);
        for (int x = 0; x < laneSelectors.size(); x++)
            boardPanel.add(laneSelectors.get(x));
    }
    
    /**
     * Initializes the GUI board by creating the TileDisplays,
     * the Pawn_Players, the Pawn_Tokens,
     * @author Brian Badura
     * @author Brian Coyne
     * @author Kevin Chan
     */
    public void initializeGUIBoard(){
        //Creating and placing the Tiles
        guiBoard = new TileDisplay[7][7];
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 7; y++){
                TileDisplay temp = new TileDisplay(tileSize);
                boardPanel.add(temp);
                temp.setLocation(x * 70 + 20, y * 70 + 20);
                guiBoard[x][y] = temp;
            }
        }
        
        //Creating and placing the Pawn_Tokens
        boardTokens = new ArrayList<Pawn_Token>();
        for (int x = 0; x < myGame.getAllTokens().size(); x++){
            Pawn_Token temp = new Pawn_Token(myGame.getAllTokens().get(x));
            guiBoard[temp.getCol()][temp.getRow()].add(temp);
            boardTokens.add(temp);
        } 
        
        //Creating and placing the Pawn_Players
        boardPlayers = new ArrayList<Pawn_Player>();
        for (int x = 0; x < myGame.getAllPlayers().size(); x++){
            Pawn_Player temp = new Pawn_Player(myGame.getAllPlayers().get(x));
            guiBoard[temp.getCol()][temp.getRow()].add(temp);
            boardPlayers.add(temp);
        }
    }
    
    /**
     * Initializes the ActionListeners. Only unique (occurring once) JButtons appear here.
     * If there are similar JButtons (Move buttons, Lane Selector buttons, etc), we create 
     * classes for those and program the ActionListeners there.
     * @author Brian Badura
     * @author Yong Chen
     */
    public void initializeActionListeners(){
        btnRotateExtraTileCW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                myGame.getExtraTile().rotateClockwise();
                updateGUI();
            }
        });
        btnRotateExtraTileCCW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                myGame.getExtraTile().rotateCounterClockwise();
                updateGUI();
            }
        });
        btnInsertExtraTile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                int row = getUserRow();
                int col = getUserCol();
                if (myGame.insertExtraTile(col, row) == true){
                    myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).setStartLocation();
                    setGUIState(1);
                }
            }
        }); 
        btnEndTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Getting the current player to reference.
                code.Player currentPlayer = myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex());
                //If the player has moved during their turn, they may not end their turn on the tile they started on.
                if (currentPlayer.getLocation().equals(currentPlayer.getStartLocation()) && hasMoved == true){
                    JOptionPane.showMessageDialog(null, "You cannot end your turn on the same tile you started on.");
                    return;
                }
                //Otherwise, if the Player location is equal to that of the next Token, the Player picks it up.
                if (currentPlayer.getLocation().equals(myGame.getAllTokens().get(myGame.getCurrentTokenIndex()).getLocation()) && hasMoved == true){
                    currentPlayer.takeToken(myGame.getAllTokens().get(myGame.getCurrentTokenIndex()));
                    myGame.setCurrentTokenIndex(myGame.getCurrentTokenIndex() + 1);
                    if (myGame.getAllTokens().get(myGame.getCurrentTokenIndex() - 1).getTokenValue() == 25){
                        setGUIState(2);
                        return;
                    }
                }
                //Incrementing the currentPlayerIndex
                int i = myGame.getCurrentPlayerIndex();
                i++;
                if (i > myGame.getAllPlayers().size() - 1)
                    i = 0;
                myGame.setCurrentPlayerIndex(i); 
                setGUIState(0);
            }
        });
    }
    
    /**
     * Updates the GUI by reading/displaying the updated location of all visually represented objects.
     * @author Brian Coyne
     * @author Brian Badura
     * @author Kevin Chan
     * @author Yong Chen
     */
    public void updateGUI(){
        //Update the tiles
        code.Tile[][] tempBoard = myGame.getTileBoard();
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 7; y++){
                code.Tile t = tempBoard[x][y];
                guiBoard[x][y].removeAll();
                if (x % 2 == 0 && y % 2 == 0)
                    guiBoard[x][y].setIcon(t.getTileImageIcon(true));
                else
                    guiBoard[x][y].setIcon(t.getTileImageIcon(false));
                guiBoard[x][y].repaint();
            }
        }
        
        //Update the Extra Tile
        code.Tile t = myGame.getExtraTile();
        btnExtraTile.setIcon(t.getTileImageIcon(false));
        btnExtraTile.repaint();
        
        //Update the Players
        for (int x = boardPlayers.size() - 1; x >= 0; x--){
            Pawn_Player temp = boardPlayers.get(x);
            guiBoard[temp.getCol()][temp.getRow()].add(temp);
            guiBoard[temp.getCol()][temp.getRow()].repaint();
        }
        
        //Update the Tokens
        for (int x = myGame.getCurrentTokenIndex(); x < boardTokens.size(); x++){
            Pawn_Token temp = boardTokens.get(x);
            guiBoard[temp.getCol()][temp.getRow()].add(temp);
            guiBoard[temp.getCol()][temp.getRow()].repaint();
        }
        
        //Update the insertion buttons
        for (int x = 0; x < laneSelectors.size(); x++)
            laneSelectors.get(x).repaint();
        
        //Update the Row/Col labels
        lblColumn.setText("Column (x): " + getUserCol());
        lblRow.setText("Row (y): " + getUserRow());
        
        //Update the info label
        String temp = "";
        for (int x = 0; x < myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).getCollectedTokens().size(); x++){
            temp = temp + "" + myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).getCollectedTokens().get(x).getTokenValue() + ", ";
        }
        String collectedTokens;
        if (temp.equalsIgnoreCase(""))
            collectedTokens = "";
        else
            collectedTokens = temp.substring(0, temp.length() - 2);
        lblPlayerInfo.setText("<html>" + myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).toString() + "'s turn." + "<br>" +
                              "Target Token: " + (myGame.getAllTokens().get(myGame.getCurrentTokenIndex()).getTokenValue()) + "<br>" +
                              "My Tokens: " + collectedTokens + "</html>");
        lblPlayerInfo.setBackground(myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).getPlayerColor());
        lblPlayerInfo.setForeground(myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).getPreferredForecolor());
        
        //Repainting the JFrame.
        window.repaint();  
    }
    
    /**
     * Enables/Disables JComponents based on the state of the game. This is how we limit
     * user interaction to ensure the game rules are upheld.
     * @param guiState int which represents the new state of the GUI.
     * @author Kevin Chan
     * @author Brian Badura
     */
    public void setGUIState(int guiState){
        switch (guiState){
            //Waiting for insertion...
            case (0):
                hasMoved = false;
                btnMoveUp.setEnabled(false);
                btnMoveDown.setEnabled(false);
                btnMoveLeft.setEnabled(false);
                btnMoveRight.setEnabled(false);
                btnMoveMid.setEnabled(false);
                btnEndTurn.setEnabled(false);
                for (int x = 0; x < laneSelectors.size(); x++){
                    laneSelectors.get(x).setEnabled(true);
                }
                btnInsertExtraTile.setEnabled(true);
                btnRotateExtraTileCCW.setEnabled(true);
                btnRotateExtraTileCW.setEnabled(true);
                btnExtraTile.setEnabled(true);
                updateGUI();
                break;
            //After insertion, waiting to move and/or end turn.
            case (1):
                btnMoveUp.setEnabled(true);
                btnMoveDown.setEnabled(true);
                btnMoveLeft.setEnabled(true);
                btnMoveRight.setEnabled(true);
                btnMoveMid.setEnabled(true);
                btnEndTurn.setEnabled(true);
                for (int x = 0; x < laneSelectors.size(); x++){
                    laneSelectors.get(x).setBackground(Color.RED);
                    laneSelectors.get(x).setImage();
                    laneSelectors.get(x).setEnabled(false);
                }
                setUserCol(0);
                setUserRow(0);
                btnInsertExtraTile.setEnabled(false);
                btnRotateExtraTileCCW.setEnabled(false);
                btnRotateExtraTileCW.setEnabled(false);
                btnExtraTile.setEnabled(false);
                updateGUI();
                break;
            //All tokens have been collected. Game is over.
            case (2):
                btnMoveUp.setEnabled(false);
                btnMoveDown.setEnabled(false);
                btnMoveLeft.setEnabled(false);
                btnMoveRight.setEnabled(false);
                btnMoveMid.setEnabled(false);
                btnEndTurn.setEnabled(false);
                btnInsertExtraTile.setEnabled(false);
                btnRotateExtraTileCCW.setEnabled(false);
                btnRotateExtraTileCW.setEnabled(false);
                btnExtraTile.setEnabled(false);
                lblPlayerInfo.setForeground(Color.WHITE);
                lblPlayerInfo.setBackground(Color.BLACK);
                lblPlayerInfo.setText("<html>" + myGame.getAllPlayers().get(0).toString() + ": " + myGame.getAllPlayers().get(0).getScore() + "<br>" +
                                                 myGame.getAllPlayers().get(1).toString() + ": " + myGame.getAllPlayers().get(1).getScore() + "<br>" +
                                                 myGame.getAllPlayers().get(2).toString() + ": " + myGame.getAllPlayers().get(2).getScore() + "<br>" +
                                                 myGame.getAllPlayers().get(3).toString() + ": " + myGame.getAllPlayers().get(3).getScore() + "</html>");
                code.Player winner = new code.Player(5, new code.Location(0, 0), Color.GRAY, null);
                for (int z = 0; z < myGame.getAllPlayers().size(); z++){
                    if (myGame.getAllPlayers().get(z).getScore() > winner.getScore())
                        winner = myGame.getAllPlayers().get(z);
                }
                JOptionPane.showMessageDialog(null, "Congratulations " + winner.toString() + ". You're the winner! The game is now over. The final scores have been printed.");
        }
    }    
    
    /**
     * userCol Getter.
     * @return userCol as int.
     */
    public int getUserCol(){
        return userCol;
    }
    
    /**
     * userCol Setter.
     * @param c The new value of userCol.
     */
    public void setUserCol(int c){
        userCol = c;
    }
    
    /**
     * userRow Getter.
     * @return userRow as int.
     */
    public int getUserRow(){
        return userRow;
    }
    
    /**
     * userRow Setter.
     * @param r The new value of userRow.
     */
    public void setUserRow(int r){
        userRow = r;
    }
    
    /**
     * guiBoard Getter.
     * @return guiBoard as TileDisplay[][].
     */
    public TileDisplay[][] getGUIBoard(){
        return guiBoard;
    }
    
    /**
     * boardTokens Getter.
     * @return boardTokens as ArrayList<Pawn_Token>.
     */
    public ArrayList<Pawn_Token> getBoardTokens(){
        return boardTokens;
    }
    
    /**
     * laneSelectors Getter.
     * @return laneSelectors as ArrayList<Button_LaneSelector>.
     */
    public ArrayList<Button_LaneSelector> getLaneSelectors(){
        return laneSelectors;
    }
}
