public class PC extends GameEntity {
    
    private int dx;
    

    public PC(int gameWidth, int gameHeight,int x, int y, int w,int h,int speed) {
        super(gameWidth,gameHeight,x,y,w,h,speed);
        
    }
    
    @Override
    public void update(){
        //check the edges
       x=x+dx;
        if(x<=0){
           x=0;
       }
       if(x>gameWidth-w){
           x=gameWidth-w;
       }
       dx=0;
    }
   
    public void setLeft(){
        this.dx=-this.speed;
       
    }
    
    public void setRight(){
        this.dx=this.speed;
    }
    
}