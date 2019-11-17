import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Decodeur.java - Classe permettant de casser les cryptages du server.
 */

public class Decodeur 
{
	final String ESPACE = " ";
	final int SUITE = 0 ; //case qui contiendra la suite de lettres
	final int FREQUENCE = 1 ; //case qui contiendra la fréquence
	
	private Map<String, Double> frequences = new HashMap<String, Double>();;
	
	public Decodeur(String fileName) throws IOException 
	{
		File file = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) 
		{
			String[] parties = st.split(ESPACE);
			this.frequences.put(parties[SUITE], Math.log(Double.valueOf(parties[FREQUENCE])) );
		}
		br.close();
	}
	
	public double calculScore(String phrase)
	{
		double score = 0;
		for(Map.Entry<String, Double> entry : this.frequences.entrySet())
		{
			String key = entry.getKey();
		    Double value = entry.getValue();
		    
		    if(phrase.contains(key))
		    {
		    	score += value;
		    }
		}
		return score;
	}
	
	public ArrayList<Integer> calculLongueurCle(String texte, int max)
	{
		ArrayList<Integer> longueurPossible = new ArrayList<Integer>();
		
		for(int i = 0; i < max; i++)
		{
			String lettresANDansTexte = "";
			for(int j = 0; j < texte.length(); j+=i)
			{
				lettresANDansTexte += texte.charAt(j);
			}
			double score = calculeFrequence(lettresANDansTexte);
			if(Math.abs(score - 0.06) < 0.007)
			{
				longueurPossible.add(i);
			}
		}
		
		return longueurPossible;
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
		Resultat cesarResultat = new Resultat("Cesar");
		for(int i = 0; i < max; i++)
		{
			Ceeeeaaaasaaaaaarrr cesar = new Ceeeeaaaasaaaaaarrr();
			double score = 0;
			for(String phrase : phrasesEncodees)
			{
				score += calculScore(phrase);
			}
			if(score >= 300)
			{
				cesarResultat.ajoute(i, score);
			}
		}
		return cesarResultat;
	}
	
	public ArrayList<Resultat> trouveClePhrase(ArrayList<String> phrasesEncodees, int max)
	{
		ArrayList<Resultat> results = new ArrayList<Resultat>();
		results.add(new Resultat("Vigenere"));
		results.add(new Resultat("Xor"));
		String texteEncode = "";
		for(int i = 0; i < phrasesEncodees.size(); i++) {
			texteEncode += phrasesEncodees.get(i);
		}
		ArrayList<Integer> longueurPossible = calculLongueurCle(texteEncode, max);
		
		for(int j = 0; j < longueurPossible.size(); j++) {
			int total = 0;
			while(total < max) {
				double scoreDecaleEnColonne = 0.0;
				double scoreOuLogic = 0.0;
				char[] getCle = Cle.obtenirCleCar(1);
				String finalCle = "";
				for(int k = 0; k < getCle.length; k++) {
					finalCle += getCle[k];
				}
				Crypto Vigenere = new Vigenere();
				Vigenere.setCle(finalCle);
				Crypto Xor = new Xor();
				Xor.setCle(finalCle);
				
				for(int o = 0; o < phrasesEncodees.size(); o++) {
					scoreOuLogic += calculScore(Xor.decode(phrasesEncodees.get(o)));
					scoreDecaleEnColonne += calculScore(Vigenere.decode(phrasesEncodees.get(o)));
				}
				if(scoreOuLogic > 600) {
					total += 1;
					
				}
				
			}
		}
		
		return results;
	}
	
	public Resultat trouveDictionnaire(ArrayList<String> phrasesEncodees, int scoreTotal)
	{
		Resultat result = new Resultat("");
		return result;
	}
	
	
}
