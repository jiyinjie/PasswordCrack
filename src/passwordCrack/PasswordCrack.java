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
	private static ArrayList<Password> encrypted;
	private static String[] decrypted;
	private static int found;
	private static long startTime;
	
	public static void main (String[]args){
		
		startTime = System.nanoTime();
	
		encrypted = new ArrayList<Password>();
		dictionary = new TreeSet<String>();
		mangled_dictionary = new TreeSet<String>();
		dictionary.addAll(parseInput(args[0]));
		processEncryption(parseInput(args[1]));
		decrypted = new String[encrypted.size()];
		found = 0;
		
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
		
		int mangles = 0;
		Iterator<String> dict_i;
		while(!finished())
		{
			if(mangles == 0)
			{
				check(dictionary);
				mangled_dictionary = new TreeSet<String>(dictionary); 
			}
			else
			{
				dict_i = dictionary.iterator();	
				while(dict_i.hasNext() && !finished())
				{
					String word = dict_i.next();
					mangleWord(word);		
				}
				
				check(mangled_dictionary);
			}
			dictionary = new TreeSet<String>(mangled_dictionary);
			mangled_dictionary = new TreeSet<String>();
			//System.gc();
			mangles++;
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
					found++;
					decrypted[enc_i] = word;
					long endTime = System.nanoTime();
					long duration = (endTime-startTime)/1000000;
					System.out.println("TIMESTAMP: "+duration+"ms "+p.getAccount()+"'s encrypted password is "+p.getPasswordEncrypted()+", and  the decrypted password is "+word+". ("+found+" out of "+encrypted.size()+" found)");
					return true;
				}
			}
		}
		return false;
	}
	
	/* dict is the dictionary that the mangled word will be added to */
	private static void mangleWord(String word)
	{
		word = word.toLowerCase();
		//if(times > 0)
		{
			casePermutations(word);
			
			//Prepend character and append character
			for(int character = 32; character < 128; character++)
			{
				String c = ""+(char)character;
				mangled_dictionary.add(word+c);
				mangled_dictionary.add(c+word);

			}
			
			//Delete first character
			mangled_dictionary.add(word.substring(1));

			//Delete last character
			mangled_dictionary.add(word.substring(0, word.length()-1));
	
			//Reverse string
			String reverse = new StringBuilder(word).reverse().toString();
			mangled_dictionary.add(reverse);
	
			//Duplicate the string
			mangled_dictionary.add(word+word);
			
			//Reflect string
			mangled_dictionary.add(word+reverse);
			mangled_dictionary.add(reverse+word);
		}
		
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
			checkWord(new String(permutation));
		}
	}
	
	private static boolean isBitSet(int n, int offset){
		return (n >> offset & 1) != 0;
	}
	
}
