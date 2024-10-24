import javax.swing.*;

public class App {

    public static void main(String[] args) throws Exception {
        int boardwidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        // frame.setVisible(true);
        frame.setSize(boardwidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flappyBirds flappyBirds = new flappyBirds();
        frame.add(flappyBirds);
        frame.pack();
        flappyBirds.requestFocus();
        frame.setVisible(true);
    }
}