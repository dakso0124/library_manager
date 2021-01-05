package info;

public class Info
{
	protected String m_ID;
	
	protected Info()
	{
		m_ID = null;
	}
	
	protected Info(String ID)
	{
		m_ID = ID;
	}
	
	public String getID()
	{
		return m_ID;
	}
}
