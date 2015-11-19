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

    public int incX()
    {
        return x++;
    }

    public int incY()
    {
        return y++;
    }

    public int decX()
    {
        return x--;
    }

    public int decY()
    {
        return y--;
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
