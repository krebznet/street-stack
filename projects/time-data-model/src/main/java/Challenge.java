
public class Challenge {
	/**
	 * main is the starting point for the java program, these lines 
	 * of code they gave me, i had to write the function starting on ine 21
	 * to implement the logic. 
	 */
	public static void main(String[] args) {
		// printing out 3 lines to the system console that calls the function isPalindrome
		// with different words each time, the first 2 words racecar and kayak are palindromes
		// thg third word money is not  
		System.out.println("Is racecar a palindrome? :" + isPalindrome("racecar"));	
		System.out.println("Is kayak a palindrome? :" + isPalindrome("kayak"));	
		System.out.println("Is money a palindrome? :" + isPalindrome("money"));	
	}
	
	

	// function that accepts a string input and returns a true/false (a boolean) 
	// if the it is a palindrome or not. 
	public static boolean isPalindrome(String word) { 
		
		// think how to test if a word is a palindrome? create a loop based on the character count of the word
		// each loop iteration compare the pair two characters from ascending and descending order
		// of the string or word if they are not = then return false in the function 

		
		// the semi colons in the loop on line 33 has 3 expressions seperated by a semi colin 
		// 1. first is create a integer variable called i initialized to = 0 
		// 2. second expression is the loop condition while i is < then  the length of the word
		// 3. the third expression is increment i by 1 each time it loops, short hand for i++ 
		// green lines predixed with // are comments ignored by the compilier
		for(int i = 0; i < word.length(); i++) { // this bracket defines the start of the code in the loop
			// get the ascending character in the loop from start to end
			char ascendingCharacter = word.charAt(i); 
			// get the descending character in the loop from end to start
			char descendingCharacter = word.charAt(word.length() - (i + 1)); 
			// trick here is subtract the length of the word by (i + 1) accounts for 0 index based strings in Java
			// if they do not = each other then return false its not a palindrome! 
			if(ascendingCharacter != descendingCharacter) { 
				return false; 
			}
			
		} // this bracket closes the loop 

		// at this point we made it through the loop and all paired characters were equal
		// it is is a palindrome!!!! 
		return true;
	}
}
