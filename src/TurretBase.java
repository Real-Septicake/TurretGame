import greenfoot.*;

public class TurretBase extends Actor
{
    int[] w;
    int[] h;

    int id;
    private static int count = 0;
    public boolean buying = true;
    public TurretBase(GreenfootImage image){
        setImage(image);
        //System.out.println("Before: " + count);
        id = count++;
        //System.out.println("Count After: " + count);
        h = new int[]{-(getImage().getHeight()/2), getImage().getHeight()/2};
        w = new int[]{-(getImage().getWidth()/2), getImage().getWidth()/2};
    }

    public void act() {
        if(buying && Greenfoot.getMouseInfo() != null){
            setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
    }

    public boolean bounds(){
        boolean t = true;
        for(int i : w){
            for(int j : h){
                t = (getX() + i > 0 && getY() + j > 0 && getX() + i < MyWorld.IMAGE.getWidth() && getY() + j < MyWorld.IMAGE.getHeight() && t);
            }
        }
        return t;
    }

    public boolean place(){
        if(getOneIntersectingObject(TurretBase.class) != null){
            return false;
        }
        for(int i : w){
            for(int j : h){
                if(MyWorld.check(MyWorld.IMAGE.getColorAt(getX()+i, getY()+j))){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canPlace(){
        return bounds() && place();
    }

    public int getID(){
        return id;
    }
}
