package main;

import lib_manager.LibraryManager;

public class lib_main
{
	public static void main(String args[])
	{
		System.out.println("도서관리 프로그램을 시작합니다.");
		
		LibraryManager lm = new LibraryManager();
		
		lm.init();
		lm.startManager();
	}
}
