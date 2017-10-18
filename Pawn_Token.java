package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Pawn_Token.class - The visual representation of a Token object.
 * @author Brian Badura
 * @author Yong Chen.
 *
 */
@SuppressWarnings("serial")
public class Pawn_Token extends JButton
{
    /**
     * Each Pawn_Tokenis linked with a code.Token.
     */
    private code.Token myToken;
    
    /**
     * Constructor which sets visual properties, and links a code.Token object.
     * @param t code.Token to be linked.
     */
    public Pawn_Token(code.Token t){
        myToken = t;
        setSize(20, 20);
        setLocation(25, 25);
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
        setFont(new Font("Times New Roman", 1, 12));
        setOpaque(false);
        setContentAreaFilled(false);
        setLayout(null);
        setBorder(null);
        setMargin(new Insets(0, 0, 0, 0));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER); 
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setIcon(new ImageIcon("src/images/Token.png"));
        setText("" + getTokenDatum().getTokenValue());
    }
    
    /**
     * myToken Setter.
     * @param t code.Token. to be linked.
     */
    public void setTokenDatum(code.Token t){
        myToken = t;
    }
    
    /**
     * myToken Getter.
     * @return myToken as code.Token.
     */
    public code.Token getTokenDatum(){
        return myToken;
    }
    
    /**
     * Column Getter.
     * @return the column value of the myToken object.
     */
    public int getCol(){
        return myToken.getLocation().getCol();
    }
    
    /**
     * Row Getter.
     * @return the row value of the myToken object.
     */
    public int getRow(){
        return myToken.getLocation().getRow();
    }
}
