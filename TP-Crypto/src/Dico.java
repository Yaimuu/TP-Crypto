/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Dico.java - Classe implémentant l'algorithme du dictionnaire
 */

public class Dico implements Crypto
{
	private String cle;
	
	@Override
	public void setCle(String cle) 
	{
		this.cle = new String(Cle.obtenirCleAlphabet());
	}
	
	@Override
	public void setCle(int cle) 
	{
		// TODO Auto-generated method stub
	}

	public Dico() 
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
		
		//System.out.println(this.cle);
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
