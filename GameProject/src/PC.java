public class PC extends GameEntity {
    
    private int dx;   

    public PC(int gameWidth, int gameHeight,int x, int y, int w,int h,int speed) {
        super(gameWidth,gameHeight,x,y,w,h,speed);
        dx=speed;
    }
    
    @Override
    public void update(){
        //check the edges    
        if(x>(gameWidth-w) || x<0)
            dx*=-1; 
        // PC's bat always changes its position with Ball's x-cordinate, so PC never loses 
        x = Ball.getBallX();
    }
   
    
}