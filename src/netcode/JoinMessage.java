import java.net.SocketAddress;
import java.io.Serializable;

public class JoinMessage extends Message implements Serializable
{
	public JoinMessage()
	{
		payload = null;
	}

	public JoinMessage(SocketAddress masterIP)
	{
		payload = masterIP;
	}

	public SocketAddress getPayload()
	{
		return (SocketAddress)payload;
	}
}
