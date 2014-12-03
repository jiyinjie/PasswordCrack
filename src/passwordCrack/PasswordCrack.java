package passwordCrack;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PasswordCrack {
	
	private static ArrayList<String> dictionary;
	private static ArrayList<String> mangled_dictionary;
	private static ArrayList<Password> encrypted;
	private static String[] decrypted;
	
	
	public static void main (String[]args){

		encrypted = new ArrayList<Password>();
		mangled_dictionary = new ArrayList<String>();
		
		dictionary = parseInput(args[0]);
		processEncryption(parseInput(args[1]));
		decrypted = new String[encrypted.size()];

		dictionaryAttack();
		
		for(int i = 0; i < decrypted.length; i++)
		{
			System.out.println(decrypted[i]);
		}
	}
	
	public static ArrayList<String> parseInput(String filename){
		ArrayList<String> list = new ArrayList<String>();
		try{
			/* Open the file that is the first command line 
			parameter*/			
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			//Process each instruction
			while (strLine!= null) {
				//DO THE PROCESSING
				list.add(strLine);
				strLine = br.readLine();
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.toString());
		}
		
		return list;
	}
	
	public static void processEncryption(ArrayList<String> list){
		for (int i = 0; i < list.size(); i ++){
			Password processed = new Password(list.get(i));
			encrypted.add(processed);
		}
	}
	
	private static void dictionaryAttack()
	{
		
		//Check passwords against normal dictionary words
		for(int enc_i= 0; enc_i < encrypted.size(); enc_i++)
		{
			Password p = encrypted.get(enc_i);
			String encrypted_password = p.getPasswordEncrypted();
			String salt = p.getSalt();
			
			//Adds username and fullname to dictionary
			dictionary.add(p.getAccount());
			dictionary.add(p.getFullname());
			
			//Check current password aaginst dictionary word
			for(int dict_i = 0; dict_i < dictionary.size(); dict_i++)
			{
				String word = dictionary.get(dict_i);
				String guess = jcrypt.crypt(salt, word);
				
				if(encrypted_password.equals(guess))
				{
					decrypted[enc_i] = word;
					break;
				}
			}
			
			
			//Remove names from dictionary
			//dictionary.remove(dictionary.size()-1);
			//dictionary.remove(dictionary.size()-1);
		}
		
		//Mangle Dictionary
		for(int i = 0; i < dictionary.size(); i++)
		{
			mangleWord(mangled_dictionary, dictionary.get(i));
		}
		
		//Check password against mangled dictionary words
		for(int enc_i= 0; enc_i < encrypted.size(); enc_i++)
		{
			if(decrypted[enc_i] == null)
			{
				Password p = encrypted.get(enc_i);
				String encrypted_password = p.getPasswordEncrypted();
				String salt = p.getSalt();
				
				//Adds username and fullname to dictionary
				dictionary.add(p.getAccount());
				dictionary.add(p.getFullname());
				
				//Check passwords against normal dictionary words
				for(int dict_i = 0; dict_i < mangled_dictionary.size(); dict_i++)
				{
					String word = mangled_dictionary.get(dict_i);
					String guess = jcrypt.crypt(salt, word);
					
					if(encrypted_password.equals(guess))
					{
						decrypted[enc_i] = word;
						break;
					}
				}
				
				
				//Remove names from dictionary
				dictionary.remove(dictionary.size()-1);
				dictionary.remove(dictionary.size()-1);
			}
		}
		
	}
	
	/* dict is the dictionary that the mangled word will be added to */
	private static void mangleWord(ArrayList<String> dict, String word)
	{
		
		/* 1. Treat each word as a bitmap where 0 represents an uncapitalized letter and 1 represents a 
		 *    capitalized letter.
		 * 2. Keep adding one to the bitmap until bitmap is all 1s
		 */
		
		//Prepend character and append character
		for(int character = 32; character < 128; character++)
		{
			String c = ""+(char)character;
			dict.add(word+c);
			dict.add(c+word);
		}
		
		//Delete first character
		dict.add(word.substring(1));
		
		//Delete last character
		dict.add(word.substring(0, word.length()-1));

		//Reverse string
		String reverse = new StringBuilder(word).reverse().toString();
		dict.add(reverse);

		//Duplicate the string
		dict.add(word+word);
		
		//Reflect string
		dict.add(word+reverse);
		dict.add(reverse+word);
		
		
	}
	
	private static void casePermutations(String w){
		char[] word = w.toCharArray();
		int n = (int) Math.pow(2, word.length);
		for (int i = 0; i < n; i++){
			char[] permutation = new char[word.length];
			for(int j = 0; j < word.length; j++){
				if (isBitSet(i, j)){
					permutation[j] = Character.toUpperCase(word[j]);
				} 
				else{
					permutation[j] = word[j];
				}
			}
			mangled_dictionary.add(permutation.toString());
		}
	}
	
	private static boolean isBitSet(int n, int offset){
		return (n >> offset & 1) != 0;
	}
	
//	private static String capitalize(String w, int index)
//	{
//		char[] word = w.toCharArray();
//		word[index]+='A' - 'a';
//		return word.toString();
//	}
//
//	private static String lowercase(String w, int index)
//	{
//		char[] word = w.toCharArray();
//		word[index]+='a' - 'A';
//		return word.toString();
//	}
}
