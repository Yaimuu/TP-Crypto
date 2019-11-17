/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 2.0
 * Fichier : Xor.java - Classe implémentant l'algorithme de cryptage XOR (Algorithme du ou-logic)
 */

public class Xor implements Crypto
{
	private String cle;
	
	@Override
	public void setCle(int cle) { /* Ne fait rien*/ }
	
	@Override
	public void setCle(String cle) 
	{
		this.cle = cle;
		
	}

	public Xor(String cle) 
	{
		super();
		this.setCle(cle);
	}

	@Override
	public String decode(String message) 
	{
		Crypto.characterVector.clear();
		this.loadMessage(message);
		
		for(int i = 0; i < Crypto.characterVector.size(); i++)
		{
			
			char newChar = (char)((this.cle.charAt(i % this.cle.length()) ^ message.charAt(i)) );
			
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
			
			char newChar = (char)((message.charAt(i) ^ this.cle.charAt(i % this.cle.length())) );
			
			Crypto.characterVector.set( i, newChar);
		}
		
		message = this.vectorToString();
		
		return message;
	}
	
	@Override
	public String obtenirNomAlgo() 
	{
		return "Chiffre XOR";
	}
}
