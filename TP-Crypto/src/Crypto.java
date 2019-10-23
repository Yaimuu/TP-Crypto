import java.util.Vector;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Crypto.java - Interface implémentant les méthodes decode et encode
 */

public interface Crypto 
{
	Vector<Character> characterVector = new Vector<Character>();
	
	public String decode(String message);
	public String encode(String message);
}
