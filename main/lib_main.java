package main;

import lib_manager.LibraryManager;

public class lib_main
{
	public static void main(String args[])
	{
		System.out.println("�������� ���α׷��� �����մϴ�.");
		
		LibraryManager lm = new LibraryManager();
		
		lm.init();
		lm.startManager();
	}
}
