import greenfoot.*;

public class PathFollow extends Actor {
    private final int x;
    private final int y;
    private static final int gap=2;
    public PathFollow(int r, int X, int Y){
        setRotation(r);
        x=X;
        y=Y;
    }
    public void act(){}
    public int pathCheck(){
        GreenfootImage check=new GreenfootImage(7,25);
        check.setColor(Color.BLACK);
        check.fill();
        setImage(check);
        GreenfootImage path = EnemyPath.image();
        setLocation(x,y);
        move(21);
        turn(90);
        move(gap);
        //Greenfoot.delay(4);
        if(EnemyPath.check(path.getColorAt(getX(),getY()+21))){
            return 1;
        }
        turn(180);
        move(gap * 2);
        turn(180);
        //Greenfoot.delay(10);
        if (EnemyPath.check(path.getColorAt(getX(), getY() + 21))) {
            return 2;
        }
        return 0;
    }
}