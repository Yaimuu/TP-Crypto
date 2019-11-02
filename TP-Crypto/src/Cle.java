/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Cle.java - Classe permettant de générer une clé aléatoirement
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
	
}
