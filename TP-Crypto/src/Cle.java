import java.util.Random;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 3.0
 * Fichier : Cle.java - Classe permettant de générer une clé aléatoirement
 */

public class Cle 
{
	
	public static final int MIN_UP = 65; 	// Correspond au code ascii de 'A'
	public static final int MAX_UP = 90;	// Correspond au code ascii de 'Z'
	public static final int MIN_LOW = 97;	// Correspond au code ascii de 'a'
	public static final int MAX_LOW = 122;	// Correspond au code ascii de 'z'
	
	public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	/*
	 * Permet d'obtenir une clé composée de caractères
	 */
	public static char[] obtenirCleCar(int nbCars)
	{
		if(nbCars == Cle.alphabet.length)
		{
			return obtenirCleAlphabet();
		}
		
		char[] key = new char[nbCars];
		
		for(int i = 0; i < nbCars; i++)
		{
			int random = (int)(Math.random() * Cle.alphabet.length + 1);
			key[i] = alphabet[random-1];
		}
		
		return key;
	}
	
	/*
	 * Permet d'obtenir une clé numérique
	 */
	public static int obtenirCleNum(int max)
	{
		int random = (int)(Math.random() * max + 2);
		return random;
	}
	
	/*
	 * Permet d'obtenir une clé alphabétique (En mélangeant l'alphabet)
	 */
	public static char[] obtenirCleAlphabet()
	{
		Random rgen = new Random();  // Random number generator			
		char[] alphabetRes = Cle.alphabet.clone();
		
		for (int i = 0; i < Cle.alphabet.length; i++) 
		{
		    int randomPosition = rgen.nextInt(alphabetRes.length);
		    int temp = alphabetRes[i];
		    alphabetRes[i] = alphabetRes[randomPosition];
		    alphabetRes[randomPosition] = (char) temp;
		    
		}
		
		return alphabetRes;
	}
	
	/*
	 * Reçoit une clé sous forme de tableau de caractères et retourne un tableau à deux dimensions de caractères.
	 */
	public static char[][] clesMutees(char[] cle){
		char[] tempTable = cle;
		char[][] cleReturn = new char[cle.length*26][cle.length];
		int nbTurn = 0;
		char lastChar = 'A';
		for(int j = 0; j < cle.length; j++) 
		{
			lastChar = tempTable[j];
			for(int k = 0; k < 26; k++) 
			{
				tempTable[j] = (char)(k+'A');
				cleReturn[nbTurn] = tempTable.clone();
				nbTurn++;
			}
			tempTable[j] = lastChar;	
			
		}
		
		return cleReturn;
	}
	
	/*
	 * reçoit une clé sous forme de tableau de caractères et échange deux des caractères de la clé au hasard. 
	 * La clé réarrangée est ensuite retournée.
	 */
	public static char[] cleRearrangee(char[] cle) 
	{
		int random1 = (int)(Math.random() * 100);
		int random2 = (int)(Math.random() * 100);
		char tempChar;
		while(random2%cle.length == random1%cle.length) 
		{
			random2 = (int)(Math.random() * 100);
		}
		tempChar = cle[(random1)%cle.length];
		cle[(random1)%cle.length] =  cle[(random2)%cle.length];
		cle[(random2)%cle.length] =  tempChar;
		return cle;	
	}
	
}
