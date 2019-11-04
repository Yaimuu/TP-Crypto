/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Xor.java - Classe implémentant l'algorithme de cryptage XOR (Algorithme du ou-logic)
 */

public class Xor implements Crypto
{
	private int cle;
	
	@Override
	public void setCle(int cle) 
	{
		if(cle == 0)
		{
			cle++;
		}
		this.cle = cle;
	}
	
	@Override
	public void setCle(String cle) {
		// TODO Auto-generated method stub
		
	}

	public Xor() 
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
		
		return message;
	}
	
}
