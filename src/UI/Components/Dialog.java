package UI.Components;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog {
    JFrame f;

    public Dialog(String text) {
        f = new JFrame();
        f.setSize(200, 200);
        JOptionPane.showMessageDialog(f, text);

    }
}