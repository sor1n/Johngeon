package main;

import listeners.KeyHandler;
import listeners.TimerHandler;

import javax.swing.*;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class Main extends JFrame {
    public static Game game = new Game();
    private static Timer timer;
    public static Main mainProgram = new Main();

    public static void main(String[] args) {
        mainProgram.setTitle("Johngeon");
        mainProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainProgram.addKeyListener(new KeyHandler());
        mainProgram.add(game);
        mainProgram.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainProgram.setUndecorated(true);
        mainProgram.setVisible(true);

        timer = new Timer(60, new TimerHandler());
        timer.start();
    }
}
