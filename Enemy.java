
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;


public class Enemy extends GameObject{
    
    //random speed นก ในช่วง 10
    private Random random = new Random();
    private int ran = random.nextInt(10);
    
    private Image[] ranimage = new Image[3];
    private int ranintimage = random.nextInt(3);
    
    private GameScene scene;
    
    //สร้างรูปนก
    private Image flappy;
    private Image angry;
    private Image angry1;
    
    //สร้าง ตำแหน่งนก และ เพิ่ม speed
    private int x, y;
    private int speedX = ran + 5;
    private int speedY = ran - 5;
    
    public Enemy(GameScene scene, int x, int y){
        
        this.scene = scene;
        
        //ใส่รูป นก flappy
        ImageIcon flappyimage = new ImageIcon(getClass().getResource("image/flappy1.gif"));
        flappy = flappyimage.getImage();
        
        ImageIcon angryyellow = new ImageIcon(getClass().getResource("image/Angrybirdyellow.gif"));
        angry = angryyellow.getImage();
        
        ImageIcon angryred = new ImageIcon(getClass().getResource("image/Angrybirdred.gif"));
        angry1 = angryred.getImage();
        
        ranimage[0] = flappy;
        ranimage[1] = angry;
        ranimage[2] = angry1;
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean update(){
        
        //กำหนดขอบเขตให่นกไม่เกิน ทะเล ข้างล่าง
        if (y > 475 ){
            speedY = -ran;
        }
        
        //กำหนดขอบเขตให้นกไม่เกิน Scoreboard ข้างบน
        else if (y < 80){
            speedY = ran;
        }

        x += speedX;
        y += speedY;
        
        return true;
    }
    
    @Override
    public void draw(Graphics g){
        
        //วาด นก
        g.drawImage(ranimage[ranintimage], x, y, scene);
    }
    
    @Override
    public Rectangle getBounds(){
        
        //สร้าง สี่เหลี่ยมครอบนก
        return new Rectangle(x, y, 50, 50);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
