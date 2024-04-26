import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;


public class Panel extends JPanel implements KeyListener,ActionListener {
    private Boolean playing = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDirection = -1;
    private int ballYDirection = -2;

    private Map map;


    public Panel() {
        map = new Map(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {
        //bg
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //map
        map.draw((Graphics2D)g);
        //score
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("Score: " + score, 500, 30);

        //border
        g.setColor(Color.blue);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.red);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        g.dispose();

        //
        if(ballPosY>570){
            playing=false;
            ballXDirection=0;
            ballYDirection=0;
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            g.drawString("GAME OVER: "+score, 10, 30);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(playing){
            //intersection
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYDirection=-ballYDirection;
            }
            A : for(int i=0; i<map.map.length; i++){
                for (int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] >0){
                        int brickX = j*map.brickWidth +80;
                        int brickY = i* map.brickHeight +50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brickRect=rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(i,j,0);
                            totalBricks--;
                            score += 5;
                            if (ballPosX + 19 <= brickRect.x|| ballPosX+1 >=brickRect.x+brickRect.width){
                                ballXDirection=-ballXDirection;
                            }
                            else {
                                ballYDirection=-ballYDirection;
                            }
                            break A;
                        }
                    }
                }
            }

            ballPosX+=ballXDirection;
            ballPosY+=ballYDirection;
            if(ballPosX<0){
                ballXDirection=-ballXDirection;
            }
            if(ballPosY<0){
                ballYDirection=-ballYDirection;
            }
            if(ballPosX>670){
                ballXDirection=-ballXDirection;
            }
        }
        repaint();

    }


    //using key pressed
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
    }
    public void moveRight() {
        playing=true;
        playerX+=20;
    }
    public void moveLeft() {
        playing=true;
        playerX-=20;
    }

}
