package utility;

/**
 * Created by Sorin on 11/18/2015.
 */
public class Camera
{
    private int x = 0, y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int incX(int i)
    {
        return x+=i;
    }

    public int incY(int i)
    {
        return y+=i;
    }

    public int decX(int i)
    {
        return x-=i;
    }

    public int decY(int i)
    {
        return y-=i;
    }

    public void setFocus(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "X: "+x+"; Y: "+y;
    }
}
