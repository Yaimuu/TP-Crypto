import java.io.IOException;
import java.util.ArrayList;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Main.java - Classe principale du programme, elle permet de lancer les algorithmes développés pour le projet
 */

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		Server s = new Server("Shakespeare.txt");
		ArrayList<String> texteEncode = new ArrayList<String>();
		boolean isKeyNum = false;
		boolean isKeyPhrase = false;
		boolean isKeyDico = false;
		
		for (String string : s. obtenirFichierEncode())
		{
			texteEncode.add(string);
		}
		
		System.out.println("Chargement, veuillez patienter...");
		
		Decodeur decodeur = new Decodeur("english_quadgrams.txt");
		
		Resultat resultNum = decodeur.trouveCleNumerique(texteEncode, 26);
		
		System.out.println("Nombre de clés numériques potentielles : " + resultNum.getCles().size());
		
		for(Object cle : resultNum.getCles())
		{
			Double cleDouble = (Double)cle;
			if(s.soumettreCle(cleDouble.intValue()))
			{
				System.out.println("Clé : " + cle + " Algo : " + resultNum.getAlgo());
				isKeyNum = true;
			}
		}
		
		
		
		if(!isKeyNum)
		{
			System.out.println("Aucune clé numérique n'a été trouvée !");
			System.out.println("Recherche de clé alphabétique, veuillez patienter...");
			
			ArrayList<Resultat> resultsPhrases = decodeur.trouveClePhrase(texteEncode, 26);
			int i = 0;
			
			for(Resultat result : resultsPhrases)
			{
				System.out.println("Nombre de clés alpha potentielles : " + result.getCles().size() + " pour l'algo " + result.getAlgo());
				if(s.soumettreCle((String)result.getCles().get(i)))
				{
					System.out.println("Clé : " + result.getCles().get(i) + " Algo : " + result.getAlgo());
					isKeyPhrase = true;
				}
				i++;
				
				if(Server.TENTATIVE_DE_SOUMMISSION < Server.MAX_SOUMISSION)
				{
					break;
				}
			}
			
			if(!isKeyPhrase)
			{
				System.out.println("Aucune clé alphabétique n'a été trouvée !");
				System.out.println("Recherche de clé dictionnaire, veuillez patienter...");
				
				Resultat resultDico = decodeur.trouveDictionnaire(texteEncode, 850);
				
				for(Object cle : resultDico.getCles())
				{
					if(s.soumettreCle((String)cle))
					{
						System.out.println("Clé : " + cle + " Algo : " + resultDico.getAlgo());
						isKeyDico = true;
					}
				}
				
				
			}
		}
		
		if(!isKeyDico)
		{
			System.out.println("Aucune clé n'a été trouvée !");
		}
		
	}
}
