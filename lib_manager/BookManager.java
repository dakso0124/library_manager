package lib_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import books.Book;
import main.LibraryDataBase;
import main.ScannerInstance;

public class BookManager
{
	private static BookManager instance;
	
	public static BookManager getInstance()
    {
    	if(instance == null)
    		instance = new BookManager();
    	
        return instance;
    }
	
	private ArrayList<Book> m_bookList;
	private String m_lastBookID;
	private int m_lastID;
	
	private BookManager()
	{
		init();
	}
	
	public void init()
	{
		m_lastID 	 = 0;
		m_bookList = new ArrayList<Book>();
		m_lastBookID = String.format("%05d", m_lastID);
	}
	
	public void setBookList(ArrayList<Book> bookList)
	{
		m_bookList = bookList;
	}
	
	public ArrayList<Book> getRentalBookList()
	{
		ArrayList<Book> rentalList = new ArrayList<Book>();
		
		for(int i = 0 ; i < m_bookList.size(); i++)
		{
			if(m_bookList.get(i).getRental())
			{
				rentalList.add(m_bookList.get(i));
			}
		}
		
		return rentalList;
	}
	
	public void showMenu()
	{
		while(true)
		{
			System.out.println("도서 관리 메뉴입니다. 메뉴를 선택해주세요.\n1. 도서 추가\n2. 도서 검색\n3. 도서 대여\n4. 도서 반납\n5. 도서 분실\n6. 이전 메뉴로");
			
			String temp = ScannerInstance.getInstance().nextLine();
			
			switch(temp)
			{
				case "1":
					addBook();
					break;
					
				case "2":
					searchBook();
					break;
					
				case "3":
					rentalBook();
					break;
					
				case "4":
					returnBook();
					break;
					
				case "5":
					lossBook();
					break;
					
				case "6":
					System.out.println("메인 메뉴로 돌아갑니다.");
					return;
					
				default:
					System.out.println("메뉴를 확인하고 제대로 입력해주세요.");
					break;
			}
		}
	}
	
	public void addBook()
	{
		String title	= "";
		String author	= "";
		
		while(true)
		{
			try
			{
				System.out.print("도서를 추가합니다\n제목을 입력해 주세요 : ");
				title = ScannerInstance.getInstance().nextLine();
				
				System.out.print("저자를 입력해 주세요 : ");
				author = ScannerInstance.getInstance().nextLine();
				
				int tryCount = 0;
				
				while(true)
				{
					System.out.print(String.format("제목 : %s\n저자 : %s\n위 정보가 맞습니까? Y/N : ", title, author));
					
					String temp = ScannerInstance.getInstance().nextLine();
					
					if(temp.toUpperCase().equals("Y"))
					{
						m_bookList.add(new Book(m_lastBookID,title, author));
						
						m_lastID++;
						m_lastBookID = String.format("%05d", m_lastID);
						
						LibraryManager.getInstance().saveBookData(m_bookList);
						System.out.println(String.format("제목 : %s, ID : %s 추가되었습니다.", title, m_lastBookID));
						return;
					}
					else if(temp.toUpperCase().equals("N"))
					{
						tryCount++;
						System.out.println("다시 입력합니다.");
						break;
					}
					else
					{
						tryCount++;
						
						if(tryCount == 3)
						{
							System.out.println("도서 추가를 취소하고 이전 메뉴로 돌아갑니다.");
							return;
						}
						System.out.println("제목과 저자를 확인후 Y or N을 입력해주세요.");
						continue;
					}
				}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("");
				continue;
			}
			catch(IllegalStateException e)
			{
				System.out.println("");
				continue;
			}
		}
	}
	
	public void searchBook()
	{
		String temp = null;
		String target = null;
		while(true)
		{
			System.out.println("도서 검색 메뉴입니다. 원하는 메뉴를 선택해 주세요.\n1. 전체 도서 검색(제목 순)\n2. 전체 도서 검색(ID 순)\n"
					+ "3. 저자 검색\n4. 제목 검색\n5. 이전메뉴로");
			temp = ScannerInstance.getInstance().nextLine();
			
			switch(temp)
			{
				case "1":
					searchAll(true);
					break;
					
				case "2":
					searchAll(false);
					break;
					
				case "3":
				case "4":
					Boolean bAuthor = temp.equals("3")? true:false;
					
					System.out.print(String.format("%s 입력하세요 : ", (temp.equals("3")? "저자를" : "제목을") ));
					target = ScannerInstance.getInstance().nextLine();
					ArrayList<Book> authorList = search(target, bAuthor);
					
					if(authorList.size() == 0)
						System.out.println(String.format("%s 책이 없습니다.", (temp.equals("3")? "저자의" : "입력하신 제목의")));
					else
						showBookInfo(authorList);
					
					break;
					
				case "5":
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
					
				default:
					System.out.println("메뉴를 확인하고 제대로 입력해 주세요.");
					break;
			}
			
			break;
		}
	}
	
