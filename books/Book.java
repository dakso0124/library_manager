package books;

import info.Info;

public class Book extends Info
{
	private String m_title;
	private String m_author;
	
	private boolean m_rental;
	
	public Book()
	{
		
	}
	
	public Book(String bookID, String title, String author)
	{
		super(bookID);
		
		m_title  = title;
		m_author = author;
	}
	
	public String getTitle()
	{
		return m_title;
	}

	public String getAuthor()
	{
		return m_author;
	}
	
	public void setRental(boolean bRental)
	{
		m_rental = bRental;
	}
	
	public boolean getRental()
	{
		return m_rental;
	}

	public void setInformation(String bookID, String title, String author)
	{
		m_ID = bookID;
		m_title = title;
		m_author = author;
	}
}