package main;

import java.util.Scanner;

public class ScannerInstance
{
	public static Scanner sc; 
	 //private construct
	private ScannerInstance()
	{
		 
    }
 
    private static class LazyHolder
    {
        public static final ScannerInstance INSTANCE = new ScannerInstance();
    }
 
    public static ScannerInstance getInstance()
    {
        return LazyHolder.INSTANCE;
    }
    
    public static void init()
    {
    	sc = new Scanner(System.in);
    }
    
    public static void finish()
    {
    	sc.close();
    }
}
