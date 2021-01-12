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
		System.out.println("ȸ�� �߰��޴� �Դϴ�.");
		
		String name = null;
		String phone = null;
		String age = null;
		
		while(true)
		{
			try
			{
				System.out.print("�̸��� �Է����ּ��� : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("��ȭ��ȣ�� �Է����ּ��� : ");
				phone = ScannerInstance.sc.nextLine();
				
				System.out.print("���̸� �Է����ּ��� : ");
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
			System.out.print("ȸ�� ���� �˻� �޴� �Դϴ�.\n1. ȸ�� ��ü���� ( �̸� �� )\n2. ȸ�� ��ü���� ( ID �� )\n3. �̸����� ã��\n4. ID�� ã��\n5. ���� �޴���");
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
							
							ArrayList<MemberInfo> infos = searchMember(target, false);
							
							for(int i = 0 ; i < infos.size(); i++)
								infos.get(i).showInfo();
							System.out.println("--------------------------------");
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
		String target = null;
		while(true)
		{
			System.out.print("ȸ�� ������ �����մϴ�. �����ϰ����ϴ� ȸ���� \n1. �̸����� ã���\n2. ID�� ã��\n3. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
				case "2":
					
					System.out.print(String.format("�˻��� ����� %s�� �Է��ϼ��� : ", (temp.equals("1")? "�̸�" : "ID") ));
					target =  ScannerInstance.sc.nextLine();
					ArrayList<MemberInfo> tempList = searchMember(target, false);
					
					if(tempList.size() == 0)
					{
						System.out.println("�˻��� ȸ���� �����ϴ�.");
					}
					else if(tempList.size() == 1)
					{
						System.out.println("�˻��� ȸ���� ������ �����մϴ�.");
						editMemberInfo(tempList.get(0));
					}
					else
					{
						while(true)
						{
							try
							{
							
								System.out.println(String.format("�˻��� ȸ���� %d�� �ֽ��ϴ�.", tempList.size()));
								for(int i = 0 ; i < tempList.size(); i++)
								{
									System.out.print((i+1) + ". ");
									tempList.get(i).showInfo();
								}
								
								System.out.print("������ ȸ���� ��ȣ�� �Է��� �ּ��� : ");
								temp = ScannerInstance.sc.nextLine();
								
								if(Integer.parseInt(temp) >= tempList.size())
								{
									System.out.println("���ڸ� ����� �Է��� �ּ���.");
									continue;
								}
								else
								{
									editMemberInfo(tempList.get(Integer.parseInt(temp)-1));
								}
							}
							catch(NoSuchElementException e)
							{
								
							}
							catch(IllegalStateException e)
							{
								
							}
							catch(NumberFormatException e)
							{
								System.out.println("�޴��� Ȯ���ϰ� ���ڸ� �Է��� �ּ���.");
								continue;
							}
							
							break;
						}
					}
					
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
	
	private void removeMember()
	{
		String temp = null;
		String target = null;
		
		while(true)
		{
			System.out.print("ȸ�� Ż�� �����մϴ�. Ż���� ȸ���� \n1. �̸����� ã��\n2. ID�� ã��\n3. ���� �޴���");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
				case "2":
					
					System.out.print(String.format("�˻��� ����� %s�� �Է��ϼ��� : ", (temp.equals("1")? "�̸�" : "ID") ));
					target =  ScannerInstance.sc.nextLine();
					ArrayList<MemberInfo> tempList = searchMember(target, false);
					
					if(tempList.size() == 0)
					{
						System.out.println("�˻��� ȸ���� �����ϴ�.");
					}
					else if(tempList.size() == 1)
					{
						if(tempList.get(0).getRentalList().size() > 0)
						{
							System.out.println("������ ȸ���� �뿩������ �����մϴ�.");
						}
						else
						{
							m_memberList.remove(tempList.get(0));
							System.out.println("������ ȸ���� Ż�� �Ϸ��߽��ϴ�.");
						}
					}
					else
					{
						while(true)
						{
							try
							{
							
								System.out.println(String.format("�˻��� ȸ���� %d�� �ֽ��ϴ�.", tempList.size()));
								for(int i = 0 ; i < tempList.size(); i++)
								{
									System.out.print((i+1) + ". ");
									tempList.get(i).showInfo();
								}
								
								System.out.print("������ ȸ���� ��ȣ�� �Է��� �ּ��� : ");
								temp = ScannerInstance.sc.nextLine();
								
								if(Integer.parseInt(temp) >= tempList.size())
								{
									System.out.println("���ڸ� ����� �Է��� �ּ���.");
									continue;
								}
								else
								{
									editMemberInfo(tempList.get(Integer.parseInt(temp)-1));
								}
							}
							catch(NoSuchElementException e)
							{
								
							}
							catch(IllegalStateException e)
							{
								
							}
							catch(NumberFormatException e)
							{
								System.out.println("�޴��� Ȯ���ϰ� ���ڸ� �Է��� �ּ���.");
								continue;
							}
							
							break;
						}
					}
					
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
	
	private ArrayList<MemberInfo> searchMember(String target, boolean bName)
	{
		ArrayList<MemberInfo> result = new ArrayList<MemberInfo>();
		
		for(int i = 0 ; i < m_memberList.size(); i++)
		{
			if(bName)
			{
				if(m_memberList.get(i).getName().equals(target))
				{
					result.add(m_memberList.get(i));
				}
			}
			else
			{
				if(m_memberList.get(i).getID().equals(target))
				{
					result.add(m_memberList.get(i));
					break;
				}
			}
		}
		
		return result;
	}
	
	private void editMemberInfo(MemberInfo info)
	{
		String name = null;
		String phone = null;
		while(true)
		{
			try
			{
				System.out.print("ȸ�������� �����մϴ�.\n�̸��� �Է��ϼ��� : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("��ȭ��ȣ�� �Է����ּ��� : ");
				phone = ScannerInstance.sc.nextLine();
				
				info.setName(name);
				info.setPhoneNumber(phone);
				
				System.out.println("���������� �Ϸ�Ǿ����ϴ�.");
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
}
