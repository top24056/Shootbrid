
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class HeartLife extends GameObject{
    private GameScene scene;
    private Image image;
    private int x, y;
    
    
    public HeartLife(GameScene scene, int x, int y){
        
        this.scene = scene;
        this.x = x;
        this.y = y;
        
        //สร้าง รูป หัวใจ
        ImageIcon loader1 = new ImageIcon(getClass().getResource("image/heart.gif"));
        image = loader1.getImage();
    }
    
    public boolean update(){
        return true;
    }
    
    @Override
    public void draw (Graphics g){
        
        //วาด หัวใจ
        g.drawImage(image, x, y, scene);
    }
    
    @Override
    public Rectangle getBounds(){
        
        //สร้างสี่เหลี่ยม รอบ หัวใจ
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
