
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameStrat extends JPanel{
    private final JLabel start;
    private final JLabel exit;
    private Image bc;
    
    private JFrame framestart;
    
    private Font font = new Font("ALGERIAN", Font.BOLD, 50);
    
    public GameStrat(final JFrame framestart) {
        
        this.framestart = framestart;
        
        setLayout(null);
        
        ImageIcon back = new ImageIcon(getClass().getResource("image/backgroundstart.png"));
        bc = back.getImage();
        
        final ImageIcon starticon = new ImageIcon(getClass().getResource("image/start1.png"));
        final ImageIcon exiticon = new ImageIcon(getClass().getResource("image/exit1.png"));
        final ImageIcon starticon1 = new ImageIcon(getClass().getResource("image/start2.png"));
        final ImageIcon exiticon1 = new ImageIcon(getClass().getResource("image/exit2.png"));
        
        start = new JLabel(starticon);
        start.setIcon(starticon);
        start.setBounds(200, 450, 250, 100);
        
        
        exit = new JLabel(exiticon);
        exit.setIcon(exiticon);
        exit.setBounds(575, 450, 250, 100);
        
 
        add(start);
        add(exit);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e){
                framestart.setVisible(false);
                JFrame f = new JFrame("Game Shot");
                f.add(new GameScene(f), BorderLayout.CENTER);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(1024, 768);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
            
    });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e){
                System.exit(0);
            }
            
    });
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e1){
                start.setIcon(starticon1);
            }
            @Override
            public void mouseExited (MouseEvent e2){
                start.setIcon(starticon);
            }
});
        exit.addMouseListener(new MouseAdapter(){
            public void mouseEntered (MouseEvent e3){
                exit.setIcon(exiticon1);
            }
            public void mouseExited (MouseEvent e4){
                exit.setIcon(exiticon);
            }
        });
        
    }

    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(bc, 0, 0, null);
        
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("!GAME SHOT!", 345, 100);
        g.drawString("!PRESS START BUTTON!", 220, 150);
        
    }
    
}

