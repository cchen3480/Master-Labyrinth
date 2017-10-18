package userInterface;

import java.awt.Color;

import javax.swing.JButton;

/**
 * TileDisplay.class - A visual representation of a tile.
 * @author Kevin Chan
 *
 */
@SuppressWarnings("serial")
public class TileDisplay extends JButton
{   
    /**
     * Constructor
     * Sets visual properties of the TileDisplay. TileDisplays are not linked to 
     * any specific tile, as they simply display the tile at a static location.
     */
    public TileDisplay(int s){
        this.setSize(s, s);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }  
}
