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
				System.out.println("회원 관리 메뉴입니다. 메뉴를 선택해주세요.\n1. 회원 추가\n2. 회원 검색\n3. 회원정보 수정\n4. 회원탈퇴\n 5. 이전 메뉴로");
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
						System.out.println("메뉴를 확인해주세요.");
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
		System.out.println("회원 추가메뉴 입니다.");
		
		String name = null;
		String phone = null;
		String age = null;
		
		while(true)
		{
			try
			{
				System.out.print("이름을 입력해주세요 : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("전화번호를 입력해주세요 : ");
				phone = ScannerInstance.sc.nextLine();
				
				System.out.print("나이를 입력해주세요 : ");
				age = ScannerInstance.sc.nextLine();
				
				m_memberList.add(new MemberInfo(m_lastMemberID, name, phone, Integer.parseInt(age)));
				
				System.out.println(String.format("회원 %s님이 추가되었습니다. ( ID : %s )", name, m_lastMemberID));
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
			System.out.print("회원 정보 검색 메뉴 입니다.\n1. 회원 전체보기 ( 이름 순 )\n2. 회원 전체보기 ( ID 순 )\n3. 이름으로 찾기\n4. ID로 찾기\n5. 이전 메뉴로");
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
							System.out.print(String.format("검색할 회원의 %s를 입력하세요 : ", (temp.equals("3")? "ID" : "이름")));
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
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
				default:
					System.out.println("메뉴를 확인해주세요.");
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
			System.out.print("회원 정보를 수정합니다. 수정하고자하는 회원의 \n1. 이름으로 찾기ㅣ\n2. ID로 찾기\n3. 이전 메뉴로");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
				case "2":
					
					System.out.print(String.format("검색할 사람의 %s를 입력하세요 : ", (temp.equals("1")? "이름" : "ID") ));
					target =  ScannerInstance.sc.nextLine();
					ArrayList<MemberInfo> tempList = searchMember(target, false);
					
					if(tempList.size() == 0)
					{
						System.out.println("검색된 회원이 없습니다.");
					}
					else if(tempList.size() == 1)
					{
						System.out.println("검색된 회원의 정보를 수정합니다.");
						editMemberInfo(tempList.get(0));
					}
					else
					{
						while(true)
						{
							try
							{
							
								System.out.println(String.format("검색된 회원이 %d명 있습니다.", tempList.size()));
								for(int i = 0 ; i < tempList.size(); i++)
								{
									System.out.print((i+1) + ". ");
									tempList.get(i).showInfo();
								}
								
								System.out.print("수정할 회원의 번호를 입력해 주세요 : ");
								temp = ScannerInstance.sc.nextLine();
								
								if(Integer.parseInt(temp) >= tempList.size())
								{
									System.out.println("숫자를 제대로 입력해 주세요.");
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
								System.out.println("메뉴를 확인하고 숫자를 입력해 주세요.");
								continue;
							}
							
							break;
						}
					}
					
					break;
				case "3":
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
				default:
					System.out.println("메뉴를 확인해주세요.");
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
			System.out.print("회원 탈퇴를 진행합니다. 탈퇴할 회원의 \n1. 이름으로 찾기\n2. ID로 찾기\n3. 이전 메뉴로");
			temp = ScannerInstance.sc.nextLine();
			
			switch(temp)
			{
				case "1":
				case "2":
					
					System.out.print(String.format("검색할 사람의 %s를 입력하세요 : ", (temp.equals("1")? "이름" : "ID") ));
					target =  ScannerInstance.sc.nextLine();
					ArrayList<MemberInfo> tempList = searchMember(target, false);
					
					if(tempList.size() == 0)
					{
						System.out.println("검색된 회원이 없습니다.");
					}
					else if(tempList.size() == 1)
					{
						if(tempList.get(0).getRentalList().size() > 0)
						{
							System.out.println("선택한 회원의 대여도서가 존재합니다.");
						}
						else
						{
							m_memberList.remove(tempList.get(0));
							System.out.println("선택한 회원의 탈퇴를 완료했습니다.");
						}
					}
					else
					{
						while(true)
						{
							try
							{
							
								System.out.println(String.format("검색된 회원이 %d명 있습니다.", tempList.size()));
								for(int i = 0 ; i < tempList.size(); i++)
								{
									System.out.print((i+1) + ". ");
									tempList.get(i).showInfo();
								}
								
								System.out.print("수정할 회원의 번호를 입력해 주세요 : ");
								temp = ScannerInstance.sc.nextLine();
								
								if(Integer.parseInt(temp) >= tempList.size())
								{
									System.out.println("숫자를 제대로 입력해 주세요.");
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
								System.out.println("메뉴를 확인하고 숫자를 입력해 주세요.");
								continue;
							}
							
							break;
						}
					}
					
					break;
				case "3":
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
				default:
					System.out.println("메뉴를 확인해주세요.");
					continue;
			}
			
			break;
		}
	}
	
	private void searchAll(boolean bName)
	{
		ArrayList<MemberInfo> tempList = m_memberList;
		
		// 이름 정렬
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
				System.out.print("회원정보를 수정합니다.\n이름을 입력하세요 : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("전화번호를 입력해주세요 : ");
				phone = ScannerInstance.sc.nextLine();
				
				info.setName(name);
				info.setPhoneNumber(phone);
				
				System.out.println("정보수정이 완료되었습니다.");
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
