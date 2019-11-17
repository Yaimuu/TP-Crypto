import java.util.ArrayList;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Resultat.java - Classe permettant de stocker le résultat des décryptions.
 */

public class Resultat 
{
	private String algo;
	private ArrayList<Object> cles = new ArrayList<>();
	private ArrayList<Double> scores = new ArrayList<Double>();
	
	public Resultat(String algo) 
	{
		this.algo = algo;
	}

	public void ajoute(String cle, double score) 
	{
		cles.add(cle);
		scores.add(score);
		trierListes();
	}
	
	public void ajoute(int cle, double score) 
	{
		cles.add(cle);
		scores.add(score);
		trierListes();
	}

	public String getAlgo() 
	{
		return algo;
	}

	public void setAlgo(String algo) 
	{
		this.algo = algo;
	}

	public ArrayList<Object> getCles() 
	{
		return cles;
	}

	public void setCles(ArrayList<Object> cles) 
	{
		this.cles = cles;
	}
	
	/*
	 * Fonction permettant de trier par ordre décroissant et en fonction des scores les ArrayList scores et cles
	 */
	private void trierListes()
	{
		double tmp = 0;
		Object tmpCle;
		for(int i = 0; i < scores.size(); i++)
		{
			for(int j = 1; j < scores.size(); j++)
			{
				if(scores.get(j-1) < scores.get(j))
				{
					tmp = scores.get(j-1);
					scores.set(j-1, scores.get(j));
					scores.set(j, tmp);
					tmpCle = cles.get(j-1);
					cles.set(j-1, scores.get(j));
					cles.set(j, tmp);
				}
			}
		}
	}
}
