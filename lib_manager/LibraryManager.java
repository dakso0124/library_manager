package lib_manager;

import java.util.NoSuchElementException;

import main.ScannerInstance;

public class LibraryManager
{
	private BookManager bookManager;
	private MemberManager memberManager;
	
	public void init()
	{		
		bookManager = new BookManager();
		memberManager = new MemberManager();
		
		bookManager.init();
		memberManager.init();
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
						bookManager.showMenu();
						break;
					case "2":
						memberManager.showMenu();
						break;
					case "3":
						finishManager();
						break;
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
	
	private void finishManager()
	{
		ScannerInstance.finish();
	}
}
