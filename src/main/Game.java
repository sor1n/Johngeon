package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class Game extends JPanel {
    int x = 0;
    ImageIcon table;

    public Game() {
        table = new ImageIcon(getClass().getClassLoader().getResource("table_top.jpg"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (table.getImage() != null)
            g2.drawImage(table.getImage(), 0, 0, Main.mainProgram.getWidth(), Main.mainProgram.getHeight(), null);
        Rectangle box1 = new Rectangle(x + 165, 130, 30, 55);
        g2.draw(box1);
    }

    public void run() {
        x++;
        repaint();
    }
}
