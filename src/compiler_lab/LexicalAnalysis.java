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
	
	
	static ArrayList<String> symTable = new ArrayList<String>();
	
	static Set<String> kes = new HashSet<String>();
	static Set<String> ops = new HashSet<String>();
	static Set<String> sym = new HashSet<String>();
	static Set<String> num = new HashSet<String>();
	//static Set<String> iden = new HashSet<String>();
	//ArrayList<String> iden = new ArrayList<String>();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File fl = new File("hello.c");
		
		Scanner sc = new Scanner(fl);
		
		System.out.println("Lexems\t Token name\t Attribute value");
		int pointer = 0;
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
					if(!symTable.contains(st))
					{
						//iden.add(st);
						symTable.add(st);
					}
					
					System.out.println(st+ "\t\tid\t\t"+ pointer_table_entry( st));
				}
				
			}
			//shk8owAll(iden);
		}
		System.out.println("Symbol Table");
		//showAll(iden);
		for(int i = 0 ; i < symTable.size() ; i++)
		{
			System.out.format("|------%s-----------ID----%d---|\n",symTable.get(i),  i);
		}
		
		//lexems = rawCode.split("\\s+");
		
		//System.out.println("------------Identifiers---------\n");
		//showAll(iden);
		//System.out.println("------------Symbols---------\n");
		//showAll(sym);
		//System.out.println("------------Keywords---------\n");
		//showAll(kes);
		//System.out.println("------------Numbers---------\n");
		//showAll(num);
		//System.out.println("------------Operators---------\n");
		//showAll(ops);
		
		
		
		
	}
	
	public static void showAll(Set<String> set)
	{
		System.out.println("------------------------------------\n");
		System.out.println("|-----Symbol---------Token---------Pointer-|\n");
		System.out.println("------------------------------------\n");
		int count = 0;
		for(String se : set)
		{
			String tp = se.toString();
			System.out.format("|------%s-----------ID----%d---|\n",se,  count++);
		}
		//System.out.println("------------------------------------\n");
	}
	
	public static int pointer_table_entry( String str)
	{
		
		
		/*
		for(String se : iden)
		{
			//System.out.println(se);
			
			
			if(se.equals(str))
			{
				//System.out.println(se);
				return pointer;
			}
			pointer++;
			
		}
		*/
		
		
		int j = symTable.size();
		for(int i = 0; i < j ; i++)
		{
			if(symTable.get(i).equals(str) )
				return i;
		}
		
		return -1;
		
	}
	
	public static boolean isNumeric(String str)  
	{  
		  try  
		  {  
		    double d = Double.parseDouble(str);
		    System.out.println(str+ "\t\tnumber\t\tconstant");
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
			{
				System.out.println(str+"\t\t"+str+"\t\t-");
				return true;
			}
				
		}
		return false;
	}
	
	public static boolean checkOperator(String str)
	{
		for(String s : operators)
		{
			if(s.equals(str))
			{
				if(s.equals("="))
					System.out.println(str+ "\t\toperator\t\tassignment");
				else if(s.equals("+"))
					System.out.println(str+ "\t\toperator\t\taddition");
				else if(s.equals("-"))
					System.out.println(str+ "\t\toperator\t\tsubtraction");
				else if(s.equals("*"))
					System.out.println(str+ "\t\toperator\t\tmultiplication");
				else if(s.equals("/"))
					System.out.println(str+ "\t\toperator\t\tdivision");
				else if(s.equals("=="))
					System.out.println(str+ "\t\toperator\t\tequal checking");
				else if(s.equals("++"))
					System.out.println(str+ "\t\toperator\t\tincrement");
				else if(s.equals("--"))
					System.out.println(str+ "\t\toperator\t\tdecrement");
				else if(s.equals("+="))
					System.out.println(str+ "\t\toperator\t\tincrement and assign");
				else if(s.equals("-="))
					System.out.println(str+ "\t\toperator\t\tdecrement and assign");
				else if(s.equals("*="))
					System.out.println(str+ "\t\toperator\t\tmultiply and assign");
				else if(s.equals("/="))
					System.out.println(str+ "\t\toperator\t\tdivision and assign");
				return true;
			}
				
		}
		return false;
	}
	
	public static boolean checkSymbol(String str)
	{
		for(String s : symbols)
		{
			if(s.equals(str))
			{
				if(s.equals("("))
					System.out.println(str+ "\t\tspecial symbol\t\topening braces");
				else if(s.equals(")"))
					System.out.println(str+ "\t\tspecial symbol\t\tclosing braces");
				else if(s.equals("{")) 
					System.out.println(str+ "\t\tspecial symbol\t\tleft curly braces");
				else  if(s.equals("}"))
					System.out.println(str+ "\t\tspecial symbol\t\tright curly braces");
				
				
				
				return true;
			}
				
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
