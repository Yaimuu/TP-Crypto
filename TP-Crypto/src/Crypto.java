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
	public void setCle(int cle);
	public void setCle(String cle);
	public String obtenirNomAlgo();
	
	default void loadMessage(String message)
	{
		for(int i = 0; i < message.length(); i++)
		{
			characterVector.add(message.charAt(i));
		}
	}
	
	default String vectorToString()
	{
		String message = "";
		for(int i = 0; i < characterVector.size(); i++)
		{
			message += characterVector.get(i);
		}
		return message;
	}
}
