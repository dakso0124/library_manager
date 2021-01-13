package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import books.Book;
import members.MemberInfo;

public class LibraryDataBase
{
	public final static String bookSeparator = "|";
	public final static String memberSeparator = ";";
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

		System.out.println("회원정보를 업데이트 했습니다.");
	}
	
	// 도서 목록 불러오기
	public ArrayList<Book> loadBookList()
	{
		BufferedReader br = null;
		ArrayList<Book> bookList = new ArrayList<Book>();

		try
		{
			br = new BufferedReader(new FileReader(m_rootPath));
		}
		catch (FileNotFoundException e) // new FileReader
		{
			System.out.println("도서목록 파일이 없습니다.");

			return bookList;
		}

		String line = null;
		
		try
		{
			while ((line = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(line, bookSeparator);

				String id = st.nextToken();
				String title = st.nextToken();
				String author = st.nextToken();
				String rentalMemberID = st.nextToken();
				
				Book book = new Book(id, title, author);
				
				if(rentalMemberID.equals("null"))
				{
					book.setRental(false);
				}
				else
				{
					book.setRental(true);
					book.setRentalMemberID(rentalMemberID);
				}
				
				bookList.add(book);
			}
		}
		catch (IOException e)
		{
			System.out.println("도서 목록을 읽어오는 도중 문제가 발생하였습니다.");

			return bookList;
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
			}
			catch (IOException e) // br.close()
			{
				System.out.println("도서 목록을 읽어오는 도중 문제가 발생하였습니다. ");
			}
		}

		System.out.println("도서목록을 불러오는데 성공했습니다.");
		return bookList;
	}
	
	public ArrayList<MemberInfo> loadMemberList()
	{
		BufferedReader br = null;
		ArrayList<MemberInfo> memberList = new ArrayList<MemberInfo>();

		try
		{
			br = new BufferedReader(new FileReader(m_rootPath));
		}
		catch (FileNotFoundException e) // new FileReader
		{
			System.out.println("회원 파일이 없습니다.");

			return memberList;
		}

		String line = null;
		try
		{
			while ((line = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(line, bookSeparator);

				String id = st.nextToken();
				String name = st.nextToken();
				String phone = st.nextToken();
				String age = st.nextToken();
				
				MemberInfo member = new MemberInfo(id, name, phone, Integer.parseInt(age));
				memberList.add(member);
			}
		}
		catch (IOException e)
		{
			System.out.println("회원 목록을 읽어오는 도중 문제가 발생하였습니다.");

			return memberList;
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
			}
			catch (IOException e) // br.close()
			{
				System.out.println("회원 목록을 읽어오는 도중 문제가 발생하였습니다. ");
			}
		}

		System.out.println("회원목록을 불러오는데 성공했습니다.");
		return memberList;
	}
}
