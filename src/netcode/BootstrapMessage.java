import java.io.Serializable;

public class BootstrapMessage extends Message implements Serializable
{
	public BootstrapMessage()
	{
		payload = null;
	}
}
