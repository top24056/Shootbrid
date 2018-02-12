import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioStream;

public class GameScene extends JPanel implements ActionListener, KeyListener{
    
    private JFrame game;
    
    //set ค่าปุ่มให้เท่ากับตัวเลข
    public static final int KEY_UP = 0;
    public static final int KEY_DOWN = 1;
    public static final int KEY_LEFT = 2;
    public static final int KEY_RIGHT = 3;
    public static final int KEY_FIRE = 4;
    public static final int KEY_BOMB = 5;
    
    //สร้างที่อยู่ให้ปุ่มกด
    private boolean[] keyStates = new boolean[6];
    
    //สร้างที่อยู่ให้รูปหัวใจ
    private int locationheartx = 960;
    private int locationhearty = 20;
    private int droplocationx = 30;
    
    //สร้าง ตัวแปรเก็บ เป้า background flappy
    private Image imagesc;
    private Sight player;
    private Image bc;
    private Image bullet;
    
    //สร้าง Arrayslist ให้หัวใจ กับ นก คน ลูกระเบิด
    private ArrayList<GameObject> enemies = new ArrayList<GameObject>();
    private ArrayList<GameObject> lstheart = new ArrayList<GameObject>();
    private ArrayList<GameObject> lstbomb = new ArrayList<GameObject>();
    private ArrayList<GameObject> lsthuman = new ArrayList<GameObject>();

    //จำนวนตัวในหัวใจ
    private int heartlocation;
    
    //random ตัวเลข
    private Random random;
    
    //สร้าง คะแนน
    private int score;
    
    //เปลี่ยน score เป็น String
    private String strscore;
    
    private int time = 1;
    
    //set font
    private Font myfont = new Font("Serif", Font.BOLD, 25);
    
    private Timer timer;
    
