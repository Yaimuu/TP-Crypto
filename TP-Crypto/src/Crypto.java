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
	
	default char alphabeticOffset(int digit)
	{
		if(digit > Cle.MAX_LOW)
		{
			digit = Cle.MIN_LOW + (digit % Cle.MAX_LOW) - 1;
		}
		else if(digit > Cle.MAX_UP && digit < Cle.MIN_LOW)
		{
			digit = Cle.MIN_UP + (digit % Cle.MAX_UP) - 1;
		}
		
		return (char)digit;
	}
}
