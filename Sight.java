
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Sight extends GameObject{
    
    private GameScene scene;
    private Image image;
    private int x, y;
    private int speed = 12;
    
    public Sight(GameScene scene, int x, int y){
        
        this.scene = scene;
        
        ImageIcon loader = new ImageIcon(getClass().getResource("image/sight.png"));
        image = loader.getImage();
        
        this.x = x - image.getWidth(null)/2;
        this.y = y - image.getHeight(null)/2;
    }
    
    @Override
    public boolean update(){
        //เลื่อนไปบน
        if (scene.getKeyState(GameScene.KEY_UP)){
            y -= speed;
        }
        
        //เลื่อนไปล่าง
        if (scene.getKeyState(GameScene.KEY_DOWN)){
            y += speed;
        }
        
        //เลื่อนซ้าย
        if (scene.getKeyState(GameScene.KEY_LEFT)){
            x -= speed;
        }
        
        //เลื่อนขวา
        if (scene.getKeyState(GameScene.KEY_RIGHT)){
            x += speed;
        }
        
        //กดยิง
        
        //เลื่อนซ้ายแล้วชน
        if (x < 1){
            x += speed;
        }
        
        //เลื่อนขวาแล้วชน
        if (x > 975){
            x -= speed;
        }
        
        //เลื่อนล่างแล้วชน 767
        if (y > 500){
           y -= speed;
        }
        
        //เลื่อนบนแล้วชน
        if (y < 75){
            y += speed;
        }
        
        return true;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, scene);
    }
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle(x ,y ,image.getWidth(null) ,image.getHeight(null));
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
