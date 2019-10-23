/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Cle.java - Classe permettant de générer une clé aléatoirement
 */

public class Cle 
{
	public static char[] alphabet = "ABCDEFGHIJKLMOPQRSTUVWXYZ".toCharArray();
	
	private static char[] obtenirCleCar(int nbCars)
	{
		char[] key = new char[nbCars];
		
		for(int i = 0; i < nbCars; i++)
		{
			int random = (int)(Math.random() * alphabet.length + 1);
			key[i] = alphabet[random-1];
		}
		
		return key;
	}
	
	private static int obtenirCleNum(int max)
	{
		int random = (int)(Math.random() * max + 1);
		return random;
	}
	
}
