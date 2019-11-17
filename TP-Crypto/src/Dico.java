/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 2.0
 * Fichier : Dico.java - Classe implémentant l'algorithme du dictionnaire
 */

public class Dico implements Crypto
{
	private String cle;
	
	@Override
	public void setCle(String cle) 
	{
		this.cle = cle;
	}
	
	@Override
	public void setCle(int cle) { /* Ne fait rien*/ }

	public Dico(String cle) 
	{
		super();
		this.setCle(cle);
	}

	@Override
	public String decode(String message) 
	{
		Crypto.characterVector.clear();
		this.loadMessage(message);
		
		String copy = message;
		for(int i = 0; i < Crypto.characterVector.size(); i++)
		{
			
			int index = this.cle.indexOf((copy.toUpperCase().charAt(i)));
			
			char newChar = Cle.alphabet[index];
			
			if(message.charAt(i) >= Cle.MIN_LOW) 
			{
				newChar += Cle.MIN_LOW - Cle.MIN_UP;
			}
			
			Crypto.characterVector.set( i, newChar);
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
			int digit = message.charAt(i);
			if(message.charAt(i) >= Cle.MIN_LOW) 
			{
				digit -= Cle.MIN_LOW;
			}
			else
			{
				digit -= Cle.MIN_UP;
			}
			
			char newChar = this.cle.charAt(digit);
			
			if(message.charAt(i) >= Cle.MIN_LOW) 
			{
				newChar += Cle.MIN_LOW - Cle.MIN_UP;
			}
			
			Crypto.characterVector.set( i, newChar);
		}
		
		message = this.vectorToString();
		return message;
	}
	
	@Override
	public String obtenirNomAlgo() 
	{
		return "Substitution Alphabétique";
		
	}
}
