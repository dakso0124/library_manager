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
import lib_manager.BookManager;
import lib_manager.MemberManager;
import members.MemberInfo;

public class LibraryDataBase
{
	public final static String bookSeparator = "|";
	public final static String memberSeparator = ";";
	
	private File 	m_file 		 = null;

	private String 	m_bookPath	 = null;
	private String 	m_memberPath = null;
	
	public LibraryDataBase()
	{
		m_file = new File("");
		m_bookPath = m_file.getAbsolutePath() + "\\book_list.txt";
		m_memberPath = m_file.getAbsolutePath() + "\\member_list.txt";
	}
	
	// save book list
	public void SaveBookList(ArrayList<Book> bookList)
	{
		BufferedWriter bw = null;

		try
		{			
			bw = new BufferedWriter(new FileWriter(m_bookPath));

			for(int i = 0 ; i < bookList.size(); i++)
			{
				Book book = bookList.get(i);

				bw.write(book.toString());
				bw.newLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("�����͸� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
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
				System.out.println("�����͸� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
			}
		}

		System.out.println("���� �����͸� ������Ʈ �߽��ϴ�.");
	}
	
	// save member list
	public void SaveMemberList(ArrayList<MemberInfo> memberList)
	{
		BufferedWriter bw = null;

		try
		{			
			bw = new BufferedWriter(new FileWriter(m_memberPath));

			for(int i = 0 ; i < memberList.size(); i++)
			{
				MemberInfo member = memberList.get(i);

				bw.write(member.toString());
				bw.newLine();
			}
		}
		catch (IOException e)
		{
			System.out.println("�����͸� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
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
				System.out.println("�����͸� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
			}
		}

		System.out.println("ȸ�������� ������Ʈ �߽��ϴ�.");
	}
	
	// ����, ȸ�� ��� �ҷ�����
	public void loadLibraryInfo()
	{
		BufferedReader br = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		ArrayList<Book> rentalList = new ArrayList<Book>();
		
		boolean bFileExist = true;

		try
		{
			br = new BufferedReader(new FileReader(m_bookPath));
		}
		catch (FileNotFoundException e) // new FileReader
		{
			bFileExist = false;
			System.out.println("������� ������ �����ϴ�.");

			//return bookList;
		}
		
		String line = null;

		if(bFileExist)
		{			
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
					
					if(rentalMemberID.equals("none"))
					{
						book.setRental(false);
					}
					else
					{
						book.setRental(true);
						book.setRentalMemberID(rentalMemberID);
						
						rentalList.add(book);
					}
					
					bookList.add(book);
				}
			}
			catch (IOException e)
			{
				System.out.println("���� ����� �о���� ���� ������ �߻��Ͽ����ϴ�.");

				//return bookList;
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
					System.out.println("���� ����� �о���� ���� ������ �߻��Ͽ����ϴ�. ");
				}
			}
			BookManager.getInstance().setBookList(bookList);
			System.out.println("��������� �ҷ����µ� �����߽��ϴ�.");
		}
		
		bFileExist = true;
		
		// member info load start
		ArrayList<MemberInfo> memberList = new ArrayList<MemberInfo>();
		
		try
		{
			br = new BufferedReader(new FileReader(m_memberPath));
		}
		catch (FileNotFoundException e) // new FileReader
		{
			bFileExist = false;
			System.out.println("ȸ�� ������ �����ϴ�.");

			//return memberList;
		}
		
		if(bFileExist)
		{
			try
			{
				while ((line = br.readLine()) != null)
				{
					StringTokenizer st = new StringTokenizer(line, memberSeparator);

					String id = st.nextToken();
					String name = st.nextToken();
					String phone = st.nextToken();
					String age = st.nextToken();
					
					MemberInfo member = new MemberInfo(id, name, phone, Integer.parseInt(age));
					memberList.add(member);
				}
				
				// ���� �뿩 ����
				for(int i = 0 ; i < rentalList.size(); i++)
				{
					for(int k = 0 ; k < memberList.size(); k++)
					{
						if(rentalList.get(i).getRentalMemberID().equals(memberList.get(k).getID()))
						{
							memberList.get(k).rentalBook(rentalList.get(i), true);
						}
					}
				}
			}
			catch (IOException e)
			{
				System.out.println("ȸ�� ����� �о���� ���� ������ �߻��Ͽ����ϴ�.");
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
					System.out.println("ȸ�� ����� �о���� ���� ������ �߻��Ͽ����ϴ�. ");
				}
			}
			
			MemberManager.getInstance().setMemberInfo(memberList);

			System.out.println("ȸ������� �ҷ����µ� �����߽��ϴ�.");
		}
	}
}