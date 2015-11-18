/**
 * Created by Onyx on 18-Nov-15.
 */
public class Main implements Runnable
{
    Thread main;
    public static void main(String[] args)
    {

    }

    @Override
    public void run() {
        while (main != null) {
            try {Thread.sleep(1000);}
            catch (InterruptedException e) {}
        }
    }
}
