package passwordCrack;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PasswordCrack {
	
	private static ArrayList<String> dictionary;
	private static ArrayList<Password> encrypted;
	
	
	public static void main (String[]args){
		 
		dictionary = parseInput(args[0]);
		processEncryption(parseInput(args[1]));
		
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

}
