import java.io.BufferedReader;
import java.io.File;
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
	
	private Map<String, Double> frequences = new HashMap<String, Double>();
	
	/*
	 * Constructeur de la classe
	 */
	public Decodeur(String fileName) throws IOException 
	{
		File file = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		while ((st = br.readLine()) != null) 
		{
			String[] parties = st.split(ESPACE);
			this.frequences.put(parties[SUITE], Math.log10(Double.valueOf(parties[FREQUENCE])) );
		}
		
		br.close();
	}
	
	/*
	 * Calcule le score d’une phrase en utilisant les suites de quatre lettres que le constructeur a ajouté dans la HashMap fréquences.
	 */
	public double calculScore(String phrase)
	{
		double score = 0;
		for(Map.Entry<String, Double> entry : this.frequences.entrySet())
		{
			String key = entry.getKey();
		    Double value = entry.getValue();
		    
		    if(phrase.toUpperCase().contains(key))
		    {
		    	score += value;
		    }
		}
		return score;
	}
	
	/*
	 * Utilise la distribution des lettres des phrases encodées pour déterminer la longueur de la clé utilisée lors de l’encodage
	 */
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
	
	/*
	 * Calcule la fréquence des groupements de 4 lettres contenues dans le fichier associé, contenus dans le String s
	 */
	public double calculeFrequence(String s) 
	{
		Map<Integer, Integer> lettres = new HashMap<Integer, Integer>();
		int n = 0;
		double somme = 0.0;
		double total = 0.0;
		s = s.toUpperCase();
		
		for (int i = 0; i < s.length(); i++) 
		{
			Integer nombrePrecedent = lettres.get(s.charAt(i) - 65);
			
			if (nombrePrecedent == null) 
			{
				nombrePrecedent = 0;
			}
			
			lettres.put(new Integer(s.charAt(i) - 65),
			nombrePrecedent + 1);
			n++;
		}
		
		Iterator<Entry<Integer, Integer>> it =
		lettres.entrySet().iterator();
		
		while (it.hasNext()) 
		{
			Map.Entry<Integer, Integer> pair = it.next();
			somme = somme + (pair.getValue() * (pair.getValue() - 1));
		}
		
		total = somme / (n * (n - 1));
		
		return total;
	}
	
	/*
	 * tente de trouver une clé numérique qui aurait été utilisée dans le cadre d’un encodage de type César.
	 */
	public Resultat trouveCleNumerique(ArrayList<String> phrasesEncodees, int max)
	{
		Resultat cesarResultat = new Resultat("Chiffre de César");
		for(int i = 0; i < max; i++)
		{
			Ceeeeaaaasaaaaaarrr cesar = new Ceeeeaaaasaaaaaarrr(i);
			double score = 0;
			
			for(String phrase : phrasesEncodees)
			{
				score += calculScore(cesar.decode(phrase));
			}
			
			if(score >= 300)
			{
				cesarResultat.ajoute(i, score);
			}
		}
		return cesarResultat;
	}
	
	/*
	 * Décode les phrases encodées avec toutes les clés entre 0 et max et 
	 * détermine si les phrases décodées ressemblent à de l’anglais via la méthode calculScore.
	 */
	public ArrayList<Resultat> trouveClePhrase(ArrayList<String> phrasesEncodees, int max)
	{
		ArrayList<Resultat> results = new ArrayList<Resultat>();
		results.add(new Resultat("Vigenere"));
		results.add(new Resultat("Xor"));
		String texteEncode = "";
		
		for(int i = 0; i < phrasesEncodees.size(); i++) 
		{
			texteEncode += phrasesEncodees.get(i);
		}
		
		ArrayList<Integer> longueurPossible = calculLongueurCle(texteEncode, max);
		
		for(int j = 0; j < longueurPossible.size(); j++) 
		{
			int total = 0;
			
			while(total < max) 
			{
				double scoreDecaleEnColonne = 0.0;
				double scoreOuLogic = 0.0;
				char[] getCle = Cle.obtenirCleCar(longueurPossible.get(j));
				String finalCle = "";
				
				for(int k = 0; k < getCle.length; k++) 
				{
					finalCle += getCle[k];
				}
				
				Crypto Vigenere = new Vigenere(finalCle);
				
				Crypto Xor = new Xor(finalCle);
				
				
				for(int o = 0; o < phrasesEncodees.size(); o++) 
				{
					scoreOuLogic += calculScore(Xor.decode(phrasesEncodees.get(o)));
					scoreDecaleEnColonne += calculScore(Vigenere.decode(phrasesEncodees.get(o)));
				}
				
				if(scoreOuLogic > 600) 
				{
					total += 1;
					char[][] cleMutees = Cle.clesMutees(getCle);
					
					for(int k = 0; k < cleMutees.length; k++) 
					{
						Crypto XorMutee = new Xor(new String(cleMutees[k]));
						
						double scoreOuLogicMute = 0.0;
						
						for(int s = 0; s < phrasesEncodees.size(); s++) 
						{
							scoreOuLogicMute += calculScore(XorMutee.decode(phrasesEncodees.get(s)));
						}
						
						if(scoreOuLogicMute > scoreOuLogic) 
						{
							scoreOuLogic = scoreOuLogicMute;
							getCle = cleMutees[j];
						}
						
						results.get(1).ajoute(new String(getCle), scoreOuLogic);
					}
				}
				
				if(scoreDecaleEnColonne > 600) 
				{
					total += 1;
					char[][] cleMutees = Cle.clesMutees(getCle);
					
					for(int k = 0; k < cleMutees.length; k++) 
					{
						Crypto VigenereMutee = new Vigenere(new String(cleMutees[k]));
						
						double scoreDecaleEnConlonneMute = 0.0;
						for(int s = 0; s < phrasesEncodees.size(); s++) {
							scoreDecaleEnConlonneMute += calculScore(VigenereMutee.decode(phrasesEncodees.get(s)));
						}
						if(scoreDecaleEnConlonneMute > scoreDecaleEnColonne) {
							scoreDecaleEnColonne = scoreDecaleEnConlonneMute;
							getCle = cleMutees[j];
						}
						results.get(1).ajoute(new String(getCle), scoreDecaleEnColonne);
					}
				}
				
			}
			total = 0;
		}
		
		return results;
	}
	
	/*
	 * Permet de casser un encodage fait avec l’algorithme du dictionnaire.
	 */
	public Resultat trouveDictionnaire(ArrayList<String> phrasesEncodees, int scoreTotal)
	{
		char[] createCle = Cle.obtenirCleCar(26);
		Crypto dictionnaire = new Dico(new String(createCle));
		
		Resultat result = new Resultat("Dico");
		double score = 0.0;
		
		do {
			char[] cleRearangee = Cle.cleRearrangee(createCle);
			double scoreRearange = 0.0;
			
			for(int j = 0; j < phrasesEncodees.size(); j++) 
			{
				scoreRearange += calculScore(dictionnaire.decode(phrasesEncodees.get(j)));
			}
			
			if(score < scoreRearange) 
			{
				score = scoreRearange;
				createCle = cleRearangee;
			}
			if(score > 600) 
			{
				result.ajoute(new String(createCle), score);
			}
			
		} while(score < scoreTotal);
		
		return result;
	}
	
	
}
