import greenfoot.*;

public class TurretBase extends Actor
{
    int[] w;
    int[] h;

    int id;
    private static int count = 0;
    private boolean canPlace = true;
    private boolean buying = true;
    public TurretBase(String imageFileName){
        setImage(imageFileName);
        id = count++;
        h = new int[]{0, getImage().getHeight()};
        w = new int[]{0, getImage().getWidth()};
    }

    public void act() 
    {
        if(buying){
            if(canPlace && Greenfoot.mouseClicked(this)){

            }
        }
    }

    public void bounds(){
        boolean t = true;
        for(int i : w){
            for(int j : h){
                t = (getX() + i > 0 && getY() + j > 0 && getX() + i < MyWorld.image().getWidth() && getY() + j < MyWorld.image().getHeight() && t);
            }
        }
        canPlace = t;
    }

    public boolean place(){
        if(getOneIntersectingObject(TurretBase.class) != null) return false;
        for(int i : w){
            for(int j : h){
                if(MyWorld.check(MyWorld.image().getColorAt(getX()+i, getY()+j))) return false;
            }
        }
        return true;
    }

    public boolean canPlace(){
        return canPlace;
    }
}
