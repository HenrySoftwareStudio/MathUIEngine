import UI.Window;
import java.io.Serializable;

public class Engine implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6276476245807073086L;
	
	public static Engine self;
	public Engine()
	{
		new Window("Math UI Engine").toggleAfterRunnable(null, false);
		self = this;
	}

	public static void main(String[] args)
	{
		new Engine();
	}

}
