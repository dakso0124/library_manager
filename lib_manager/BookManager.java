package lib_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

import books.Book;
import main.ScannerInstance;

public class BookManager
{
	private ArrayList<Book> bookList;
	private String m_lastBookID;
	
	public void init()
	{
		bookList 	 = new ArrayList<Book>();
		
		m_lastBookID = null;
	}
	
	public void showMenu()
	{
		while(true)
		{
			System.out.println("도서 관리 메뉴입니다. 메뉴를 선택해주세요.\n1. 도서 추가\n2. 도서 검색\n3. 도서 대여\n4. 도서 반납\n 5. 도서 분실\n6. 이전 메뉴로");
			
			String temp = ScannerInstance.sc.nextLine();
			
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
					break;
					
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
				title = ScannerInstance.sc.nextLine();
				
				System.out.print("저자를 입력해 주세요 : ");
				author = ScannerInstance.sc.nextLine();
				
				int tryCount = 0;
				
				while(true)
				{
					System.out.print(String.format("제목과 저자가 %s, %s가 맞습니까? Y/N", title, author));
					
					String temp = ScannerInstance.sc.nextLine();
					
					if(temp.toUpperCase().equals("Y"))
					{
						bookList.add(new Book(m_lastBookID,title, author));
						System.out.println(String.format("도서  : %s, ID : %s 추가되었습니다.", title, m_lastBookID));
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
		while(true)
		{
			System.out.println("도서 검색 메뉴입니다. 원하는 메뉴를 선택해 주세요.\n1. 전체 도서 검색(제목 순)\n2. 전체 도서 검색(ID 순)\n"
					+ "3. 저자 검색\n4. 제목 검색\n5. 이전메뉴로");
			String temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
					searchAll(true);
					break;
					
				case "2":
					searchAll(false);
					break;
					
				case "3":
					System.out.print("저자의 이름을 입력하세요 : ");
					temp = ScannerInstance.sc.nextLine();
					ArrayList<Book> authorList = search(temp, false);
					
					if(authorList.size() == 0)
						System.out.println("입력한 저자의 책이 없습니다.");
					else
						showBookInfo(authorList);
					
					break;
					
				case "4":
					System.out.print("제목을 입력하세요 : ");
					temp = ScannerInstance.sc.nextLine();
					ArrayList<Book> titleList = search(temp, false);
					
					if(titleList.size() == 0)
						System.out.println("입력한 제목의 책이 없습니다.");
					else
						showBookInfo(titleList);
					
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
		ArrayList<Book> tempList = bookList;
		
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
				String temp = ScannerInstance.sc.nextLine();
				
				switch(temp)
				{
					case "1":
						System.out.print("대여할 도서의 제목을 입력해 주세요 : ");
						temp = ScannerInstance.sc.nextLine();
						
						boolean bExist = false;

						for(int i = 0; i < bookList.size(); i++)
						{
							if(bookList.get(i).getTitle().equals(temp))
							{
								bExist = true;
								if(!bookList.get(i).getRental())
								{
									bookList.get(i).setRental(true);
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
						temp = ScannerInstance.sc.nextLine();

						for(int i = 0; i < bookList.size(); i++)
						{
							if(bookList.get(i).getID().equals(temp))
							{
								if(bookList.get(i).getRental())
								{
									System.out.println("해당 도서는 이미 대여중입니다.");
								}
								else
								{
									bookList.get(i).setRental(true);
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
		String temp = ScannerInstance.sc.nextLine();
		
		for(int i = 0; i < bookList.size(); i++)
		{
			if(bookList.get(i).getID().equals(temp))
			{
				if(bookList.get(i).getRental())
				{
					bookList.get(i).setRental(false);
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
		String temp = ScannerInstance.sc.nextLine();
		
		for(int i = 0; i < bookList.size(); i++)
		{
			if(bookList.get(i).getID().equals(temp))
			{
				System.out.println(String.format("도서 '%s' 분실처리를 완료했습니다.",bookList.remove(i).getTitle()));
				return;
			}
		}
	}
	
	private ArrayList<Book> search(String target, boolean bAuthor)
	{
		ArrayList<Book> result = new ArrayList<Book>();
		
		for(int i = 0 ; i < bookList.size(); i++)
		{
			if(bAuthor)
			{
				if(bookList.get(i).getAuthor().equals(target))
				{
					result.add(bookList.get(i));
				}
			}
			else
			{
				if(bookList.get(i).getTitle().equals(target))
				{
					result.add(bookList.get(i));
				}
			}
		}
		
		return result;
	}
}