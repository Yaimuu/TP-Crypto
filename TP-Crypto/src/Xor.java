/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Xor.java - Classe implémentant l'algorithme de cryptage XOR (Algorithme du ou-logic)
 */

public class Xor implements Crypto
{
	private String cle;
	
	@Override
	public void setCle(int cle) 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setCle(String cle) 
	{
		this.cle = cle;
		
	}

	public Xor() 
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
			//System.out.println((message.charAt(i) ^ this.cle.charAt(i % this.cle.length())));
			
			
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
