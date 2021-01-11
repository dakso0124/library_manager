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
			System.out.println("���� ���� �޴��Դϴ�. �޴��� �������ּ���.\n1. ���� �߰�\n2. ���� �˻�\n3. ���� �뿩\n4. ���� �ݳ�\n 5. ���� �н�\n6. ���� �޴���");
			
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
					System.out.println("���� �޴��� ���ư��ϴ�.");
					break;
					
				default:
					System.out.println("�޴��� Ȯ���ϰ� ����� �Է����ּ���.");
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
				System.out.print("������ �߰��մϴ�\n������ �Է��� �ּ��� : ");
				title = ScannerInstance.sc.nextLine();
				
				System.out.print("���ڸ� �Է��� �ּ��� : ");
				author = ScannerInstance.sc.nextLine();
				
				int tryCount = 0;
				
				while(true)
				{
					System.out.print(String.format("����� ���ڰ� %s, %s�� �½��ϱ�? Y/N", title, author));
					
					String temp = ScannerInstance.sc.nextLine();
					
					if(temp.toUpperCase().equals("Y"))
					{
						bookList.add(new Book(m_lastBookID,title, author));
						System.out.println(String.format("����  : %s, ID : %s �߰��Ǿ����ϴ�.", title, m_lastBookID));
						return;
					}
					else if(temp.toUpperCase().equals("N"))
					{
						tryCount++;
						System.out.println("�ٽ� �Է��մϴ�.");
						break;
					}
					else
					{
						tryCount++;
						
						if(tryCount == 3)
						{
							System.out.println("���� �߰��� ����ϰ� ���� �޴��� ���ư��ϴ�.");
							return;
						}
						System.out.println("����� ���ڸ� Ȯ���� Y or N�� �Է����ּ���.");
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
			System.out.println("���� �˻� �޴��Դϴ�. ���ϴ� �޴��� ������ �ּ���.\n1. ��ü ���� �˻�(���� ��)\n2. ��ü ���� �˻�(ID ��)\n"
					+ "3. ���� �˻�\n4. ���� �˻�\n5. �����޴���");
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
					System.out.print("������ �̸��� �Է��ϼ��� : ");
					temp = ScannerInstance.sc.nextLine();
					ArrayList<Book> authorList = search(temp, false);
					
					if(authorList.size() == 0)
						System.out.println("�Է��� ������ å�� �����ϴ�.");
					else
						showBookInfo(authorList);
					
					break;
					
				case "4":
					System.out.print("������ �Է��ϼ��� : ");
					temp = ScannerInstance.sc.nextLine();
					ArrayList<Book> titleList = search(temp, false);
					
					if(titleList.size() == 0)
						System.out.println("�Է��� ������ å�� �����ϴ�.");
					else
						showBookInfo(titleList);
					
					break;
					
				case "5":
					System.out.println("���� �޴��� ���ư��ϴ�.");
					return;
					
				default:
					System.out.println("�޴��� Ȯ���ϰ� ����� �Է��� �ּ���.");
					break;
			}
			
			break;
		}
	}
	
	public void showBookInfo(ArrayList<Book> showList)
	{
		System.out.println(String.format("�� %d���� ������ �˻��Ǿ����ϴ�.", showList.size()));
		for(int i = 0; i < showList.size(); i++)
		{
			showList.get(i).showInfo();
		}
		System.out.println("-------------------------");
	}
	
	// ��ü �˻�  title, ȸ����ȣ
	private void searchAll(boolean bTitle)
	{
		ArrayList<Book> tempList = bookList;
		
		// ���� ����
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
				System.out.print("������ �뿩�մϴ�.\n1. ���� �˻�\n2. ID �˻�\n3. ���� �޴���\n���ϴ� �޴��� �������ּ��� : ");
				String temp = ScannerInstance.sc.nextLine();
				
				switch(temp)
				{
					case "1":
						System.out.print("�뿩�� ������ ������ �Է��� �ּ��� : ");
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
									System.out.println("�뿩 �Ϸ�.");
									break;
								}
							}
							
							if(bExist)
								System.out.println("ã���ô� ������ ��� �뿩���Դϴ�.");
							else
								System.out.println("ã���ô� ������ �������� �ʽ��ϴ�.");
						}
						break;
						
					case "2":
						System.out.print("�뿩�� ������ ID�� �Է��� �ּ��� : ");
						temp = ScannerInstance.sc.nextLine();

						for(int i = 0; i < bookList.size(); i++)
						{
							if(bookList.get(i).getID().equals(temp))
							{
								if(bookList.get(i).getRental())
								{
									System.out.println("�ش� ������ �̹� �뿩���Դϴ�.");
								}
								else
								{
									bookList.get(i).setRental(true);
									System.out.println("�뿩 �Ϸ�.");
								}
								break;
							}
						}
						
						break;
					case "3":
						System.out.println("���� �޴��� ���ư��ϴ�.");
						return;
					default:
						System.out.println("�޴��� Ȯ���ϰ� ����� �Է��� �ּ���.");
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
		System.out.print("������ �ݳ��մϴ�.\n�ݳ��� ������ ID�� �Է��� �ּ��� : ");
		String temp = ScannerInstance.sc.nextLine();
		
		for(int i = 0; i < bookList.size(); i++)
		{
			if(bookList.get(i).getID().equals(temp))
			{
				if(bookList.get(i).getRental())
				{
					bookList.get(i).setRental(false);
					System.out.println("���� �ݳ� �Ϸ�Ǿ����ϴ�.");
				}
				else
				{
					System.out.println("�ش� ������ �뿩���� �ƴմϴ�.");
				}
			}
		}
	}
	
	public void lossBook()
	{
		System.out.print("���� �н� ��� �մϴ�.\n�нǵ� ������ ID�� �Է��� �ּ��� : ");
		String temp = ScannerInstance.sc.nextLine();
		
		for(int i = 0; i < bookList.size(); i++)
		{
			if(bookList.get(i).getID().equals(temp))
			{
				System.out.println(String.format("���� '%s' �н�ó���� �Ϸ��߽��ϴ�.",bookList.remove(i).getTitle()));
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