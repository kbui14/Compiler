package project_1;

/**
 * 
 * Simple Regex recognizer:
 * Write a simple regex recognizer in Java that recognizes single characters, single characters with +, *, ?, and concatenation of the above. 
 * It should be able to recognize for a, b in {character that are not ‘+’, ‘*’, ‘?’ or ‘\0}
 * 
 */



import java.util.Scanner;

public class Bui {

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.print("Inpute regex to match: ");
		String regex = sc.next();
		while(true) {
			System.out.print("Input a string: ");
			String string = sc.next();
			if(simpleMatch(regex,0,string,0))
				System.out.println("YES " + string + " MATCHES " + regex);
			else
				System.out.println("NO " + string + " does not match " + regex);
		} // end while loop
	} // end main

	static char peek(String string, int pos) {
		//TODO: write code that returns '\0' if we have reached the end of the string or beyond
		//		else return the character at pos in the string

		if (pos >= string.length())
			return '\0';
		else
			return string.charAt(pos);
	} // end peek

	public static boolean simpleMatch(String regex, int regexPos, String string, int stringPos){

		if(peek(regex,regexPos)=='\0'){
			return (peek(string,stringPos) == '\0');
		}
		else if (peek(regex, regexPos+1) == '?'){
			return (peek(regex, regexPos) == peek(string, stringPos) && simpleMatch(regex,regexPos+2,string,stringPos+1) || simpleMatch(regex, regexPos+2, string, stringPos));
		}
		else if (peek(regex, regexPos+1) == '*'){
			int i = 0;
			while(peek(regex,regexPos) == peek(string,stringPos+i)){
				i++;
			}
			return simpleMatch(regex, regexPos+2, string, stringPos+i);
		}
		else if (peek(regex, regexPos+1) == '+'){
			//check if next regex character is +
			if(peek(string,stringPos) != peek(regex,regexPos)){
				return false;
			}
			else{
				int i = 0;
				while(peek(regex,regexPos) == peek(string,stringPos+i)){
					i++;
				}
				return simpleMatch(regex, regexPos+2, string, stringPos+i);
			}
		}
		else{ 		
			return peek(regex,regexPos) == peek(string,stringPos) && simpleMatch(regex, regexPos+1, string, stringPos+1);
		}


	} // end simpleMatch
} // end Class

