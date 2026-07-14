import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShootingGame extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int playerX = 300;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Target> targets = new ArrayList<>();
    private int score = 0;

    public ShootingGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(10, this);
        timer.start();

        // Add some targets
        for (int i = 0; i < 5; i++) {
            targets.add(new Target(50 + i * 100, 50));
        }
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 700, 600);

        // Player
        g.setColor(Color.green);
        g.fillRect(playerX, 500, 50, 20);

        // Bullets
        g.setColor(Color.red);
        for (Bullet b : bullets) {
            g.fillRect(b.x, b.y, 5, 10);
        }

        // Targets
        g.setColor(Color.yellow);
        for (Target t : targets) {
            g.fillRect(t.x, t.y, 40, 20);
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score: " + score, 550, 30);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        // Move bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.y -= 5;
            if (b.y < 0) bullets.remove(i);
        }

        // Check collisions
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            for (int j = 0; j < targets.size(); j++) {
                Target t = targets.get(j);
                if (new Rectangle(b.x, b.y, 5, 10).intersects(new Rectangle(t.x, t.y, 40, 20))) {
                    bullets.remove(i);
                    targets.remove(j);
                    score += 10;
                    break;
                }
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX < 650) playerX += 20;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX > 10) playerX -= 20;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(playerX + 20, 490));
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        ShootingGame gamePlay = new ShootingGame();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Shooting Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}

class Bullet {
    int x, y;
    Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Target {
    int x, y;
    Target(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
