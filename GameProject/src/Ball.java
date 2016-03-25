public class Ball extends GameEntity{
      
    private int dx;
    private int dy;

    public Ball(int gameWidth, int gameHeight,int x, int y, int size,int speed) {
        super(gameWidth,gameHeight,x,y,size,size,speed);        
        dx=speed;
        dy=speed;
    }    
    
    @Override
    public void update(){   
        if(x>(gameWidth-w) || x<0)
            dx*=-1;          
        if(y>(gameHeight-w) || y<0)
            dy*=-1;
        x+=dx;
        y+=dy;          
    }

    public int getSize() {
        return w;
    }
    
    public void reverse(){
        dy=-dy;
    }
}
