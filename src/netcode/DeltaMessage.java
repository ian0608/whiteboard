package netcode;

import java.util.List;
import java.io.Serializable;

public class DeltaMessage implements Message, Serializable
{
	private List<Pixel> pixels;

	public DeltaMessage()
	{
		pixels = null;
	}

	public DeltaMessage(List<Pixel> pixels)
	{
		this.pixels = pixels;
	}

	public List<Pixel> getPayload()
	{
		return pixels;
	}

    public String toString() {
        String ret = "[";
        for (Pixel pixel : pixels){
            ret += pixel+",";
        }
        ret+="]";
        return ret;
    }
}
