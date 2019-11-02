/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Vigenere.java - Classe implémentant l'algorithme de Vigenère (Algorithme de decaleEnColonne)
 */

public class Vigenere implements Crypto
{
	private String cle;
	
	@Override
	public void setCle(String cle) 
	{
		this.cle = cle;
	}

	@Override
	public void setCle(int cle) 
	{
		// TODO Auto-generated method stub
		
	}

	public Vigenere() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String decode(String message)
	{
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public String encode(String message)
	{
		Crypto.characterVector.clear();
		this.loadMessage(message);
		
		for(int i = 0; i < Crypto.characterVector.size(); i++)
		{
			int digit = Crypto.characterVector.get(i);
			int keyDigit = this.cle.charAt(i % cle.length());
			
			if(digit + keyDigit > Cle.MAX_LOW)
			{
				digit = Cle.MIN_LOW + ((digit + keyDigit) % Cle.MAX_LOW) - 1;
			}
			else if(digit + keyDigit > Cle.MAX_UP && digit < Cle.MIN_LOW)
			{
				digit = Cle.MIN_UP + ((digit + keyDigit) % Cle.MAX_UP) - 1;
			}
			else
			{
				digit += keyDigit;
			}
			
			Crypto.characterVector.set( i, (char)( message.charAt(i) + this.cle.charAt(i % cle.length()) ) );
		}
		
		message = this.vectorToString();
		
		return message;
	}
	
}
