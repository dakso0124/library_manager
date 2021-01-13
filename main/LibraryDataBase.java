package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import books.Book;
import members.MemberInfo;

public class LibraryDataBase
{
	public final static String infoSeparator = "|";
	public final static String rentalSeparator = "`";
	
	private String 	m_rootPath	= null;
	private File 	m_file 		= null;
	
	public LibraryDataBase()
	{
		m_file = new File("");
		m_rootPath = m_file.getAbsolutePath() + "\\contact_list.txt";
	}
	
	public void SaveBookList(ArrayList<Book> bookList)
	{
		BufferedWriter bw = null;

		try
		{			
			bw = new BufferedWriter(new FileWriter(m_rootPath));
			
			
			for(int i = 0 ; i < bookList.size(); i++)
			{
				Book book = bookList.get(i);
				
				bw.write(book.toString());
				bw.newLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("데이터를 저장하는 도중 문제가 발생하였습니다.");
		}
		finally
		{
			try
			{
				if (bw != null)
				{
					bw.close();
				}
			}
			catch (IOException e)
			{
				System.out.println("데이터를 저장하는 도중 문제가 발생하였습니다.");
			}
		}

		System.out.println("도서 데이터를 업데이트 했습니다.");
	}
	
	public void SaveMemberList(ArrayList<MemberInfo> memberList)
	{
		BufferedWriter bw = null;

		try
		{			
			bw = new BufferedWriter(new FileWriter(m_rootPath));
			
			
			for(int i = 0 ; i < memberList.size(); i++)
			{
				MemberInfo member = memberList.get(i);
				
				bw.write(member.toString());
				bw.newLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("데이터를 저장하는 도중 문제가 발생하였습니다.");
		}
		finally
		{
			try
			{
				if (bw != null)
				{
					bw.close();
				}
			}
			catch (IOException e)
			{
				System.out.println("데이터를 저장하는 도중 문제가 발생하였습니다.");
			}
		}

		System.out.println("도서 데이터를 업데이트 했습니다.");
	}
}
