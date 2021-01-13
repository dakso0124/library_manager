package main;

import java.util.Scanner;

public class ScannerInstance
{
	private static Scanner sc; 

	private ScannerInstance()
	{
		 
    }
 
    public static Scanner getInstance()
    {
    	if(sc == null)
    		init();
    	
        return sc;
    }
    
    private static void init()
    {
    	sc = new Scanner(System.in);
    }
    
    public static void finish()
    {
    	sc.close();
    }
}
