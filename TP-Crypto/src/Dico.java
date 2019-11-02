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
		this.cle = cle;
	}
	
	@Override
	public void setCle(int cle) {
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
		// TODO Auto-generated method stub
		return message;
	}

}
