package userInterface;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Pawn_Player.class - A visual representation of each Player object.
 * @author Brian Badura
 * @author Yong Chen
 *
 */
@SuppressWarnings("serial")
public class Pawn_Player extends JButton
{
    /**
     * Each Pawn_Player is linked with a Player in data.
     */
    private code.Player myPlayer;
    
    /**
     * Sets visual properties of the Player, and links the code.Player object.
     * @param p code.Player to be linked.
     */
    public Pawn_Player(code.Player p){
        myPlayer = p;
        setSize(30, 30);
        setLayout(null);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(null);
        switch (myPlayer.getPlayerNumber()){
            case 1:
                setLocation(10, 10);
                setIcon(new ImageIcon("src/images/Player_Red.png"));
                break;
            case 2:
                setLocation(30, 10);
                setIcon(new ImageIcon("src/images/Player_Blue.png"));
                break;
            case 3:
                setLocation(10, 30);
                setIcon(new ImageIcon("src/images/Player_White.png"));
                break;
            case 4:
                setLocation(30, 30);
                setIcon(new ImageIcon("src/images/Player_Yellow.png"));
                break;
        }
    }
    
    /**
     * myPlayer Setter.
     * @param p code.Player to be linked.
     */
    public void setPlayer(code.Player p){
        myPlayer = p;
    }
    
    /**
     * myPlayer Getter.
     * @return myPlayer as code.Player.
     */
    public code.Player getPlayer(){
        return myPlayer;
    }
    
    /**
     * Column Getter.
     * @return Column value of the code.Player's location.
     */
    public int getCol(){
        return myPlayer.getLocation().getCol();
    }
    
    /**
     * Row Getter.
     * @return Row value of the code.Player's location.
     */
    public int getRow(){
        return myPlayer.getLocation().getRow();
    }
}
