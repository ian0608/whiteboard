import java.util.ArrayList;
import java.io.Serializable;

public class DeltaMessage extends Message implements Serializable
{
	public DeltaMessage(ArrayList<Pixel> pixels)
	{
		payload = pixels;
	}

	public ArrayList<Pixel> getPayload()
	{
		return (ArrayList<Pixel>)payload;
	}
}
