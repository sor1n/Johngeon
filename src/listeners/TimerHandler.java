package listeners;

import main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class TimerHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.game.run();
    }
}
