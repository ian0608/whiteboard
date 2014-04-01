package netcode;

import java.net.SocketAddress;
import java.io.Serializable;

public class JoinMessage implements Message, Serializable
{
	private SocketAddress masterAddress;

	public JoinMessage()
	{
		masterAddress = null;
	}

	public JoinMessage(SocketAddress masterAddress)
	{
		this.masterAddress = masterAddress;
	}

	public SocketAddress getPayload()
	{
		return masterAddress;
	}
}
