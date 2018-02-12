
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class EnemyExtra extends GameObject{
    
    private GameScene scene;
    private Image human;
    private int x,y;
    private int speedx = 6;
    
    public EnemyExtra(GameScene scene, int x ,int y){
        this.scene = scene;
        
        ImageIcon humanimage = new ImageIcon(getClass().getResource("image/Human.gif"));
        human = humanimage.getImage();
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean update(){
        
        x = x + speedx;
        return true;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(human, x, y, scene);
    }
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 40);
    }
    
    @Override
    public int getX(){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }
}
