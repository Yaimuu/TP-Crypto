/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 2.0
 * Fichier : Ceeeeaaaasaaaaaarrr.java - Classe implémentant l'algorithme de césar (Algorithme decaleADroite)
 * (Le nom de la classe est une référence culturelle de type boutade)
 */

public class Ceeeeaaaasaaaaaarrr implements Crypto
{
	private int cle;
	
	@Override
	public void setCle(int cle) 
	{
		if(cle % 26 == 0)
		{
			cle++;
		}
		this.cle = cle % 26;
	}

	@Override
	public void setCle(String cle) { /* Ne fait rien*/ }

	public Ceeeeaaaasaaaaaarrr(int cle)
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
			int digit = Crypto.characterVector.get(i);
			
			
			if(digit - this.cle < Cle.MIN_UP)
			{
				digit = Cle.MAX_UP - (Cle.MIN_UP - (digit - this.cle)) + 1;
			}
			else if(digit - this.cle < Cle.MIN_LOW && digit > Cle.MAX_UP)
			{
				digit =  Cle.MAX_LOW - (Cle.MIN_LOW - (digit - this.cle)) + 1;
			}
			else
			{
				digit -= this.cle;
			}
			
			Crypto.characterVector.set(i, (char)digit);
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
			int digit = Crypto.characterVector.get(i);
			
			if(digit + this.cle > Cle.MAX_LOW)
			{
				digit = Cle.MIN_LOW + ((digit + this.cle) % Cle.MAX_LOW) - 1;
			}
			else if(digit + this.cle > Cle.MAX_UP && digit < Cle.MIN_LOW)
			{
				digit = Cle.MIN_UP + ((digit + this.cle) % Cle.MAX_UP) - 1;
			}
			else
			{
				digit += this.cle;
			}
			
			Crypto.characterVector.set(i, (char)digit);
		}
		
		message = this.vectorToString();
		
		return message;
	}

	@Override
	public String obtenirNomAlgo()
	{
		return "Chiffre de César";
	}
	
}
