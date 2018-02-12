
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameOver extends JPanel{

    private JLabel restart;
    private JLabel exit;

    private JFrame gameover;

    private Image gameoverimage;
    private Image gameoveri1;

    private Font font = new Font("ALGERIAN", Font.BOLD, 50);

    private int score;
    private String strscore;

    public GameOver(final JFrame gameover, int score){

        this.score = score;

        this.gameover = gameover;

        setLayout(null);

        ImageIcon over = new ImageIcon(getClass().getResource("image/gameover.gif"));
        gameoverimage = over.getImage();

        ImageIcon over1 = new ImageIcon(getClass().getResource("image/Cat.gif"));
        gameoveri1 = over1.getImage();

        ImageIcon restarticon = new ImageIcon(getClass().getResource("image/replay.png"));

        ImageIcon exiticon = new ImageIcon(getClass().getResource("image/exitgameover.png"));

        restart = new JLabel(restarticon);
        restart.setIcon(restarticon);
        restart.setBounds(70, 50, 41, 46);

        exit = new JLabel(exiticon);
        exit.setIcon(exiticon);
        exit.setBounds(900, 45, 46, 46);

        add(restart);
        add(exit);

        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e){
                gameover.setVisible(false);
                JFrame f = new JFrame("Game Shot");
                f.add(new GameScene(f), BorderLayout.CENTER);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(1024, 768);
                f.setLocationRelativeTo(null);

                f.setVisible(true);

            }
});
        exit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked (MouseEvent e1){
                System.exit(0);
            }

        });


    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);

        g.drawImage(gameoverimage, 0, 0, null);
        g.drawImage(gameoveri1, 375, 500, null);

        repaint();

        g.setColor(Color.BLACK);
        g.setFont(font);
        strscore = String.valueOf(score);
        g.drawString("Score : " + strscore, getWidth()/2 - 85, 90);

    }
}
