package passwordCrack;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
public class PasswordCrack {
	
	private static TreeSet<String> dictionary;
	private static TreeSet<String> mangled_dictionary;
	private static TreeSet<String> double_mangled_dictionary;
	private static ArrayList<Password> encrypted;
	private static String[] decrypted;
	
	
	public static void main (String[]args){

		encrypted = new ArrayList<Password>();
		dictionary = new TreeSet<String>();
		
		dictionary.addAll(parseInput(args[0]));
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
		//Add names to dictionary
		for(int i = 0; i < encrypted.size(); i++)
		{
			dictionary.add(encrypted.get(i).getFirstName());
			dictionary.add(encrypted.get(i).getLastName());
			dictionary.add(encrypted.get(i).getFullName());
			dictionary.add(encrypted.get(i).getAccount());
		}
		
		Iterator<String> dict_i;
		
		//Check words in dictionary
		dict_i = dictionary.iterator();		
		check(dictionary);
		
		//Check password against normal and mangled-once dictionary words
		while(dict_i.hasNext() && !finished())
			{
				String word = dict_i.next();
				mangleWord(word, 2);		
			}
	}
	//The check function determines if any word in the given dictionary dict is a password for any user.
	private static boolean check(TreeSet<String> dict)
	{
		Iterator<String> it = dict.iterator();
		while(it.hasNext())
		{
			String word = it.next();
			checkWord(word);
		}
		return false;
	}
	
	//Returns true if all passwords are cracked. Otherwise, false.
	private static boolean finished()
	{
		for(int i = 0; i < decrypted.length; i++)
		{
			if(decrypted[i] == null)
				return false;
		}
		return true;
	}
	
	private static boolean checkWord(String word)
	{
		for(int enc_i= 0; enc_i < encrypted.size(); enc_i++)
		{
			if(decrypted[enc_i] == null)
			{
				Password p = encrypted.get(enc_i);
				String encrypted_password = p.getPasswordEncrypted();
				String salt = p.getSalt();
				String guess = jcrypt.crypt(salt, word);
				
				if(encrypted_password.equals(guess))
				{
					decrypted[enc_i] = word;
					return true;
				}
			}
		}
		return false;
	}
	
	/* dict is the dictionary that the mangled word will be added to */
	private static void mangleWord(String word, int times)
	{
		if(times > 0)
		{
			casePermutations(word.toLowerCase(), times);
			
			//Prepend character and append character
			for(int character = 32; character < 128; character++)
			{
				String c = ""+(char)character;
				checkWord(word+c);
				mangleWord(word+c, times-1);
				checkWord(c+word);
				mangleWord(c+word, times-1);
			}
			
			//Delete first character
			checkWord(word.substring(1));
			mangleWord(word.substring(1), times-1);
			
			//Delete last character
			checkWord(word.substring(0, word.length()-1));
			mangleWord(word.substring(0, word.length()-1), times-1);
	
			//Reverse string
			String reverse = new StringBuilder(word).reverse().toString();
			checkWord(reverse);
			mangleWord(reverse, times-1);
	
			//Duplicate the string
			checkWord(word+word);
			mangleWord(word+word, times-1);
			
			//Reflect string
			checkWord(word+reverse);
			mangleWord(word+reverse, times-1);
			checkWord(reverse+word);
			mangleWord(reverse+word, times-1);
		}
		
	}
	
	private static void casePermutations(String w, int times){
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
			checkWord(new String(permutation));
			mangleWord(new String(permutation), times-1);
		}
	}
	
	private static boolean isBitSet(int n, int offset){
		return (n >> offset & 1) != 0;
	}
	
}
