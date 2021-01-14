package members;

import info.Info;
import main.LibraryDataBase;

import java.util.ArrayList;

import books.Book;

public class MemberInfo extends Info
{
	private String m_name;
	private String m_phoneNumber;
	private int m_age;
	
	private ArrayList<Book> m_rentalList;
	
	public MemberInfo()
	{
		
	}
	
	public MemberInfo(String ID, String name, String phoneNumber, int age)
	{
		super(ID);
		
		m_name = name;
		m_phoneNumber = phoneNumber;
		m_age = age;
		
		m_rentalList = new ArrayList<Book>();
	}

	public String getName()
	{
		return m_name;
	}
	
	public void setName(String name)
	{
		m_name = name;
	}

	public String getPhoneNumber()
	{
		return m_phoneNumber;
	}
	
	public void setPhoneNumber(String phone)
	{
		m_phoneNumber = phone;
	}

	public int getAge()
	{
		return m_age;
	}

	public ArrayList<Book> getRentalList()
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

	@Override
	public void showInfo()
	{
		System.out.print(String.format("ID : %s, 이름 : %s, 전화번호 : %s, 나이 : %d\n대여 목록 : ", m_ID, m_name, m_phoneNumber, m_age));
		
		for(int i = 0 ; i < m_rentalList.size(); i++)
		{
			System.out.print(m_rentalList.get(i).getTitle() + " ");
		}
	}
	
	// id 매칭해야하는지 확인 필요.
	public void rentalBook(Book book, boolean bRental)
	{
		if(bRental)
		{
			m_rentalList.add(book);
		}
		else
		{
			if(m_rentalList.contains(book))
			{
				m_rentalList.remove(book);
			}
		}
	}
	
	@Override
	public String toString()
	{
		String result = this.m_ID + LibraryDataBase.memberSeparator + this.m_name + LibraryDataBase.memberSeparator
				+ this.m_phoneNumber + LibraryDataBase.memberSeparator + this.m_age;

		return  result;
	}
}