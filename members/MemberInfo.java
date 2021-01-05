package members;

import info.Info;

import java.util.ArrayList;

public class MemberInfo extends Info
{
	private String m_name;
	private String m_phoneNumber;
	private int m_age;
	
	private ArrayList<String> m_rentalList;
	
	public MemberInfo()
	{
		
	}
	
	public MemberInfo(String ID, String name, String phoneNumber, int age)
	{
		super(ID);
		
		m_name = name;
		m_phoneNumber = phoneNumber;
		m_age = age;
		
		m_rentalList = new ArrayList<String>();
	}

	public String getName()
	{
		return m_name;
	}

	public String getPhoneNumber()
	{
		return m_phoneNumber;
	}

	public int getAge()
	{
		return m_age;
	}

	public ArrayList<String> getRentalList()
	{
		return m_rentalList;
	}

	public void setInformation(String ID, String name, String phoneNumber, int age)
	{
		m_ID 			= ID;
		m_name 			= name;
		m_phoneNumber	= phoneNumber;
		m_age 			= age;
	}
	
	
}
