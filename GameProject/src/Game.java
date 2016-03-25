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

public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;

	BufferedImage buffer; // Create the buffer
        Ball ball;
        Bat bat;
        boolean isLeft;
        boolean isRight;
        
	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
		buffer = new BufferedImage(dim.width, dim.height,
				BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
										// the drawing stuff
                ball=new Ball(dim.width,dim.height,0,0,10,5);
                bat=new Bat(dim.width,dim.height,dim.width/2-50,dim.height-20,100,20,5);
                isLeft=false;
                isRight=false;
                
                
	}

	/**
	 * Start the game
	 */
	public void Start() {

		while (true) {
                        ball.update();
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
            if(rectBall.intersects(rectBat)){
                ball.reverse();
            }
        }
	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
		Graphics2D b = buffer.createGraphics();
		
		// Random color background
		//Color c = new Color(new Random().nextInt());
		b.setColor(Color.BLACK);
		b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
                
                b.setColor(Color.WHITE);
                b.fillOval(ball.getX(), ball.getY(),ball.getSize(),ball.getSize());
                
                b.setColor(Color.RED);
                
                b.fillRect(bat.getX(), bat.getY(), bat.getW(), bat.getH());
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