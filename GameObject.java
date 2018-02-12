
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public abstract class GameObject extends JPanel{
    
    public abstract boolean update();
    
    public abstract void draw(Graphics g);
    
    @Override
    public abstract Rectangle getBounds();
    
    public boolean collidedWith(GameObject other){
        return getBounds().intersects(other.getBounds());
    }
}
