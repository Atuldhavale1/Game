import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class flappyBirds extends JPanel implements ActionListener, KeyListener {
    int boardwidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Bird
    int birdX = boardwidth / 8;
    int birdY = boardHeight / 2;
    int biardwidth = 34;
    int biarHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = biardwidth;
        int height = biarHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }

    }

    // Pipes
    int pipeX = boardwidth;
    int pipeY = 0;
    int pipeWidth = 64; // scaled by 1/6
    int pipeHeight = 512;

    class pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        pipe(Image img) {
            this.img = img;
        }
    }

    // logic
    Bird bird;
    int velocityX = -4; // move pipes to the left speed (simulates bird moving right)
    int velocityY = 0;
    int gravity = 1;

    ArrayList<pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placepipesTimer;

    flappyBirds() {
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        // setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImg = new ImageIcon(getClass().getResource("./bg.jpeg")).getImage();
        // backgroundImg = new
        // ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        // birdImg = new
        // ImageIcon(getClass().getResource("./cartoon-bird.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        // bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<pipe>();

        // place pipes timer
        placepipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placepipesTimer.start();

        // game timer
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

    }

    public void placePipes() {
        // (0-1)* pipeHeight/2 -> (0-256)
        // 128
        // 0-128 -(0-256)--> 1/4 pipeHeight -> 3/4 pipeHeight
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        // generate random pipe positions
        pipe topPipe = new pipe(topPipeImg);
        pipes.add(topPipe);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // System.out.println("draw");
        // Draw background
        g.drawImage(backgroundImg, 0, 0, boardwidth, boardHeight, null);

        // brid
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        }
    }

    public void move() {
        // brid
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
