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
	
	static String single_operators[] = {"+", "-", "*", "/", "%", "=", ">", "<","!", "&", "|", "^", "~", ";"};
	
	static String symbols[] = {",","(", ")", "{", "}", ";"};
	
	 static String lexems[] ;
	
	static String rawCode = "";
	
	
	static ArrayList<String> symTable = new ArrayList<String>();
	//ArrayList<char> ch = new ArrayList<char>();
	
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
			//lexems = rawCode.split("\\s+|\\,|\\; ");
			
			if(rawCode.indexOf("//") != -1)
			{
				
				rawCode = rawCode.substring(0, rawCode.indexOf("//"));
			}
			lexems = split(rawCode);	
			
			
			for(String st : lexems)
			{
				
				if(isBlank(st))
					continue;
				
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
					//System.out.println(st );
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
		
		//split("{");
		
		//lexems = rawCode.split("\\s+");
		
		//System.out.println("------------Identifiers---------\n");
		//showAll(iden);
		//System.out.println("------------Symbols---------\n");
		//showAll(sym);
		//System.out.println("------------Keywords---------\n");
		//showAll(kes);
		System.out.println("------------Numbers---------\n");
		showAll(num);
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
		  
		  //return str.matches("-?\\d+(\\.\\d+)?");
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
				else if(s.equals(">"))
					System.out.println(str+ "\t\toperator\t\tgreater than");
				else if(s.equals("<"))
					System.out.println(str+ "\t\toperator\t\tless than");
				else if(s.equals("&&"))
					System.out.println(str+ "\t\toperator\t\tAND");
				else if(s.equals("||"))
					System.out.println(str+ "\t\toperator\t\tOR");
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
				else if(s.equals(","))
					System.out.println(str+ "\t\tspecial symbol\t\tcomma");
				else if(s.equals(";"))
					System.out.println(str+ "\t\tspecial symbol\t\tsemi-colon");
				
				
				return true;
			}
				
		}
		return false;
	}
	
	public static boolean is_double_operator(String s)
	{
		//System.out.println(s+ "from is double");
		if(s.equals("==") || s.equals("+=") ||s.equals("-=") ||s.equals("*=") ||s.equals("/=") ||s.equals("++") ||s.equals("--"))
		{
			
			return true;
		}
			
		
		return false;
	}
	
	public static boolean is_single_operator(char c)
	{
		String st = String.valueOf(c);
		for(int i = 0 ; i < single_operators.length ; i++)
			if(single_operators[i].equals(st))
				return true;
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
	
	    public static String[] split(String st){
			
			//String st = "float m =(float)(y2-y1)/x2-x1 ";
	    	st = "if(sum>b)";
			
		    ArrayList<Character> str=new ArrayList<Character>();
		    String myStrings[] = new String[30];
		    char ch, last_char = ',';
		    int pointer=0;
			for(int i=0; i < st.length() ; i++){
				
				ch=st.charAt(i);
				//System.out.println(ch + " " +is_separator(ch) );
				if(!is_separator(ch)){
					str.add(ch);
					
				}
				else{
					
					if(!str.isEmpty()) {
						myStrings[++pointer]=getStringRepresentation(str);
						str.clear();
					}
						if( (i + 1) < st.length() )
						{
							String a = String.valueOf(ch);
							a = a.concat(String.valueOf(st.charAt(i+1)));
							
							
							if(is_double_operator(a))
							{
								//System.out.println("From double"+i);
								myStrings[++pointer]=a;
								i = i + 1;
							}else if(is_single_operator(ch))
							{
								//System.out.println("From single"+i);
								myStrings[++pointer]=String.valueOf(ch);
							}
							else
							{
								//System.out.println("From else"+ch+i);
								myStrings[++pointer]=String.valueOf(ch);
									
								//pointer++;
							}
						}
						
						
					
				}
				
				if(st.length() - 1 == i)
				last_char = ch;
				
				
			}
			if(!str.isEmpty()) {
				myStrings[++pointer]=getStringRepresentation(str);
				str.clear();
			}else
				myStrings[++pointer]=String.valueOf(last_char);
	
				
				
			
			for(int i = 0 ; i <= pointer ; i++)
			{
				System.out.println(i+myStrings[i]);
			}
			
			return myStrings;
		}
		
		public static String getStringRepresentation(ArrayList<Character> list)
		{    
		    StringBuilder builder = new StringBuilder(list.size());
		    for(Character ch: list)
		    {
		        builder.append(ch);
		    }
		    return builder.toString();
		}
		
		public static Boolean is_separator(char ch){
			if(ch==','||ch==';'||ch=='/'||ch=='+'||ch=='-'||ch=='*' ){
				 return true;
			}
			
			else if(ch=='('||ch==')'||ch=='{'||ch=='}'||ch=='['){
				
				 return true;
			}
			else if(ch==']'||ch=='#'||ch=='\\'||ch=='"'||ch=='<'||ch=='>' || ch == '='){
				
				 return true;
			}else if(Character.isWhitespace(ch))
				return true;
			
			     return false;
			
		}
		
		public static boolean isBlank(String string) {
	        if (string == null || string.length() == 0)
	            return true;

	        int l = string.length();
	        for (int i = 0; i < l; i++) {
	            if (! Character.isWhitespace(string.charAt(i)) )
	                return false;
	        }
	        return true;
	    } 
	
		
	
}	
