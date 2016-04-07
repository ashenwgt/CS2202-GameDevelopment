import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;


public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;

	BufferedImage buffer; // Create the buffer
        Ball ball;
        Bat bat;
        PC pc;
        boolean isLeft;
        boolean isRight;
        
	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
		buffer = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
		// the drawing stuff
                ball=new Ball(dim.width,dim.height,0,0,10,5);
                bat=new Bat(dim.width,dim.height,dim.width/2-50,dim.height-20,100,20,5);
                pc = new PC(dim.width,dim.height,dim.width/2-50,0,100,20,5);
                isLeft=false;
                isRight=false;           
	}

	/**
	 * Start the game
	 */
	public void Start() {
		while (true) {
                        ball.update();
                        pc.update();
                        if(isLeft)bat.setLeft();
                        if(isRight)bat.setRight();
                        bat.update();
                        detectCollision();
			// Draw the buffer
			drawBuffer();
			// Paint the buffer on screen
			drawScreen();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
        public void detectCollision(){
            Rectangle rectBall=new Rectangle(ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
            Rectangle rectBat=new Rectangle(bat.getX(),bat.getY(),bat.getW(),bat.getH());
            Rectangle rectPC=new Rectangle(pc.getX(),pc.getY(),pc.getW(),pc.getH());
            if (ball.getY()==bat.getY()){
                if(rectBall.intersects(rectBat)){
                    ball.reverse();
                } else{
                    int reply = JOptionPane.showConfirmDialog(null, "You lose! Do you want to Replay?", "Replay?", JOptionPane.YES_NO_OPTION);
                    // To replay, y-cordinate of the ball is set to the top (i.e. 0) and start the game again
                    if (reply == JOptionPane.YES_OPTION) { 
                            ball.setY(0);  
                            Start();
                    } else{
                        System.exit(0);
                    }
                }
            } else if (ball.getY() == pc.getY()){
                if(rectBall.intersects(rectPC)){
                    ball.reverse();
                }
            }
        }
	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
		Graphics2D b = buffer.createGraphics();
		
		// background color
		b.setColor(Color.BLACK);
		b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
                
                b.setColor(Color.WHITE);
                b.fillOval(ball.getX(), ball.getY(),ball.getSize(),ball.getSize());
                
                b.setColor(Color.RED);                
                b.fillRect(bat.getX(), bat.getY(), bat.getW(), bat.getH());
                
                b.setColor(Color.YELLOW);                
                b.fillRect(pc.getX(), pc.getY(), pc.getW(), pc.getH());
                
                // tennis net color
                b.setColor(Color.BLUE);                
                b.fillRect(0, buffer.getHeight()/2, buffer.getWidth(), 1);
                
                // label 2 sides
                b.setColor(Color.GREEN);
                b.drawString("PC", 10, buffer.getHeight()/2-10);
                b.drawString("User", 10, buffer.getHeight()/2+20);
        }

	/**
	 *  Update it to the screen
	 */
	public void drawScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
                //draw using the g instead of buffer and check the program                   
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        if(e.getKeyCode()==37){
            isLeft=true;  
        }
        if(e.getKeyCode()==39){
            isRight=true;              
        }      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37){
            isLeft=false;
        }
        if(e.getKeyCode()==39){
            isRight=false;   
        }
    }
}