import java.util.Random;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Cle.java - Classe permettant de g�n�rer une cl� al�atoirement
 */

public class Cle 
{
	public static final int MIN_UP = 65;
	public static final int MAX_UP = 90;
	public static final int MIN_LOW = 97;
	public static final int MAX_LOW = 122;
	
	
	
	public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static char[] obtenirCleCar(int nbCars)
	{
		char[] key = new char[nbCars];
		
		for(int i = 0; i < nbCars; i++)
		{
			int random = (int)(Math.random() * alphabet.length + 1);
			key[i] = alphabet[random-1];
		}
		
		return key;
	}
	
	public static int obtenirCleNum(int max)
	{
		int random = (int)(Math.random() * max + 2);
		return random;
	}
	
	public static char[] obtenirCleAlphabet()
	{
		Random rgen = new Random();  // Random number generator			
		char[] alphabetRes = Cle.alphabet;
		
		for (int i = 0; i < alphabetRes.length; i++) 
		{
		    int randomPosition = rgen.nextInt(alphabetRes.length);
		    int temp = alphabetRes[i];
		    
		    alphabetRes[randomPosition] = (char) temp;
		    alphabetRes[i] = alphabetRes[randomPosition];
		}
		
		return alphabetRes;
	}
	
	public static char[][] clesMutees(char[] cle){
		char[] tempTable = cle;
		char[][] cleReturn = new char[cle.length*26][cle.length];
		int nbTurn = 0;
		char lastChar = 'A';
		for(int j = 0; j < cle.length; j++) {
			lastChar = tempTable[j];
			for(int k = 0; k < 26; k++) {
				tempTable[j] = (char)(k+'A');
				//System.out.println(tempTable[counter]);
				cleReturn[nbTurn] = tempTable;
				//System.out.println(cleReturn[nbTurn]);
				nbTurn++;
				//System.out.println(tempTable);
			}
			tempTable[j] = lastChar;
		}
		return cleReturn;
	}
	
	public static char[] cleRearrangee(char[] cle) {
		int random1 = (int)(Math.random() * 100);
		int random2 = (int)(Math.random() * 100);
		char tempChar;
		while(random2%cle.length == random1%cle.length) {
			random2 = (int)(Math.random() * 100);
		}
		tempChar = cle[(random1)%cle.length];
		cle[(random1)%cle.length] =  cle[(random2)%cle.length];
		cle[(random2)%cle.length] =  tempChar;
		return cle;	
	}
	
}
