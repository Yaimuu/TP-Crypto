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
		char[] array = Cle.alphabet;
		
		for (int i = 0; i < array.length; i++) 
		{
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = (char) temp;
		}
 
		return array;
	}
	
}
