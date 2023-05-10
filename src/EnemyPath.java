import greenfoot.*;

public class EnemyPath extends Actor {
    private static final GreenfootImage IMAGE = new GreenfootImage("PathHidden.png");
    public EnemyPath(){
        setImage(IMAGE);
    }
    public static GreenfootImage image(){
        return IMAGE;
    }
    public static boolean check(Color color){
//        System.out.println("New " + color);
//        System.out.println("Check " + IMAGE.getColorAt(3, 488));
//        Greenfoot.stop();
        return color.equals(IMAGE.getColorAt(3, 488));
    }
}