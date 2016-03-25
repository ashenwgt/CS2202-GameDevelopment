public abstract class GameEntity {
    protected int x;
    protected int y; 
    protected int w;
    protected int h;
    protected int speed;
    protected int gameWidth;
    protected int gameHeight;
    
    public GameEntity(int gameWidth, int gameHeight,int x, int y, int w,int h,int speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.speed=speed;       
    }
    
    public abstract void update();
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    
    public void setY(int i){
        this.y = i;
    }
}
