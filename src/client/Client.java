package client;

import java.awt.*;

public class Client {

    

    public static void main(String args[]) {

        Gui.start();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}
