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
		Iterator<String> dict_i;

		//Mangle first word in dictionary
		dict_i = dictionary.iterator();		
		
		//Check password against normal and mangled-once dictionary words
		while(dict_i.hasNext() && !finished())
			{
				if(!check(dictionary))
				{
					//Create mangled words from single dictionary word and add to mangle dictionary
					mangled_dictionary = new TreeSet<String>();
					mangleWord(mangled_dictionary, dict_i.next());
					
					if(!check(mangled_dictionary))
					{
						//From mangled words, create double mangled words and add to double dictionary
						//Iterator<String> mangled_i = mangled_dictionary.iterator();	
						//double_mangled_dictionary = new TreeSet<String>();
						//while(mangled_i.hasNext())
					//	{
						//	mangleWord(double_mangled_dictionary, mangled_i.next());
						//}
							//Check passwords against double mangled words
						//	check(double_mangled_dictionary);
					}
				}
				System.gc();
			}
	}
	//The check function determines if any word in the given dictionary dict is a password for any user.
	private static boolean check(TreeSet<String> dict)
	{
		Iterator<String> it = dict.iterator();
		while(it.hasNext())
		{
			String word = it.next();
			
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
	
	/* dict is the dictionary that the mangled word will be added to */
	private static void mangleWord(TreeSet<String> dict, String word)
	{
		casePermutations(word.toLowerCase(), dict);
		
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
	
	private static void casePermutations(String w, TreeSet<String> mangled){
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
			mangled.add(new String(permutation));
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
