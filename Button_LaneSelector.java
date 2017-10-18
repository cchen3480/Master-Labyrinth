package userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.Game;
import code.Location;

/**
 * Button_LaneSelector.class - JButton that allows the user to select a 
 * specific row/col pair to insert the extra tile.
 * @author Brian Coyne
 * @author Kevin Chan
 *
 */
@SuppressWarnings("serial")
public class Button_LaneSelector extends JButton implements ActionListener
{
    /**
     * Reference to the current Game.
     */
    @SuppressWarnings("unused")
    private Game myGame;
    
    /**
     * Reference to the current GUI.
     */
    private GUI myGUI;
    
    /**
     * Reference to the insertion Location associated with this Button.
     */
    private Location insertLocation;
    
    /**
     * Reference to whether the Button is clickable.
     */
    boolean enabled = true;
    
    /**
     * Constructor.
     * @param x The x-value of the location of the actual JButton.
     * @param y The y-value of the location of the actual JButton.
     * @param l The location that the button represents on the Game board.
     * @param c The color of the button (Green if selected, Red otherwise).
     * @param g The current Game.
     * @param gui The current GUI.
     */
    public Button_LaneSelector(int x, int y, Location l, Color c, Game g, GUI gui){
        setSize(20, 20);
        setLocation(x, y);
        setBackground(c);
        setImage();
        setVisible(true);
        setBorder(null);
        insertLocation = l;
        myGame = g;
        myGUI = gui;
        addActionListener(this);
    }
    
    /**
     * Sets the image of the button based on its Background color.
     */
    public void setImage(){
        if (getBackground().equals(Color.RED))
            setIcon(new ImageIcon("src/images/RedButton.png"));
        else if (getBackground().equals(Color.GREEN))
            setIcon(new ImageIcon("src/images/GreenButton.png"));
    }

    /**
     * When a Lane Selector Button is clicked...
     * 1. Reset all of the Lane Selector's background colors back to Red.
     * 2. Set the background color of the one that was clicked to Green.
     * 3. (re)setImage();
     * 4. Set the userCol/userRow equal to that of the insertLocation associated with this Lane Selector.
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (enabled){
            for (int x = 0; x < myGUI.getLaneSelectors().size(); x++){
                myGUI.getLaneSelectors().get(x).setBackground(Color.RED);
                myGUI.getLaneSelectors().get(x).setImage();
            }
            setBackground(Color.GREEN);
            setImage();
            myGUI.setUserCol(insertLocation.getCol());
            myGUI.setUserRow(insertLocation.getRow());
            myGUI.updateGUI();
        }
    }
    
    /**
     * enabled Setter.
     */
    @Override
    public void setEnabled(boolean a){
        enabled = a;
    }
}
