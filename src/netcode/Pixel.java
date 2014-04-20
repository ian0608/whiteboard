package netcode;

import java.io.Serializable;
import java.util.Random;

public class Pixel implements Serializable
{
	private int x;
	private int y;
	private boolean filledIn;
	
	public Pixel(int x, int y, boolean filledIn)
	{
		this.x = x;
		this.y = x;
		this.filledIn = filledIn;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean filledIn()
	{
		return filledIn;
	}

    /** This method creates a random pixel for testing purposes **/
    public static Pixel createRandomPixel() {
        Random random = new Random();
        Pixel pixel = new Pixel(random.nextInt(), random.nextInt(), random.nextBoolean());
        return pixel;
    }

    public String toString() {
        return String.format("{x: %d, y: %d, filledIn : %b}", getX(), getY(), filledIn());
    }

}
