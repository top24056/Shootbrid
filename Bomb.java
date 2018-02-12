
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Bomb extends GameObject{
    
    private Image bomb;      
    private GameScene scene;
    private int x, y;
    private int speed = 5;
    
    public Bomb(GameScene scene, int x, int y){
        
        this.scene = scene;
        
        
        ImageIcon Bombimage = new ImageIcon(getClass().getResource("image/Bomb.png"));
        bomb = Bombimage.getImage();

        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean update(){
        y += speed;
        
        if (y > 768){
            
            return false;
        }
        
        else{
            return true;
        }
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(bomb, x, y, scene);
        
        if(y > 768){
            g.drawImage(bomb, x, y, null);
        }
    }
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle( x, y, bomb.getWidth(null), bomb.getHeight(null));
    }
    
}
