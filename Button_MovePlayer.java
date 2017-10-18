package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.Game;

/**
 * Button_MovePlayer.class - These types of buttons handle player movement.
 * @author Brian Coyne 
 * @author Kevin Chan
 *
 */
@SuppressWarnings("serial")
public class Button_MovePlayer extends JButton implements ActionListener
{
    /**
     * The direction that this Button controls.
     */
    private int Direction;
    
    /**
     * A reference to the current Game object.
     */
    private Game myGame;
    
    /**
     * A reference to the current GUI object.
     */
    private GUI myGUI;
    
    /**
     * Constructor. Sets the physical location of the button and the direction that it handles.
     * @param x Location x.
     * @param y Location y.
     * @param d Direction.
     * @param g Game object.
     * @param gui GUI object.
     */
    public Button_MovePlayer(int x, int y, int d, Game g, GUI gui){
        setLocation(x, y);
        setSize(40, 40);
        Direction = d;
        myGame = g;
        myGUI = gui;
        switch (d){
            case (1):
                setIcon(new ImageIcon("src/images/MoveUpButton.png"));
                break;
            case (2):
                setIcon(new ImageIcon("src/images/MoveRightButton.png"));
                break;
            case (3):
                setIcon(new ImageIcon("src/images/MoveDownButton.png"));
                break;
            case (4):
                setIcon(new ImageIcon("src/images/MoveLeftButton.png"));
                break;
        }
        setEnabled(false);
        addActionListener(this);
    }

    /**
     * Makes the proper move dependent upon the Buttons stored direction.
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (Direction == 1){
            if (myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).canMoveUp(myGame.getTileBoard())){
                myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).moveUp();
                myGUI.hasMoved = true;
            }
        }
        if (Direction == 2){
            if (myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).canMoveRight(myGame.getTileBoard())){
                myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).moveRight();
                myGUI.hasMoved = true;
            }
        }
        if (Direction == 3){
            if (myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).canMoveDown(myGame.getTileBoard())){
                myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).moveDown();
                myGUI.hasMoved = true;
            }
        }
        if (Direction == 4){
            if (myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).canMoveLeft(myGame.getTileBoard())){
                myGame.getAllPlayers().get(myGame.getCurrentPlayerIndex()).moveLeft();
                myGUI.hasMoved = true;
            }
        }
        myGUI.updateGUI();
    }
}
