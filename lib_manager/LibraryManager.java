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
			System.out.println("�޴��� �������ּ���.\n1. ���� ����\n2. ȸ�� ����\n3. ����");
			
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
	
	private void finishManager()
	{
		ScannerInstance.finish();
	}
}
