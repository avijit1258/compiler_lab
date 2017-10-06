package compiler_lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LexicalAnalysis {

	static String keywords[] = {"bool", "char", "int", "long", "float", "double", "void", "return", "if", "else", "while", "do", "for", "goto","break", "switch", "case", "cin", "cout"};
	
	static String operators[] = {"+", "-", "*", "/", "%", "++", "--", "=", "+=", "-=", "*=", "/=", "==", "!=", ">", "<", ">=", "<=", "&&", "||","!", "&", "|", "^", "~", "<<", ">>"};
	
	static String symbols[] = {"(", ")", "{", "}", ";"};
	
	 static String lexems[] ;
	
	static String rawCode = "";
	
	static Set<String> kes = new HashSet<String>();
	static Set<String> ops = new HashSet<String>();
	static Set<String> sym = new HashSet<String>();
	static Set<String> num = new HashSet<String>();
	static Set<String> iden = new HashSet<String>();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File fl = new File("hello.c");
		
		Scanner sc = new Scanner(fl);
		
		while(sc.hasNextLine())
		{
			rawCode = sc.nextLine();
			lexems = rawCode.split("\\s+|\\,|\\; ");
			
			
			for(String st : lexems)
			{
				if(checkSymbol(st))
				{
					sym.add(st);
					
				}else if(checkKeyword(st))
				{
					kes.add(st);
				}else if(checkOperator(st))
				{
					ops.add(st);
				}else if(isNumeric(st))
				{
					num.add(st);
				}else if(isValidJavaIdentifier(st))
				{
					iden.add(st);
				}
			}
		}
		
		//lexems = rawCode.split("\\s+");
		
		System.out.println("------------Identifiers---------\n");
		showAll(iden);
		System.out.println("------------Symbols---------\n");
		showAll(sym);
		System.out.println("------------Keywords---------\n");
		showAll(kes);
		System.out.println("------------Numbers---------\n");
		showAll(num);
		System.out.println("------------Operators---------\n");
		showAll(ops);
		
		

		
		
	}
	
	public static void showAll(Set<String> set)
	{
		//System.out.println("------------------------------------\n");
		System.out.println("|------ID----------Lexems----------|\n");
		//System.out.println("------------------------------------\n");
		int count = 0;
		for(String se : set)
		{
			//String tp = se.toString();
			System.out.format("|------%d----------%s---|\n", count++, se);
		}
		//System.out.println("------------------------------------\n");
	}
	
	public static boolean isNumeric(String str)  
	{  
		  try  
		  {  
		    double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
	}
	
	public static boolean checkKeyword(String str)
	{
		for(String s : keywords)
		{
			if(s.equals(str))
				return true;
		}
		return false;
	}
	
	public static boolean checkOperator(String str)
	{
		for(String s : operators)
		{
			if(s.equals(str))
				return true;
		}
		return false;
	}
	
	public static boolean checkSymbol(String str)
	{
		for(String s : symbols)
		{
			if(s.equals(str))
				return true;
		}
		return false;
	}
	
	
	//This function uses built-in charcater class IsJavaIndentfier Methods
	//
	public static boolean isValidJavaIdentifier(String s) {
	    if (s.isEmpty()) {
	        return false;
	    }
	    if (!Character.isJavaIdentifierStart(s.charAt(0))) {
	        return false;
	    }
	    for (int i = 1; i < s.length(); i++) {
	        if (!Character.isJavaIdentifierPart(s.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}

}
