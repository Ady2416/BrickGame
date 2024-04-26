import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj  = new JFrame();
        Panel game = new Panel();
        obj.setBounds(10, 10, 710, 630);
        obj.setTitle("Brick Game");
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        obj.add(game);
        obj.setResizable(false);

    }
}