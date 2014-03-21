import java.io.Serializable;

public class Pixel implements Serializable
{
	private int x;
	private int y;
	
	public Pixel(int x, int y)
	{
		this.x = x;
		this.y = x;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}
