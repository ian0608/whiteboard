import java.net.SocketAddress;
import java.io.Serializable;

public class JoinMessage implements Message, Serializable
{
	private SocketAddress masterIP;

	public JoinMessage()
	{
		masterIP = null;
	}

	public JoinMessage(SocketAddress masterIP)
	{
		this.masterIP = masterIP;
	}

	public SocketAddress getPayload()
	{
		return masterIP;
	}
}
