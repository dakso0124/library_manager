package books;

import info.Info;
import main.LibraryDataBase;

public class Book extends Info
{
	private String m_title;
	private String m_author;
	
	private boolean m_rental;
	private String m_rentalMemberID;
	
	public Book()
	{
		
	}
	
	public Book(String bookID, String title, String author)
	{
		super(bookID);
		
		m_title  = title;
		m_author = author;
		
		m_rentalMemberID = null;
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
		if(!bRental)
			m_rentalMemberID = null;
		
		m_rental = bRental;
	}
	
	public boolean getRental()
	{
		return m_rental;
	}
	
	public void setRentalMemberID(String rentalMemberID)
	{
		m_rentalMemberID = rentalMemberID;
	}
	
	public String getRentalMemberID()
	{
		return m_rentalMemberID;
	}

	public void setInformation(String bookID, String title, String author)
	{
		m_ID = bookID;
		m_title = title;
		m_author = author;
	}
	
	@Override
	public void showInfo()
	{
		System.out.println(String.format("ID : %s, 제목 : %s, 저자 : %s", m_ID, m_title, m_author));
	}
	
	@Override
	public String toString()
	{			
		return this.m_ID + LibraryDataBase.bookSeparator + this.m_title + LibraryDataBase.bookSeparator
				+ this.m_author + LibraryDataBase.bookSeparator + (m_rentalMemberID == null? "null":this.m_rentalMemberID) ;
	}
}