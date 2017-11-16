import java.awt.*;
import javax.swing.*;

public class CodeBarrePanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private CodeBarre cb;

    public CodeBarrePanel() {
        cb = new CodeBarre();
    }
    
    public CodeBarrePanel(String nb)
    {
        cb = new CodeBarre(nb);
    }
    
    public void modifie(String nouvNb)
    {
        cb.modifie(nouvNb);
        repaint();
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        // remplissage du fond pour effacer lors des repaint()
        g.setColor(Color.WHITE);
        Dimension d = getSize();
        g.fillRect(0,0,d.width,d.height);
        
        cb.dessine(g);
    }
}
