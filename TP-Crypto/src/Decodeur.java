import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Decodeur 
{
	private Map<String, Double> frequences = new HashMap<String, Double>();
	
	void Decodeur()
	{
		
	}
	
	public double calculScore(String phrase)
	{
		return 0;
	}
	
	public ArrayList<Integer> calculLongueurCle(String texte, int max)
	{
		return new ArrayList<Integer>();
	}
	
	public double calculeFrequence(String s) 
	{
		Map<Integer, Integer> lettres = new HashMap<Integer, Integer>();
		int n = 0;
		double somme = 0.0;
		double total = 0.0;
		s = s.toUpperCase();
		for (int i = 0; i < s.length(); i++) {
		Integer nombrePrecedent = lettres.get(s.charAt(i) - 65);
		if (nombrePrecedent == null) {
		nombrePrecedent = 0;
		}
		lettres.put(new Integer(s.charAt(i) - 65),
		nombrePrecedent + 1);
		n++;
		}
		Iterator<Entry<Integer, Integer>> it =
		lettres.entrySet().iterator();
		while (it.hasNext()) {
		Map.Entry<Integer, Integer> pair = it.next();
		somme = somme + (pair.getValue() * (pair.getValue() - 1));
		}
		total = somme / (n * (n - 1));
		return total;
	}
	
	public Resultat trouveCleNumerique(ArrayList<String> phrasesEncodees, int max)
	{
		Resultat result = new Resultat("");
		return result;
	}
	
	public ArrayList<Resultat> trouveClePhrase(ArrayList<String> phrasesEncodees, int max)
	{
		return new ArrayList<Resultat>();
	}
	
	public Resultat trouveDictionnaire(ArrayList<String> phrasesEncodees, int scoreTotal)
	{
		Resultat result = new Resultat("");
		return result;
	}
	
	
}
