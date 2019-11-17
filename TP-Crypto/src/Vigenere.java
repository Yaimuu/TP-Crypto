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
		Crypto.characterVector.clear();
		this.loadMessage(message);
		
		for(int i = 0; i < Crypto.characterVector.size(); i++)
		{
			int digit = 0;
			int keyDigit = this.cle.charAt(i % (this.cle.length())) - Cle.MIN_UP;
			int newDigit = 0;
			
			if(message.charAt(i) >= Cle.MIN_LOW) 
			{
				digit = message.charAt(i) - Cle.MIN_LOW;
				newDigit = (digit - keyDigit + 26) % 26 + Cle.MIN_LOW;
			}
			else 
			{
				digit = message.charAt(i) - Cle.MIN_UP;
				newDigit = (digit - keyDigit + 26) % 26 + Cle.MIN_UP;
			}
			
			Crypto.characterVector.set( i, (char)( newDigit ));
		}
		
		message = this.vectorToString();
		
		return message;
	}

	@Override
	public String encode(String message)
	{
		Crypto.characterVector.clear();
		this.loadMessage(message);
		
		for(int i = 0; i < Crypto.characterVector.size(); i++)
		{
			int digit = 0;
			int keyDigit = this.cle.charAt(i % (this.cle.length())) - Cle.MIN_UP;
			int newDigit = 0;
			
			if(message.charAt(i) >= Cle.MIN_LOW) 
			{
				digit = message.charAt(i) - Cle.MIN_LOW;
				newDigit = (digit + keyDigit) % 26 + Cle.MIN_LOW;
			}
			else 
			{
				digit = message.charAt(i) - Cle.MIN_UP;
				newDigit = (digit + keyDigit) % 26 + Cle.MIN_UP;
			}
			
			Crypto.characterVector.set( i, (char)( newDigit ));
		}
		
		message = this.vectorToString();
		
		return message;
	}
	
	@Override
	public String obtenirNomAlgo() 
	{
		return "Vigenere";
		//return "Chiffre de Vigenère";
		
	}
}
