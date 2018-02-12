
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class BasicGame extends JFrame{
    
    public static void main(String[] args) {
        
        JFrame start = new JFrame("START GAME NOWWWW !!!");
        
        start.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        start.setSize(1024,768);
        start.add(new GameStrat(start) , BorderLayout.CENTER);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        

    }
}
