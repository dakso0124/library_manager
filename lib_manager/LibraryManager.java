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
			System.out.println("�޴��� �������ּ���.\n1. ���� ����\n2. ȸ�� ����\n3. ����");
			
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
						System.out.println("�޴��� ���ڷ� �Է���  �ּ���.");
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
