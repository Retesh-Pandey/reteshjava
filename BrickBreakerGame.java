import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int ballX = 120, ballY = 350, ballXdir = -1, ballYdir = -2;
    private int paddleX = 310;
    private int score = 0;
    private int totalBricks = 21;

    public BrickBreakerGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(8, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Paddle
        g.setColor(Color.green);
        g.fillRect(paddleX, 550, 100, 8);

        // Ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 550, 30);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        ballX += ballXdir;
        ballY += ballYdir;

        if (ballX < 0 || ballX > 670) ballXdir = -ballXdir;
        if (ballY < 0) ballYdir = -ballYdir;

        if (ballY > 570) {
            ballXdir = 0;
            ballYdir = 0;
            JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score);
        }

        // Paddle collision
        if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(paddleX, 550, 100, 8))) {
            ballYdir = -ballYdir;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddleX >= 600) paddleX = 600;
            else moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddleX < 10) paddleX = 10;
            else moveLeft();
        }
    }

    public void moveRight() { paddleX += 20; }
    public void moveLeft() { paddleX -= 20; }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        BrickBreakerGame gamePlay = new BrickBreakerGame();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
