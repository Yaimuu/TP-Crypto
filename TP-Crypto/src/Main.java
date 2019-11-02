import java.io.IOException;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Main.java - Classe principale du programme, elle permet de lancer les algorithmes développés pour le projet
 */

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		Server s = new Server("texte.txt");
		for (String string : s. obtenirFichierEncode())
		{
			System.out.println(string);
		}
	}
}
