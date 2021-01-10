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
				System.out.println("회원 관리 메뉴입니다. 메뉴를 선택해주세요.\n1. 회원 추가\n2. 회원 검색\n3. 회원정보 수정\n4. 회원탈퇴\n 5. 이전 메뉴로");
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
		String temp = null;
		
		String name = null;
		String phone = null;
		String age = null;
		
		while(true)
		{
			try
			{
				System.out.print("회원의 이름을 입력해주세요 : ");
				name = ScannerInstance.sc.nextLine();
				
				System.out.print("회원의 전화번호를 입력해주세요 : ");
				phone = ScannerInstance.sc.nextLine();
				
				System.out.print("회원의 나이를 입력해주세요 : ");
				age = ScannerInstance.sc.nextLine();
				
				m_infoList.add(new MemberInfo(m_lastMemberID, name, phone, Integer.parseInt(age)));
				
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
	
	private void searchMember()
	{
		String temp = null;
		
		while(true)
		{
			System.out.print("회원 찾기메뉴 입니다.\n1. 회원 전체보기 ( 이름 순 )\n2. 회원 전체보기 ( ID 순 )\n3. 이름으로 찾기\n4. ID로 찾기\n5. 이전 메뉴로");
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
		
		while(true)
		{
			System.out.print("회원 정보를 수정합니다. 수정하고자하는 회원의 \n1. 이름으로 찾기ㅣ\n2. ID로 찾기\n3. 이전 메뉴로");
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
					System.out.println("메뉴를 확인해주세요.");
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
			System.out.print("회원 탈퇴를 진행합니다. 탈퇴할 회원의 \n1. 이름으로 찾기\n2. ID로 찾기\n3. 이전 메뉴로");
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
					System.out.println("메뉴를 확인해주세요.");
					continue;
			}
			
			break;
		}
	}
}
