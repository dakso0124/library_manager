package lib_manager;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import books.Book;
import main.LibraryDataBase;
import main.ScannerInstance;
import members.MemberInfo;

public class LibraryManager
{
	private static LibraryManager instance;
	
	public static LibraryManager getInstance()
    {
    	if(instance == null)
    		instance = new LibraryManager();
    	
        return instance;
    }
	private LibraryDataBase dataManager;
	
	private LibraryManager()
	{
		init();
	}
	
	public void init()
	{
		dataManager = new LibraryDataBase();
		
		dataManager.loadLibraryInfo();
	}
	
	public void startManager()
	{
		while(true)
		{
			System.out.println("메뉴를 선택해주세요.\n1. 도서 관리\n2. 회원 관리\n3. 종료");
			
			try
			{
				String temp = ScannerInstance.getInstance().nextLine();

				switch(temp)
				{
					case "1":
						BookManager.getInstance().showMenu();
						break;
					case "2":
						MemberManager.getInstance().showMenu();
						break;
					case "3":
						finishManager();
						return;
					default:
						System.out.println("메뉴를 숫자로 입력해  주세요.");
						break;
				}
			}
			catch(NoSuchElementException e)
			{
				
			}
			catch(IllegalStateException e)
			{
				
			}
		}
	}
	
	public void saveMemberData(ArrayList<MemberInfo> memberList)
	{
		dataManager.SaveMemberList(memberList);
	}
	
	public void saveBookData(ArrayList<Book> bookList)
	{
		dataManager.SaveBookList(bookList);
	}
	
	private void finishManager()
	{
		ScannerInstance.finish();
	}
}