	public void showBookInfo(ArrayList<Book> showList)
	{
		System.out.println(String.format("총 %d권의 도서가 검색되었습니다.", showList.size()));
		for(int i = 0; i < showList.size(); i++)
		{
			showList.get(i).showInfo();
		}
		System.out.println("-------------------------");
	}
	
	// 전체 검색  title, 회원번호
	private void searchAll(boolean bTitle)
	{
		ArrayList<Book> tempList = m_bookList;
		
		// 제목 정렬
		if(bTitle)
		{
			Collections.sort(tempList, new Comparator() {
				@Override
				public int compare(Object b1, Object b2) {
					return ((Book)b1).getTitle().compareTo(((Book)b2).getTitle());
				}
			});
		}
		else
		{
			Collections.sort(tempList, new Comparator() {
				@Override
				public int compare(Object b1, Object b2) {
					return ((Book)b1).getID().compareTo(((Book)b2).getID());
				}
			});
		}
		
		for(int i = 0 ; i < tempList.size(); i++)
		{
			tempList.get(i).showInfo();
		}
	}
	
	public void rentalBook()
	{
		while(true)
		{
			try
			{
				System.out.print("도서를 대여합니다.\n1. 제목 검색\n2. ID 검색\n3. 이전 메뉴로\n원하는 메뉴를 선택해주세요 : ");
				String temp = ScannerInstance.getInstance().nextLine();
				
				switch(temp)
				{
					case "1":
						System.out.print("대여할 도서의 제목을 입력해 주세요 : ");
						temp = ScannerInstance.getInstance().nextLine();
						
						boolean bExist = false;

						for(int i = 0; i < m_bookList.size(); i++)
						{
							if(m_bookList.get(i).getTitle().equals(temp))
							{
								bExist = true;
								if(!m_bookList.get(i).getRental())
								{
									m_bookList.get(i).setRental(true);
									System.out.println("대여 완료.");
									break;
								}
							}
							
							if(bExist)
								System.out.println("찾으시는 도서가 모두 대여중입니다.");
							else
								System.out.println("찾으시는 도서가 존재하지 않습니다.");
						}
						break;
						
					case "2":
						System.out.print("대여할 도서의 ID를 입력해 주세요 : ");
						temp = ScannerInstance.getInstance().nextLine();

						for(int i = 0; i < m_bookList.size(); i++)
						{
							if(m_bookList.get(i).getID().equals(temp))
							{
								if(m_bookList.get(i).getRental())
								{
									System.out.println("해당 도서는 이미 대여중입니다.");
								}
								else
								{
									m_bookList.get(i).setRental(true);
									System.out.println("대여 완료.");
								}
								break;
							}
						}
						
						break;
					case "3":
						System.out.println("이전 메뉴로 돌아갑니다.");
						return;
					default:
						System.out.println("메뉴를 확인하고 제대로 입력해 주세요.");
						break;
				}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("");
				continue;
			}
			catch(IllegalStateException e)
			{
				System.out.println("");
				continue;
			}
			
			break;
		}
	}
	
	public void returnBook()
	{
		System.out.print("도서를 반납합니다.\n반납한 도서의 ID를 입력해 주세요 : ");
		String temp = ScannerInstance.getInstance().nextLine();
		
		for(int i = 0; i < m_bookList.size(); i++)
		{
			if(m_bookList.get(i).getID().equals(temp))
			{
				if(m_bookList.get(i).getRental())
				{
					m_bookList.get(i).setRental(false);
					System.out.println("도서 반납 완료되었습니다.");
				}
				else
				{
					System.out.println("해당 도서는 대여중이 아닙니다.");
				}
			}
		}
	}
	
	public void lossBook()
	{
		System.out.print("도서 분실 등록 합니다.\n분실된 도서의 ID를 입력해 주세요 : ");
		String temp = ScannerInstance.getInstance().nextLine();
		
		for(int i = 0; i < m_bookList.size(); i++)
		{
			if(m_bookList.get(i).getID().equals(temp))
			{
				System.out.println(String.format("도서 '%s' 분실처리를 완료했습니다.",m_bookList.remove(i).getTitle()));
				return;
			}
		}
	}
	
	private ArrayList<Book> search(String target, boolean bAuthor)
	{
		ArrayList<Book> result = new ArrayList<Book>();
		
		for(int i = 0 ; i < m_bookList.size(); i++)
		{
			if(bAuthor)
			{
				if(m_bookList.get(i).getAuthor().equals(target))
				{
					result.add(m_bookList.get(i));
				}
			}
			else
			{
				if(m_bookList.get(i).getTitle().contains(target))
				{
					result.add(m_bookList.get(i));
				}
			}
		}
		
		return result;
	}
}