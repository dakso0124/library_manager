package lib_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

import books.Book;
import main.ScannerInstance;
import members.MemberInfo;

public class MemberManager
{
	private ArrayList<MemberInfo> m_memberList;
	private String m_lastMemberID;
	
	public void init()
	{
		m_memberList = new ArrayList<MemberInfo>();
		m_lastMemberID = null;
	}
	
	public void showMenu()
	{
		String temp = null;
		while(true)
		{
			try
			{
				System.out.println("ȸ�� ���� �޴��Դϴ�. �޴��� �������ּ���.\n1. ȸ�� �߰�\n2. ȸ�� �˻�\n3. ȸ������ ����\n4. ȸ��Ż��\n 5. ���� �޴���");
				temp = ScannerInstance.sc.nextLine();
				
				switch(temp)
				{
					case "1":
						addMember();
						break;
					case "2":
						searchMembers();
						break;
					case "3":
						editMemberInfo();
						break;
					case "4":
						removeMember();
						break;
					case "5":
						return;
					default:
						System.out.println("�޴��� Ȯ�����ּ���.");
						continue;
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
	
	private void addMember()
	{
		String temp = null;
		
		String name = null;
		String phone = null;
		String age = null;
		
		while(true)
		{
			try
			{
				System.out.print("ȸ���� �̸��� �Է����ּ��� : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("ȸ���� ��ȭ��ȣ�� �Է����ּ��� : ");
				phone = ScannerInstance.sc.nextLine();
				
				System.out.print("ȸ���� ���̸� �Է����ּ��� : ");
				age = ScannerInstance.sc.nextLine();
				
				m_memberList.add(new MemberInfo(m_lastMemberID, name, phone, Integer.parseInt(age)));
				
				System.out.println(String.format("ȸ�� %s���� �߰��Ǿ����ϴ�. ( ID : %s )", name, m_lastMemberID));
			}
			catch(NoSuchElementException e)
			{
				
			}
			catch(IllegalStateException e)
			{
				
			}
			
			break;
		}
	}
	
	private void searchMembers()
	{
		String temp = null;
		String target = null;
		
		while(true)
		{
			System.out.print("ȸ�� ã��޴� �Դϴ�.\n1. ȸ�� ��ü���� ( �̸� �� )\n2. ȸ�� ��ü���� ( ID �� )\n3. �̸����� ã��\n4. ID�� ã��\n5. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
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
					
					while(true)
					{
						try 
						{
							System.out.print(String.format("�˻��� ȸ���� %s�� �Է��ϼ��� : ", (temp.equals("3")? "ID" : "�̸�")));
							target = ScannerInstance.sc.nextLine();
							
							 searchMember(target, false);
						}
						catch(NoSuchElementException e)
						{
							
						}
						catch(IllegalStateException e)
						{
							
						}						
						
						break;
					}
					
					break;
				case "5":
					System.out.println("���� �޴��� ���ư��ϴ�.");
					return;
				default:
					System.out.println("�޴��� Ȯ�����ּ���.");
					continue;
			}
			
			break;
		}
	}
	
	private void editMemberInfo()
	{
		String temp = null;
		
		while(true)
		{
			System.out.print("ȸ�� ������ �����մϴ�. �����ϰ����ϴ� ȸ���� \n1. �̸����� ã���\n2. ID�� ã��\n3. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
					
					break;
				case "2":
					
					break;
				case "3":
					
					return;
				default:
					System.out.println("�޴��� Ȯ�����ּ���.");
					continue;
			}
			
			break;
		}
	}
	
	private void removeMember()
	{
		String temp = null;
		
		while(true)
		{
			System.out.print("ȸ�� Ż�� �����մϴ�. Ż���� ȸ���� \n1. �̸����� ã��\n2. ID�� ã��\n3. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
					searchMember(true);
					break;
				case "2":
					searchMember(false);
					break;
				case "3":
					System.out.println("���� �޴��� ���ư��ϴ�.");
					return;
				default:
					System.out.println("�޴��� Ȯ�����ּ���.");
					continue;
			}
			
			break;
		}
	}
	
	private void searchAll(boolean bName)
	{
		ArrayList<MemberInfo> tempList = m_memberList;
		
		// �̸� ����
		if(bName)
		{
			Collections.sort(tempList, new Comparator() {
				@Override
				public int compare(Object b1, Object b2) {
					return ((MemberInfo)b1).getName().compareTo(((MemberInfo)b2).getName());
				}
			});
		}
		else
		{
			Collections.sort(tempList, new Comparator() {
				@Override
				public int compare(Object b1, Object b2) {
					return ((MemberInfo)b1).getID().compareTo(((MemberInfo)b2).getID());
				}
			});
		}
		
		for(int i = 0 ; i < tempList.size(); i++)
		{
			tempList.get(i).showInfo();
		}
	}
	
	private void searchMember(String target, boolean bName)
	{
		
	}
	
	private void selectMember()
	{
		
	}
}
