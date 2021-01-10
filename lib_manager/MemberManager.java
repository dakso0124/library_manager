package lib_manager;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import main.ScannerInstance;
import members.MemberInfo;

public class MemberManager
{
	private ArrayList<MemberInfo> m_infoList;
	private String m_lastMemberID;
	public void init()
	{
		m_infoList = new ArrayList<MemberInfo>();
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
						searchMember();
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
				
				m_infoList.add(new MemberInfo(m_lastMemberID, name, phone, Integer.parseInt(age)));
				
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
	
	private void searchMember()
	{
		String temp = null;
		
		while(true)
		{
			System.out.print("ȸ�� ã��޴� �Դϴ�.\n1. ȸ�� ��ü���� ( �̸� �� )\n2. ȸ�� ��ü���� ( ID �� )\n3. �̸����� ã��\n4. ID�� ã��\n5. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":

					break;
				case "2":

					break;
				case "3":

					break;
				case "4":

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
}