    public GameScene(JFrame game){
        
        setDoubleBuffered(true);
        
        this.game = game;
        
        //สร้าง background
        ImageIcon background = new ImageIcon(getClass().getResource("image/background.png"));
        bc = background.getImage();
        
        //สร้างรูป ScoreBorad
        ImageIcon scoreboard = new ImageIcon(getClass().getResource("image/sc1.png"));
        imagesc = scoreboard.getImage();
        
        ImageIcon bullethope = new ImageIcon(getClass().getResource("image/bullethope.png"));
        bullet = bullethope.getImage();
        
        //สร้างรูปหัวใจ 10 ดวง
        for (int j=0 ; j<3 ; j++){
            HeartLife heart = new HeartLife(this, locationheartx, locationhearty);
            lstheart.add(heart);
            heartlocation += 1;
            locationheartx -= droplocationx;
        }
          
        //สร้าง เป้า เกิดที่ประมาณกลางจอ
        player = new Sight(this, 507, 390);
        
        //สราง ตัว คน คะแนนพิเศษ
        EnemyExtra human = new EnemyExtra(this, 2, 657);
        lsthuman.add(human);
        
        //สร้างตัว random
        random = new Random();
        
        //สร้าง flappy 3 ตัว
        for(int i = 0 ; i < 3 ; i++){
            Enemy enemy = new Enemy(this, 1, random.nextInt(474) + 1);
            enemies.add(enemy);
        }
        
        setFocusable(true);
        addKeyListener(this);
        
        //FPS
        timer = new Timer(1000/50 ,this);
        timer.start();
        
    }

    
    public void update(){
        
        //สร้าง Arraylist สำหรับ การตายของ Object
        ArrayList<GameObject> deadlist = new ArrayList<GameObject>();
        
        //สร้าง Arraylist สำหรับ การตายของ object ที่ไม่ได้ยิง
        ArrayList<GameObject> dead = new ArrayList<GameObject>();
        
        //Update ตัวเป้าให้เคลื่อนที่ได้
        player.update();
        
       
        
        for (GameObject object : lstbomb){
            if(object.update() == false){
                deadlist.add(object);
            }
        }
        
        //Update ตัวคนให้เคลื่อนที่
        for (GameObject object : lsthuman){
            object.update();    
        }
        
        //Update ตัวนก
        if (lstheart.isEmpty() == false){
            for (GameObject object : enemies){
                object.update();
            }
        }
        
        //Update ตัวระเบิด
        for (GameObject object : lstbomb){
            object.update();
        }
        
        for (GameObject bombs : lstbomb){
            for(GameObject enemyex : lsthuman){
                if(bombs.collidedWith(enemyex)){ 
                    deadlist.add(bombs);
                    deadlist.add(enemyex);
                    score += 10;
                }
            }
        }
        
        for (GameObject human1 : lsthuman){
            if (human1.getX() > 1020){
                deadlist.add(human1);
            }
        }
        
        //ตรวจถ้า sight ทับกับ นก ให้ใส่นกใน deadlist แล้ว +score 
        for(GameObject enemy : enemies){
            if(getKeyState(GameScene.KEY_FIRE)){
                if (player.collidedWith(enemy)){
                    deadlist.add(enemy);
                    score = score + 1;
                    this.score = score;
                    
                if(score % 10 == 0){
                      EnemyExtra human = new EnemyExtra(this, 2, 657);
                      lsthuman.add(human);
                    }
                }
            }    
            
            //ถ้านกเกินระยะ 1020 ให้เข้า deadlist เสียหัวใจ
            if(enemy.getX() > 1020){
                dead.add(enemy);
                lstheart.remove(heartlocation - 1);
                heartlocation -= 1;
                }
            
            }
        
        //วนใน deadlist ถ้ามีนกใน deadlist ให้ลบออก แล้วสร้างใหม่ ได้ SCORE
        for (GameObject deadlistObject : deadlist){
            if(enemies.contains(deadlistObject)){
                enemies.remove(deadlistObject);

                Enemy enemy = new Enemy(this, 1, random.nextInt(450) + 80);
                enemies.add(enemy);
            }
            
            if(lstbomb.contains(deadlistObject)){
                lstbomb.remove(deadlistObject);
            }
            
            if(lsthuman.contains(deadlistObject)){
                lsthuman.remove(deadlistObject);
            }
            
        }
        
        //วน dead ถ้านกตายโดนที่ไม้ได้ยิง
        for(GameObject deadObject : dead){
            if(enemies.contains(deadObject)){
                enemies.remove(deadObject);
                
                Enemy enemy = new Enemy(this, 1, random.nextInt(450) + 80);
                enemies.add(enemy);
            }
        }
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //วาด background
        g.drawImage(bc, 0, 0, null);
        
        //วาด ScoreBoard
        g.drawImage(imagesc, 42, 42, null);
        
        //วาด flappy
        for(GameObject object : enemies){
            object.draw(g);
        }
        
        //วาด หัวใจ
        for (GameObject object : lstheart){
            object.draw(g);
        }
        
        //วาด bombs
        for (GameObject object : lstbomb){
            object.draw(g);
        }
        
        //วาด คน
        for (GameObject object : lsthuman){
            object.draw(g);
        }
        
        if(getKeyState(GameScene.KEY_FIRE)){
            g.drawImage(bullet, player.getX() - 17, player.getY() - 15, null);
        }

        //วาด เป้า
        player.draw(g);
        
        //ใส่ score
        g.setColor(Color.RED);
        g.setFont(myfont);
        strscore = String.valueOf(score);
        g.drawString(strscore, 160, 60);
        
        //ตายแล้ววาดหน้าจอ Gameover 412 428
        if(lstheart.isEmpty() == true){
            game.setVisible(false);
            JFrame fover = new JFrame("GAME OVERR!!!!");
            fover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fover.setSize(1024, 768);
            fover.add(new GameOver(fover,score), BorderLayout.CENTER);
            fover.setLocationRelativeTo(null);
            fover.setVisible(true);
            
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        update();
    }
    
    public boolean getKeyState(int key){
        return keyStates[key];
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        //Don't anything
    }
    
    public int getscore(){
        System.out.println(score);
        return score;
    }
    
    //method ที่กดแล้วจะทำการเคลื่อนที่
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = true;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = true;
                break;
            case KeyEvent.VK_SPACE:
                keyStates[KEY_FIRE] = true;
                break;
            case KeyEvent.VK_CONTROL:
                keyStates[KEY_BOMB] = true;
                Bomb bomb = new Bomb(this, player.getX() + 12, player.getY());
                lstbomb.add(bomb);
                break;
        }
    }
    
    //method ที่ปล่อยปล่อยปุ่มแล้วจะหยุดเคลื่อน
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = false;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = false;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = false;
                break;
            case KeyEvent.VK_SPACE:
                keyStates[KEY_FIRE] = false;
                break;
            case KeyEvent.VK_CONTROL:
                keyStates[KEY_BOMB] = false;
                break;
        }
    }
}
