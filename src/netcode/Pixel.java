import java.io.Serializable;

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
}
